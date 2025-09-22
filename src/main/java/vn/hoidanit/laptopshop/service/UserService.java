package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
 

    public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

    @GetMapping("")
    public String handleHello(){
        return "hello from service";
    }

    public List<User> getAllUsersByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(long id){
        return this.userRepository.findById(id);
    }

    public User hanldleSaveUser(User user){
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public void deleteUser(long id){
        this.userRepository.deleteById(id);
    }


}
