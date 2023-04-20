package edu.javeriana.ProyectoDesarrolloWeb.Security.controller;

import edu.javeriana.ProyectoDesarrolloWeb.Security.domain.User;
import edu.javeriana.ProyectoDesarrolloWeb.Security.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

        @Autowired
        private UserService service;

        @PostMapping(value="login", produces = {MediaType.APPLICATION_JSON_VALUE})
        public User userPost(@RequestParam("user") String username, @RequestParam("password")String password){
                String token = getJWTToken(username);
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setToken(token);
                return service.createUser(user);
        }

        private String getJWTToken(String username) {
                String secretKey = "mySecretKey";
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_USER");

                String token = Jwts
                        .builder()
                        .setId("userJWT")
                        .setSubject(username)
                        .claim("authorities",
                                grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();

                return "Bearer " + token;
        }


}
