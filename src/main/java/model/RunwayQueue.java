package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.LinkedBlockingQueue;


@Getter
public class RunwayQueue {


    private LinkedBlockingQueue<FlightRequest> flightRequestQueue;


    public RunwayQueue() {
        flightRequestQueue = new LinkedBlockingQueue<>();
    }

    public void addRequest(FlightRequest fr) {
        flightRequestQueue.add(fr);
    }




}
