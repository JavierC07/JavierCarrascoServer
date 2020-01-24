package es.uma.ingweb.coffeecar.controller;


import es.uma.ingweb.coffeecar.entities.Comment;
import es.uma.ingweb.coffeecar.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{id}")
    public List<Comment> getCommentFromAnnounce(@PathVariable String id){
        return commentRepository.findCommentByAnnounceOrderByDate(id);
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        commentRepository.findById(id)
                .ifPresentOrElse(
                        commentRepository::delete,
                        () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");}
                );
    }

}
