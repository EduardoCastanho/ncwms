/*******************************************************************************
 * Copyright (c) 2013 The University of Reading
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University of Reading, nor the names of the
 *    authors or contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package uk.ac.rdg.resc.edal.ncwms;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import uk.ac.rdg.resc.edal.ncwms.config.NcwmsConfig;
import uk.ac.rdg.resc.edal.wms.WmsServlet;

/**
 * Servlet implementation class NcWmsServlet
 */
public class NcwmsServlet extends WmsServlet implements Servlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see WmsServlet#WmsServlet()
     */
    public NcwmsServlet() {
        super();
        /*
         * TODO in the real world: Get Javadoc uploading somewhere Cookbook
         * documentation
         */
    }

    @Override
    public void destroy() {
        super.destroy();
        NcwmsConfig.shutdown();
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        Object config = servletConfig.getServletContext().getAttribute("NcwmsCatalogue");
        if (config instanceof NcwmsCatalogue) {
            NcwmsCatalogue ncwmsCatalogue = (NcwmsCatalogue) config;
            setCatalogue(ncwmsCatalogue);
        } else {
            throw new ServletException(
                    "ncWMS configuration object is incorrect type.  The \"NcwmsConfig\" attribute of the ServletContext has been incorrectly set.");
        }
    }

    /*-
     * Test URL for this servlet.
     * http://localhost:8080/ncWMS/wms?REQUEST=GetMap&VERSION=1.3.0&FORMAT=image/png&CRS=CRS:84&BBOX=-180,-90,180,90&WIDTH=1024&HEIGHT=512&LAYERS=foam/TMP&STYLES=boxfill/alg&COLORSCALERANGE=265,305&TIME=2010-01-30T12:00:00.000Z&ELEVATION=5&NUMCOLORBANDS=50
     */
}