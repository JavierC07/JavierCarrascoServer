package es.uma.ingweb.coffeecar.repositories;

import es.uma.ingweb.coffeecar.entities.Announce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnounceRepository extends MongoRepository<Announce, String> {
    Announce findAnnouncesById(String id);
    List<Announce> findAnnouncesByArrivalOrderByDepartureTime(String arrival);
    List<Announce> findAnnouncesByDriverIdOrderByDepartureTime(String id);
    List<Announce> findAnnouncesByDriverIsNotOrderByDepartureTime(String id);
}
