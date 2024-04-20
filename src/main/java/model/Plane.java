package model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Plane {

    private final String planeId;

    private final String callSign;

    private final PlaneType planeType;


    public void notify(String msg) {
        System.out.println(msg);

    }


}
