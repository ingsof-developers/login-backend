package com.developers.developers.Service;

import com.developers.developers.Repository.RoleRepository;
import com.developers.developers.Repository.UserRepository;
import com.developers.developers.jwt.JwtService;
import com.developers.developers.model.entity.AuthResponse;
import com.developers.developers.model.entity.LoginRequest;
import com.developers.developers.model.entity.RegisterRequest;
import com.developers.developers.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {

        String email = UsernameToEmail(registerRequest.getUsername(), registerRequest.getLastName(), registerRequest.getMaternalSurname());

        UserEntity userEntity = UserEntity.builder()
                .username(registerRequest.getUsername())
                .lastName(registerRequest.getLastName())
                .maternalSurname(registerRequest.getMaternalSurname())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(email)
                        .authorities(Collections.singletonList(roleRepository.findByName("USER"))).build();

        userRepository.save(userEntity);

        return AuthResponse.builder()
                .token(jwtService.getToken(userEntity))
                .build();
    }

    public String UsernameToEmail(String username, String lastName, String maternalSurname) {
        String[] names = username.split(" ");
        StringBuilder initials = new StringBuilder();

        for (String name : names) {
            initials.append(name.charAt(0));
        }

        return initials.toString().toLowerCase() + "." + lastName.toLowerCase() + maternalSurname.toLowerCase() + "@ugto.mx";
    }

}
