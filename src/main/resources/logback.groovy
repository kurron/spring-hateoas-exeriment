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
import static ch.qos.logback.classic.Level.ALL
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

context.name = "BAT"
def MESSAGE_FORMAT = "%contextName %5.5relative %.-1level %35.35logger{0} %message %throwable{short}%n"
def consoleAppender = "CONSOLE"
appender( consoleAppender, ConsoleAppender ) {
    encoder( PatternLayoutEncoder ) {
        pattern = "${MESSAGE_FORMAT}"
    }
}
logger( "org.kurron", ALL )
root( ALL, [consoleAppender] )
