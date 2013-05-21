/*******************************************************************************
 * Copyright year Ronald D. Kurr kurr@kurron.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package org.kurron.user.adapter.rest

import java.security.SecureRandom
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Concrete REST adapter.
 */
@Controller( "userController" )
@RequestMapping( value = '/users', produces = 'application/json' )
class UserController implements UserAdapter {
    private def data = [:]

    UserController( ) {
        SecureRandom random = new SecureRandom()
        (1..20).each {
            data[it] = Integer.toHexString( random.nextInt( Integer.MAX_VALUE ) ).toUpperCase()
        }
    }

    @Override
    @RequestMapping( method = RequestMethod.GET, value = '/{userId}' )
    @ResponseBody
    User findByUserId( @PathVariable Integer userId ) {
        String value = data[userId]
        new User( userId, value )
    }

    @Override
    @RequestMapping( method = RequestMethod.GET )
    ResponseEntity<List<User>> findAllIds( ) {
        def list = []
        data.each { k, v -> list << new User( k, v ) }
        new ResponseEntity<List<User>>( list, HttpStatus.OK )
    }
}
