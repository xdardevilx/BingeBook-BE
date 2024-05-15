package valerio.BingeBookBE.security;

import java.math.BigInteger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

    private final JWTTools jWTTools;

    public AuthTokenInterceptor(JWTTools jWTTools) {
        this.jWTTools = jWTTools;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("Authorization");
        if (authToken == null || !authToken.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authToken.substring(7);

        BigInteger userId = jWTTools.extractIdFromToken(token);
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        request.setAttribute("userId", userId);

        return true;
    }
}

