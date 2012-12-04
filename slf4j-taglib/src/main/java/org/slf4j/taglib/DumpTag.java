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

import java.util.Enumeration;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * A straight copy of the log4j version (retained for compatibility).
 * @author Matt Humphreys (<a href="http://www.yojava.org">yojava.org</a>)
 *
 */
public class DumpTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private int scope;
    
    
    public void setScope(String sc) throws JspException {
        if (sc.equalsIgnoreCase("session")) {
            this.scope = PageContext.SESSION_SCOPE;
        }
        else if (sc.equalsIgnoreCase("request")) { 
            this.scope = PageContext.REQUEST_SCOPE;
        }
        else if (sc.equalsIgnoreCase("application")) {
            this.scope = PageContext.APPLICATION_SCOPE;
        }
        else if (sc.equalsIgnoreCase("page")) {
            this.scope = PageContext.PAGE_SCOPE;
        }
        else {
            throw new JspException(
                "Scope must be page, request, session or application."
            );
        }
    }
    
    public int doEndTag() throws JspException {
        try {
            Enumeration names = pageContext.getAttributeNamesInScope(scope);
            pageContext.getOut().write("<dl>");
            while(names.hasMoreElements()) {
                String name = (String) names.nextElement();
                Object value = pageContext.getAttribute(name, scope);
                
                pageContext.getOut().write("<dt><code>"+name+"</code></dt>");
                pageContext.getOut().write("<dd><code>"+value+"</code></dd>");
            }
            pageContext.getOut().write("</dl>");
        }
        catch (Exception e) {
            throw new JspException(e);//"Exception: "+e.getMessage());
        }
        return EVAL_PAGE;
    }
}
