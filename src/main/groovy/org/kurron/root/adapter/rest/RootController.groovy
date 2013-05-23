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
package org.kurron.root.adapter.rest

import org.kurron.tools.adapter.rest.BaseController
import org.kurron.user.adapter.rest.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Concrete REST adapter.
 */
@Controller( 'rootController' )
@RequestMapping( value = '/', produces = 'application/json' )
class RootController extends BaseController {

    /**
     * This method will present links back to the REST client to all the known
     * resources.
     * @return a REST shell compatible collection of resource.
     */
    @RequestMapping( method = RequestMethod.GET )
    ResponseEntity<Map<String, ?>> discover( ) {
        def list = [entityLinks.linkFor( User ).withRel( 'user' )]
        def map = ['links': assembleResourceInventoryLinks( list )]
        new ResponseEntity<Map<String, ?>>( map, HttpStatus.OK )
    }
}
