package es.uma.ingweb.coffeecar.repositories;

import es.uma.ingweb.coffeecar.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String email);
    List<User> findUsersByName(String name);
}