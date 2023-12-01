package com.gc.microservices.controller;

import com.gc.microservices.dto.UserRecordDTO;
import com.gc.microservices.models.UserModel;
import com.gc.microservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    /**
    * @author: Guilherme Correa
    * @to-do:
    * - Implementar o resto do CRUD
    * - Implementar validações
    */

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDTO userRecordDTO){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDTO, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userModel));
    }
}
