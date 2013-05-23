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
package org.kurron.spring

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.config.EnableEntityLinks
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.web.servlet.config.annotation.EnableWebMvc

/**
 * This Spring context is for the web controllers.
 */
@Configuration
@EnableWebMvc
@ComponentScan( basePackages = ['org.kurron.user.adapter.rest', 'org.kurron.root.adapter.rest'] )
@EnableEntityLinks
@EnableHypermediaSupport
class WebContext {}
