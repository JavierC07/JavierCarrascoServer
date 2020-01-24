package es.uma.ingweb.coffeecar.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;


@Data
public class Announce {
    @Id
    private String id;
    private String title;
    private String departureTime;
    private String arrivalDate;
    private String description;
    private String arrival;
    private double arrivalLatitude;
    private double arrivalLongitude;
    private double departureLatitude;
    private double departureLongitude;
    private String imgLink;
    private int seats;
    @DBRef
    private User driver;
    @DBRef
    private List<User> passengers;
}
