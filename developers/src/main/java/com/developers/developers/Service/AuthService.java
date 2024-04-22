package com.developers.developers.Service;

import com.developers.developers.Repository.RoleRepository;
import com.developers.developers.Repository.UserRepository;
import com.developers.developers.jwt.JwtService;
import com.developers.developers.model.entity.AuthResponse;
import com.developers.developers.model.entity.LoginRequest;
import com.developers.developers.model.entity.RegisterRequest;
import com.developers.developers.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
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
        UserEntity userEntity = UserEntity.builder()
                .username(registerRequest.getUsername())
                .last_Name(registerRequest.getLast_Name())
                .maternal_Surname(registerRequest.getMaternal_Surname())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .authorities(Collections.singletonList(roleRepository.findByName("USER"))).build();

        userRepository.save(userEntity);

        return AuthResponse.builder()
                .token(jwtService.getToken(userEntity))
                .build();
    }

}
