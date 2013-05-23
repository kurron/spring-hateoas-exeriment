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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import java.lang.reflect.Method
import org.kurron.tools.adapter.rest.BaseController
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
class UserController extends BaseController {
    private UserResourceAssembler theAssembler = new UserResourceAssembler()

    @RequestMapping( method = RequestMethod.GET, value = '/{id}' )
    ResponseEntity<UserResource> show( @PathVariable Long id ) {
        User user = new User( id, 'bob', new Date( System.currentTimeMillis() ) )
        new ResponseEntity<UserResource>( theAssembler.toResource( user ), HttpStatus.OK )
    }

    @RequestMapping( method = RequestMethod.GET )
    ResponseEntity<Map<String, ?>> discover( ) {
        def list = []
        addInIndividualUserLinks( list )
        def map = ['links': assembleResourceInventoryLinks( list )]
        new ResponseEntity<Map<String, ?>>( map, HttpStatus.OK )
    }

    private static addInIndividualUserLinks( list ) {
        Method method = UserController.class.getMethod( 'show', Long )
        (1..10).each { Long id -> list << linkTo( method, id ).withRel( 'users.user' ) }
    }

}
