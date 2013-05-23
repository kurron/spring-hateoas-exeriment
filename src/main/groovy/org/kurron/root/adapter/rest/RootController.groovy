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

import org.kurron.user.adapter.rest.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.LinkDiscoverer
import org.springframework.hateoas.RelProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Concrete REST adapter.
 */
@Controller( "rootController" )
@RequestMapping( value = '/', produces = 'application/json' )
class RootController {
    @Autowired EntityLinks entityLinks
    @Autowired RelProvider relationProvider
    @Autowired LinkDiscoverer linkDiscoverer

    /**
     * This method will present links back to the REST client to all the known
     * resources.
     * @return a REST shell compatible collection of resource.
     */
    @RequestMapping( method = RequestMethod.GET )
    ResponseEntity<Map<String, ?>> discover( ) {
        def map = ['links': assembleResourceInventoryLinks()]
        new ResponseEntity<Map<String, ?>>( map, HttpStatus.OK )
    }

    private assembleResourceInventoryLinks( ) {
        List<Map<String, String>> links = new ArrayList<>( 1 )
        Map<String, String> map = [:]
        Link link = entityLinks.linkFor( User ).withRel( 'user' )
        map.put( 'rel', link.rel )
        map.put( 'href', link.href )
        links << map
        return links
    }
}
