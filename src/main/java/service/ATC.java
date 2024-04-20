package service;

import model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class ATC {

    private RunwayQueue runwayQueue;


    private RunwayClearerJob runwayClearerJob;

    private RunwayAllocationJob runwayAllocationJob;

    private List<Runway> runwayList = new ArrayList<>();

    private LinkedBlockingQueue<RunwayAllocation> runwayAllocationQueue;


    public ATC() {
        runwayQueue = new RunwayQueue();
        runwayAllocationQueue = new LinkedBlockingQueue<>();

        runwayList.add(new Runway("1", "r1"));
        runwayList.add(new Runway("2", "r2"));

        runwayAllocationJob = new RunwayAllocationJob(runwayQueue, runwayList, runwayAllocationQueue);

        runwayClearerJob = new RunwayClearerJob(runwayList, runwayAllocationQueue);

        new Thread(runwayAllocationJob).start();
        new Thread(runwayClearerJob).start();


    }


    public String requestFromFlight(String flightNo, String callSign, PlaneType planeType, FlightRequest.RequestType requestType) {

        Plane p = new Plane(flightNo, callSign, planeType);
        FlightRequest fr = new FlightRequest(p, requestType, new Timestamp(System.currentTimeMillis()));

        runwayQueue.addRequest(fr);

        return "Request Accepted, wait for response, Roger Flight "+ flightNo;

    }


}
