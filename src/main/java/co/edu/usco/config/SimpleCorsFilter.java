package co.edu.usco.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple CORS filter to handle Cross-Origin Resource Sharing (CORS) requests.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Value("${app.client.url}")
    private String clientAppUrl = "";

    /**
     * Default constructor.
     */
    public SimpleCorsFilter() {
    }

    /**
     * Applies the CORS filter to incoming requests.
     *
     * @param req the ServletRequest object.
     * @param res the ServletResponse object.
     * @param chain the FilterChain object.
     * @throws IOException if an I/O error occurs during the processing.
     * @throws ServletException if a servlet error occurs during the processing.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        Map<String, String> map = new HashMap<>();
        String originHeader = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Initializes the filter.
     *
     * @param filterConfig the FilterConfig object.
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Destroys the filter.
     */
    @Override
    public void destroy() {
    }
}