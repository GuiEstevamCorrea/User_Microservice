package com.gc.microservices.service;

import com.gc.microservices.models.UserModel;
import com.gc.microservices.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Transactional
    public UserModel save(UserModel userModel){
        return repository.save(userModel);
    }

}
