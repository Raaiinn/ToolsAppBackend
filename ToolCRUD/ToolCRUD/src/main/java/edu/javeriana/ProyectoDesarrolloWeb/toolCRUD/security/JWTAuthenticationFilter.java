package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserDetails userDetails;

    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;;

        if (tokenManager == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            tokenManager = webApplicationContext.getBean(TokenManager.class);
        }

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);
            try {
                username = tokenManager.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get authorization token. Please try again");
            } catch (ExpiredJwtException e) {
                System.out.println("Your session token has expired. Please login again");
            }
        } else {
            System.out.println("Bearer String not found in token");

        }
        if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenManager.validateJwtToken(token, userDetails)) {
                userDetails = new UserDetailsImpl();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
                System.out.println("Successful authentication!");
                System.out.println("Welcome "+username);
                System.out.println("Your authentication token is: "+token);
                System.out.println("Committed successfully ");
            }
        }



        filterChain.doFilter(request, response);
    }
}
