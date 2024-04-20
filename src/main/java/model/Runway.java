package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Runway {

    private final String runwayId;
    private final String runwayName;

    private boolean isFree;


    public Runway(String runwayId, String runwayName) {
        this.runwayId = runwayId;
        this.runwayName = runwayName;
        this.isFree = true;
    }


    public void isFree(boolean b) {
        this.isFree = b;
    }
}
