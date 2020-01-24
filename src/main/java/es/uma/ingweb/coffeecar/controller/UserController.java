package es.uma.ingweb.coffeecar.controller;

import es.uma.ingweb.coffeecar.entities.User;
import es.uma.ingweb.coffeecar.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userRepository.findById(id)
            .ifPresentOrElse(
                userRepository::delete,
                () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announce not found");}
            );
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ":("));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/search/findByEmail")
    public User findByEmail(@RequestParam String email) {
        return userRepository.findUserByEmail(email);
    }

    @GetMapping("/search/findByName")
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findUsersByName(name);
    }
}
