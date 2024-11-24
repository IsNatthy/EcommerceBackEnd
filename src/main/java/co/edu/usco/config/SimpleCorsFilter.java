package co.edu.usco.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Filter to handle Cross-Origin Resource Sharing (CORS) requests.
 * This filter sets the necessary headers to allow cross-origin requests.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    /**
     * URL of the client application allowed to make cross-origin requests.
     */
    @Value("${app.client.url}")
    private String clientAppUrl = "";

    /**
     * Default constructor.
     */
    public SimpleCorsFilter() {
    }

    /**
     * Method to handle the filtering of requests and responses.
     * Sets the necessary CORS headers and handles preflight requests.
     *
     * @param req the ServletRequest object
     * @param res the ServletResponse object
     * @param chain the FilterChain object
     * @throws IOException if an I/O error occurs during the processing
     * @throws ServletException if a servlet error occurs during the processing
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String originHeader = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600"); // Cache for 1 hour
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Specific headers
        response.setHeader("Access-Control-Allow-Headers",
                "Access-Control-Allow-Headers, " +
                        "Origin, Accept, X-Requested-With, " +
                        "Content-Type, Access-Control-Request-Method, " +
                        "Access-Control-Request-Headers, Authorization");

        // Handle OPTIONS (preflight) requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Method to initialize the filter.
     *
     * @param filterConfig the FilterConfig object
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Method to destroy the filter.
     */
    @Override
    public void destroy() {
    }
}