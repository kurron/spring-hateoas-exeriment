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
package org.kurron.tools.adapter.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.LinkDiscoverer
import org.springframework.hateoas.RelProvider

/**
 * Common base class for HATEOAS REST adapters.
 */
class BaseController {
    @Autowired
    protected EntityLinks entityLinks

    @Autowired
    protected RelProvider relationProvider

    @Autowired
    protected LinkDiscoverer linkDiscoverer

    protected assembleResourceInventoryLinks( List<Link> resourceLinks ) {
        List<Map<String, String>> maps = new ArrayList<>( 1 )
        resourceLinks.each { Link link -> maps << storeLinkAttributes( link ) }
        return maps
    }

    private static storeLinkAttributes( Link link ) {
        Map<String, String> map = [:]
        map.put( 'rel', link.rel )
        map.put( 'href', link.href )
        return map
    }
}
