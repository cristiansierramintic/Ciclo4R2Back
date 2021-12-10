package com.ciclo4.reto2.service;

import com.ciclo4.reto2.model.User;
import com.ciclo4.reto2.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id){
        return userRepository.getUser(id);
    }

    public User save(User user){
        if(user.getId() == null) {
            return user;
        }else{
            Optional<User> userAux = userRepository.getUser(user.getId());
            if(userAux.isEmpty()){
                if(emailExists(user.getEmail()) == false){
                    return userRepository.save(user);
                }else{
                    return user;
                }
            }else{
                return user;
            }
        }
    }

    public User update(User user) {
        if(user.getId() != null) {
            Optional<User> userAux = userRepository.getUser(user.getId());
            if (!userAux.isEmpty()) {
                if (user.getIdentification() != null) {
                    userAux.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userAux.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userAux.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userAux.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userAux.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userAux.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userAux.get().setZone(user.getZone());
                }
                userRepository.save(userAux.get());
                return userAux.get();
            } else {
                return user;
            }
        }else{
            return user;
        }
    }

    public boolean delete(int userId) {
        Boolean userBoolean = getUser(userId).map(user ->{
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }

    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    public User authenticateUser (String email, String password) {
        Optional<User> user = userRepository.authenticateUser(email, password);
        if(user.isEmpty()){
            return new User();
        } else{
            return user.get();
        }
    }
}
