/*
 * Copyright (c) 2008 QOS.ch
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.slf4j.taglib;

import org.slf4j.Logger;

/** Logs a trace message to the current SLF4J category.
  *
  * @author <a href="mailto:joeo@epesh.com">Joseph Ottinger</a>
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @author Matt Humphreys (<a href="http://www.yojava.org">yojava.org</a>)
  */

public class TraceTag extends LoggerTag {
	private static final long serialVersionUID = 1L;
    
    protected boolean isEnabled(Logger logCategory) {
        return logCategory.isTraceEnabled();
    }

    protected void log(Logger logCategory, String message) {
        logCategory.trace(message);
    }

    protected void log(Logger logCategory, String message, Throwable t) {
        logCategory.trace(message,t);
    }
}

