package es.uma.ingweb.coffeecar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusHierarchy {
    @JsonProperty("result")
    private BusInfoResponse result;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BusInfoResponse {
        @JsonProperty("records")
        private List<BusData> busesData;
        @JsonIgnoreProperties(ignoreUnknown = true)
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BusData {
            private int _id;
            private int codBus;
            private float codLinea;
            @JsonProperty("lat")
            private float currLat;
            @JsonProperty("lon")
            private float currLon;
        }
    }
}
