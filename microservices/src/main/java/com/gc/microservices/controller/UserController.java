package com.gc.microservices.controller;

import com.gc.microservices.dto.UserRecordDTO;
import com.gc.microservices.models.UserModel;
import com.gc.microservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> users = this.service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId")UUID userId){

        Optional<UserModel> userModelOptional = service.findById(userId);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not  found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId")UUID userId){

        Optional<UserModel> userModelOptional = service.findById(userId);

        if (userModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not  found!");
        }
        service.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }
}
