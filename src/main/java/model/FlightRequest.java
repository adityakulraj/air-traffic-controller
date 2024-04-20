package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class FlightRequest {

    private Plane plane;
    private RequestType requestType;
    private Timestamp timestamp;

    public enum RequestType {
        LAND,
        TAKEOFF;
    }
}
