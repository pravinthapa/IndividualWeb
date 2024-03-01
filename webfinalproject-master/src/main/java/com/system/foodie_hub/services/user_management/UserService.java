package com.system.foodie_hub.services.user_management;

import com.system.foodie_hub.dto.AuthenticationResponse;
import com.system.foodie_hub.dto.LoginDto;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;



public interface UserService {
    UserPojo save(UserPojo userPojo) throws IOException;

    List<User> fetchAll();

    User fetchById(Integer id);

    void deleteById(Integer id);

    void sendEmail();

    User fatchByEmail(String email);

    AuthenticationResponse authenticate(LoginDto loginDto);

    void update(UserPojo userPojo) throws IOException;
}