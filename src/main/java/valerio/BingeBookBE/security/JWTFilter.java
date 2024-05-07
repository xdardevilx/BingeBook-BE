package valerio.BingeBookBE.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.service.UserService;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private final JWTTools jwtTools;

    @Autowired
    UserService userService;


    public JWTFilter(JWTTools jwtTools) {
        this.jwtTools = jwtTools;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please insert your token in Authorization Header");
                return;
            }

            String accessToken = authHeader.substring(7);
            jwtTools.verifyToken(accessToken);
            String id = jwtTools.extractIdFromToken(accessToken);
            User found = userService.findById(Long.parseLong(id));
            Authentication authentication = new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            // Handle exception using ErrorInterceptor
            request.setAttribute("javax.servlet.error.exception", ex);
            getServletContext().getNamedDispatcher("error").forward(request, response);
        }
    }


}
