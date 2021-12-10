package com.ciclo4.reto2.controller;

import com.ciclo4.reto2.model.User;
import com.ciclo4.reto2.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id) {
        return (Optional<User>) userService.getUser(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return userService.delete(id);
    }

    @GetMapping("/{email}/{password}")
    public User authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password){
        return userService.authenticateUser(email,password);
    }

    @GetMapping("/emailexist/{email}")
    public boolean emailExists (@PathVariable("email") String email){
        return userService.emailExists(email);
    }    
}
