package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class RunwayAllocation {

    private final String allocationId;
    private final Runway runway;
    private final Plane plane;
    private final Timestamp allocatedTimestamp;
}
