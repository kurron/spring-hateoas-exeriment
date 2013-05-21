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

import org.springframework.hateoas.Identifiable

/**
 * A dummy user class.  It is used just to transfer data.
 */
class User implements Identifiable<Long> {
    Long id
    String name
    Date date

    User( Long aId, String aName, Date aDate ) {
        date = aDate
        id = aId
        name = aName
    }
}
