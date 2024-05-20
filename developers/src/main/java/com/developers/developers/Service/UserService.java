package com.developers.developers.Service;

import com.developers.developers.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity save(UserEntity userEntity);
}
