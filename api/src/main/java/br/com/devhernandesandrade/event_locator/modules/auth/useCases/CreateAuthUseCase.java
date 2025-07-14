package br.com.devhernandesandrade.event_locator.modules.auth.useCases;

import br.com.devhernandesandrade.event_locator.modules.auth.dto.CreateAuthRequest;
import br.com.devhernandesandrade.event_locator.modules.users.entities.UserEntity;
import br.com.devhernandesandrade.event_locator.modules.users.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateAuthUseCase {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String execute (CreateAuthRequest createAuthRequest) {
        var user = userRepository.findByEmail(createAuthRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("E-mail ou senha incorretos."));

        if (!passwordEncoder.matches(createAuthRequest.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("Senha incorreta");
        }
        return buildToken(Map.of(), user, jwtExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserEntity user, long jwtExpiration) {
        var authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuer("event_locator")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("authorities", authorities)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
