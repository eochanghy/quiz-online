/*
 * Copyright(C) 2021, Nguyen Thanh Dat.
 * J3.L.P0001
 * Quiz Online
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-07-07      1.0                 DatNT           First Implement
 * 2021-07-13      2.0                 DatNT           Fix Comment 
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains methods to Redirect when user go to page by jsp page. Filter will
 * redirect the JSP url to the corresponding url controller.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class FilterJSP implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     * Parameterless constructor used to initialize a
     * <code>filter.FilterJSP</code> object.
     */
    public FilterJSP() {
    }


    /**
     * Redirect when user go to page by JSP page. Filter will redirect the JSP
     * url to the corresponding url controller.
     *
     * @param request The servlet request we are processing. It is a
     * <code> javax.servlet.ServletRequest</code>
     * @param response The servlet response we are creating. It is a <code> javax.servlet.ServletResponse
     * </code>
     * @param chain The filter chain we are processing. The FilterChain passed
     * into this method allows the Filter to pass on the request and response to
     * the next entity in the chain. It is a
     * <code>javax.servlet.FilterChain</code> object.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpRespond = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        String contextPath = httpRequest.getContextPath();
        if (url.endsWith("/ChooseQuestion.jsp")) {
            httpRespond.sendRedirect(contextPath + "/ChooseQuestion");
        } else {
            if (url.endsWith("/Home.jsp")) {
                httpRespond.sendRedirect(contextPath + "/Home");
            } else {
                if (url.endsWith("/Login.jsp")) {
                    httpRespond.sendRedirect(contextPath + "/Login");
                } else {
                    if (url.endsWith("/MakeQuiz.jsp")) {
                        httpRespond.sendRedirect(contextPath + "/MakeQuiz");
                    } else {
                        if (url.endsWith("/ManageQuiz.jsp")) {
                            httpRespond.sendRedirect(contextPath + "/MakeQuiz");
                        } else {
                            if (url.endsWith("/Register.jsp")) {
                                httpRespond.sendRedirect(contextPath + "/Register");
                            } else {
                                if (url.endsWith("/Result.jsp")) {
                                    httpRespond.sendRedirect(contextPath + "/Result");
                                } else {
                                    if (url.endsWith("/TakeQuiz.jsp")) {
                                        httpRespond.sendRedirect(contextPath + "/TakeQuiz");
                                    } else {
                                        httpRequest.getRequestDispatcher("/view/html/PageNotFound.jsp").forward(request, response);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterJSP()");
        }
        StringBuffer sb = new StringBuffer("FilterJSP(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    /**
     * Print error if error occurred
     *
     * @param t error throwable
     * @param response current response . It is a
     * <code>javax.servlet.ServletResponse</code>
     */
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Get stack trace of throwablr
     *
     * @param t throwable to get stack trace
     * @return string representation of stack trace
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    /**
     * Print message to log
     *
     * @param msg message to print
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
