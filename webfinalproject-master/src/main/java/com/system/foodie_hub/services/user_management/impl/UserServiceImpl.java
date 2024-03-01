package com.system.foodie_hub.services.user_management.impl;

import com.system.foodie_hub.config.PasswordEncoderUtil;
import com.system.foodie_hub.dto.AuthenticationResponse;
import com.system.foodie_hub.dto.LoginDto;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.repo.user_management.UserRepo;
import com.system.foodie_hub.services.user_management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final String UPLOAD_DIRECTORY=System.getProperty("user.dir")+"/photo_file";

    //    private final String UPLOAD_DIRECTORY=System.getProperty("user.dir")+"/photo_file";
    @Override
    public UserPojo save(UserPojo userPojo) throws IOException {
        User user= new User();
        user.setId(userPojo.getId());
        user.setEmail(userPojo.getEmail());
        user.setFullName(userPojo.getFullname());
//        user.setMobileNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));


        user= userRepo.save(user);
        return new UserPojo(user);
    }

    @Override
    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    @Override
    public User fetchById(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public void sendEmail() {

    }

    @Override
    public User fatchByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public AuthenticationResponse authenticate(LoginDto loginDto) {
        return null;
    }

    @Override
    public void update(UserPojo userPojo) throws IOException {
        User user= userRepo.findById(userPojo.getId()).get();
        user.setId(userPojo.getId());
        user.setEmail(userPojo.getEmail());
        user.setFullName(userPojo.getFullname());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));

        if(userPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getImage().getOriginalFilename());
            fileNames.append(userPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getImage().getBytes());

            user.setImage(userPojo.getImage().getOriginalFilename());
        }

        userRepo.save(user);
    }
}
