package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.RoleRepository;
import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.Repository.UserRepository;
import com.developers.developers.Service.TutorService;
import com.developers.developers.jwt.JwtService;
import com.developers.developers.model.entity.dto.AuthResponse;
import com.developers.developers.model.entity.dto.TutorRegisterRequest;
import com.developers.developers.model.entity.Tutores;
import com.developers.developers.model.entity.UserEntity;
import com.developers.developers.model.entity.dto.TutorUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {
    @Override
    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public AuthResponse update(Long id, TutorUpdateRequest tutorUpdateRequest) {
        Tutores tutor = tutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor not found"));
        UserEntity user = userRepository.findById(tutor.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));

        String email = UsernameToEmail(tutorUpdateRequest.getUsername(), tutorUpdateRequest.getLastName(), tutorUpdateRequest.getMaternalSurname());
        String fullName = tutorUpdateRequest.getUsername() + " " + tutorUpdateRequest.getLastName() + " " + tutorUpdateRequest.getMaternalSurname();

        user.setUsername(tutorUpdateRequest.getUsername());
        user.setLastName(tutorUpdateRequest.getLastName());
        user.setMaternalSurname(tutorUpdateRequest.getMaternalSurname());
        user.setPassword(passwordEncoder.encode(tutorUpdateRequest.getPassword()));
        user.setEmail(email);

        userRepository.save(user);

        tutor.setName(fullName);
        tutor.setTelefono(tutorUpdateRequest.getTelefono());
        tutor.setCorreo(email);

        tutorRepository.save(tutor);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public <Optional> Tutores findById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tutores> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public AuthResponse save(TutorRegisterRequest tutorRegisterRequest) {
        String email = UsernameToEmail(tutorRegisterRequest.getUsername(), tutorRegisterRequest.getLastName(), tutorRegisterRequest.getMaternalSurname());
        String fullName = tutorRegisterRequest.getUsername() + " " + tutorRegisterRequest.getLastName() + " " + tutorRegisterRequest.getMaternalSurname();

        UserEntity userEntity = UserEntity.builder()
                .username(tutorRegisterRequest.getUsername())
                .lastName(tutorRegisterRequest.getLastName())
                .maternalSurname(tutorRegisterRequest.getMaternalSurname())
                .password(passwordEncoder.encode(tutorRegisterRequest.getPassword())    )
                .email(email)
                .authorities(Collections.singletonList(roleRepository.findByName("ROLE_TUTOR"))).build();

        UserEntity savedUserEntity = userRepository.save(userEntity);

        Tutores tutor = Tutores.builder()
                .name(fullName)
                .telefono(tutorRegisterRequest.getTelefono())
                .correo(email)
                .user(savedUserEntity)
                .departamentoId(tutorRegisterRequest.getDepartamentoId())
                .build();

        Tutores savedTutor = tutorRepository.save(tutor);

        return AuthResponse.builder()
                .token(jwtService.getToken(savedUserEntity))
                .build();
    }

    public String UsernameToEmail(String username, String lastName, String maternalSurname) {
        username = removeAccents(username);
        String[] names = username.split(" ");
        StringBuilder initials = new StringBuilder();

        for (String name : names) {
            initials.append(name.charAt(0));
        }

        lastName = removeAccents(lastName);
        lastName.split(" ");
        maternalSurname = removeAccents(maternalSurname);
        maternalSurname.split(" ");

        return initials.toString().toLowerCase() + "." + lastName.toLowerCase() + maternalSurname.toLowerCase() + "@ugto.mx";
    }

    private String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

}
