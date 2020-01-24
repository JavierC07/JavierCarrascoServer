package es.uma.ingweb.coffeecar.repositories;

import es.uma.ingweb.coffeecar.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentByAnnounceOrderByDate(String idAnnounce);
}
