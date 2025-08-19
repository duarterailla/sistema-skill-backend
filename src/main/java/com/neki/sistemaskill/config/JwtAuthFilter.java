package com.neki.sistemaskill.config;

import com.neki.sistemaskill.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

// Filtro para autenticação JWT nas requisições protegidas
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String jwtSecret;

    public JwtAuthFilter(UserService userService, String jwtSecret) {
        this.userService = userService;
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        // Ignora autenticação para rotas públicas e GET de skills/user-skills
        if (path.startsWith("/api/auth/") ||
            (path.startsWith("/api/skills") && request.getMethod().equals("GET")) ||
            (path.startsWith("/api/user-skills") && request.getMethod().equals("GET"))) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        // Busca o header Authorization
        if (header != null && header.startsWith("Bearer ")) {
            // Se houver token JWT, tenta validar
            String token = header.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
                // Extrai informações do token
                String email = claims.getSubject();
                var user = userService.findByEmailOrNickname(email);
                // Busca usuário pelo e-mail ou nickname
                if (user != null && user.getEmail().equals(email)) {
                    // Se usuário existe, autentica no contexto
                    UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(email).password("").authorities("USER").build();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // Token inválido
            }
        }
        filterChain.doFilter(request, response);
    }
}
