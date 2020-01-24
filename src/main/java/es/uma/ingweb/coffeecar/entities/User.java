package es.uma.ingweb.coffeecar.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Announce> ownedAnnounces;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Announce> joinedAnnounces;
}