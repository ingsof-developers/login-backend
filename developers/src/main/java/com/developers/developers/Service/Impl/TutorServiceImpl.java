package com.developers.developers.Service.Impl;

import com.developers.developers.Repository.RoleRepository;
import com.developers.developers.Repository.TutorRepository;
import com.developers.developers.Repository.UserRepository;
import com.developers.developers.Service.TutorService;
import com.developers.developers.jwt.JwtService;
import com.developers.developers.model.entity.AuthResponse;
import com.developers.developers.model.entity.TutorRegisterRequest;
import com.developers.developers.model.entity.Tutores;
import com.developers.developers.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Collections;
import java.util.List;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

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
                .password(tutorRegisterRequest.getPassword())
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
        maternalSurname = removeAccents(maternalSurname);

        return initials.toString().toLowerCase() + "." + lastName.toLowerCase() + maternalSurname.toLowerCase() + "@ugto.mx";
    }

    private String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
