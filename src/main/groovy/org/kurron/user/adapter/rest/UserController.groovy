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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Concrete REST adapter.
 */
@Controller( "userController" )
@RequestMapping( value = '/user', produces = 'application/json' )
@ExposesResourceFor( User )
class UserController implements UserAdapter {
    private Map<Long, String> data = [:]
    private UserResourceAssembler theAssembler = new UserResourceAssembler()
    @Autowired EntityLinks entityLinks

    UserController( ) {
        SecureRandom random = new SecureRandom()
        (1..20).each {
            data[it] = Integer.toHexString( random.nextInt( Integer.MAX_VALUE ) ).toUpperCase()
        }
    }

    @Override
    @RequestMapping( method = RequestMethod.GET, value = '/{id}' )
    ResponseEntity<UserResource> user( @PathVariable Integer id ) {
        String value = data[id]

        new ResponseEntity<UserResource>( theAssembler.toResource( new User( id, value ) ), HttpStatus.OK )
    }

    @Override
    @RequestMapping( method = RequestMethod.GET )
    ResponseEntity<List<UserResource>> user( ) {
        def list = []
        data.each { k, v -> list << new User( k, v ) }
        def resources = theAssembler.toResources( list )
        new ResponseEntity<List<UserResource>>( resources, HttpStatus.OK )
    }
}
