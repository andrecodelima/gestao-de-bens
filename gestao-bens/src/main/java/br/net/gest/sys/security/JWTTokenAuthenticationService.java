package br.net.gest.sys.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.net.gest.sys.entity.Usuario;
import br.net.gest.sys.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAuthenticationService {

    private static final long EXPIRATION_TIME = 11 * 24 * 60 * 60 * 1000L; // 11 dias
    private static final String SECRET_STRING = "!@$%%ss/-*-*sds565dsd-s/d-s*dsds!@$%%";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private final UsuarioRepository usuarioRepository;

    public JWTTokenAuthenticationService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Gera o token e retorna na resposta HTTP
    public void addAuthentication(HttpServletResponse response, String username) throws Exception {
        String token = generateToken(username);

        response.addHeader(HEADER_STRING, token);
        response.setContentType("application/json");
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    // Gera um token JWT com o username e tempo de expiração
    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Valida o token e retorna a autenticação
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            try {
                String tokenWithoutPrefix = token.replace(TOKEN_PREFIX, "").trim();
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(tokenWithoutPrefix)
                        .getBody();

                String username = claims.getSubject();
                if (username != null) {
                    Usuario usuario = usuarioRepository.findUsuarioByLogin(username);
                    if (usuario != null) {
                        return new UsernamePasswordAuthenticationToken(
                                usuario.getUsername(),
                                null,
                                usuario.getAuthorities()
                        );
                    }
                }
            } catch (Exception e) {
                liberacaoCors(response);
            }
        }

        liberacaoCors(response);
        return null;
    }

    // Liberação de CORS
    private void liberacaoCors(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    }
}
