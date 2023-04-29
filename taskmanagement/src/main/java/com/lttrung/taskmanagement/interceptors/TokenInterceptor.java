package com.lttrung.taskmanagement.interceptors;

import com.lttrung.taskmanagement.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@AllArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        String[] authorizationArray = authorization.split(" ");
        if (!authorizationArray[0].equals("Bearer")) {
            return false;
        }
        String token = authorizationArray[1];
        Claims claims = jwtHelper.decodeToken(token);
        String username = (String) claims.get("username");
        request.setAttribute("username", username);
        return true;
    }
}
