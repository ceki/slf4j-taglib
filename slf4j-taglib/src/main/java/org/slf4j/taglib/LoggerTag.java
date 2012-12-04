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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Abstract base class for the logging tags which log a message to a 
  * SLF4J category.
  * 
  * @author <a href="mailto:joeo@epesh.com">Joseph Ottinger</a>
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @author Matt Humphreys (<a href="http://www.yojava.org">yojava.org</a>)
  */

public abstract class LoggerTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String category;
    private String message;
    private Throwable t;

    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setException(Throwable t) {
        this.t = t;
    }
    
    // Tag interface
    //------------------------------------------------------------------------- 
    public int doStartTag() throws JspException  {
        if ( message != null ) {
            Logger logCategory = getLoggingCategory();
            if ( isEnabled( logCategory ) ) {
                // Log now as doAfterBody() may not be called for an empty tag 
                if ( t == null ) {
                    log( logCategory, message );
                } else {
                    log( logCategory, message, t );
                }
            }
            return SKIP_BODY;
        }
        return EVAL_BODY_BUFFERED;//EVAL_BODY_TAG;
    }
    
    public int doAfterBody() throws JspException {
        if (message == null) {
            Logger logCategory = getLoggingCategory();
            if ( isEnabled( logCategory ) ) {
                if ( t == null ) {
                    log( logCategory, getBodyContent().getString().trim() );
                } else {
                    log( logCategory, getBodyContent().getString().trim(), t );
                }
            }
        }
        return SKIP_BODY;
    }
    
    // Implementation methods
    //------------------------------------------------------------------------- 
    protected abstract boolean isEnabled(Logger logCategory);
    protected abstract void log(Logger logCategory, String message);
    protected abstract void log(Logger logCategory, String message, Throwable t);
    
    protected Logger getLoggingCategory() {
        if ( category == null ) {
            // used to be Category.getRoot();
            return LoggerFactory.getLogger("");
        }
        else {
            return LoggerFactory.getLogger( category );
        }
    }
}

