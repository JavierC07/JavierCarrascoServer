package es.uma.ingweb.coffeecar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeographicalCoordinates {
    private final int EARTH_RADIUS = 6371;

    private float longitude;
    private float latitude;

    public double distanceTo(GeographicalCoordinates origin){
        double latDistance = Math.toRadians(origin.latitude - latitude);
        double lonDistance = Math.toRadians(origin.longitude - longitude);

        // formula haversine ni idea de que es a y c :) https://en.wikipedia.org/wiki/Haversine_formula
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
              + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(origin.latitude))
              * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
