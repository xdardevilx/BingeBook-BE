package valerio.BingeBookBE.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.repositories.UserDAO;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final UserDAO userDAO;
    private final JWTTools jwtTools;

    @Autowired
    JWTFilter(UserDAO userDAO, JWTTools jwtTools) {
        this.userDAO = userDAO;
        this.jwtTools = jwtTools;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, StringConfig.errorUnauthorized);
                return;
            }

            String accessToken = authHeader.substring(7);
            jwtTools.verifyToken(accessToken);
            Long id = jwtTools.extractIdFromToken(accessToken);
            User found = userDAO.findById(id).orElse(null);
            Authentication authentication = new UsernamePasswordAuthenticationToken(found, null,
                    found.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            // Handle exception using ErrorInterceptor
            request.setAttribute("javax.servlet.error.exception", ex);
            getServletContext().getNamedDispatcher("error").forward(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
