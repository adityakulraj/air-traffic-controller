package service;

import model.FlightRequest;
import model.Runway;
import model.RunwayAllocation;
import model.RunwayQueue;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class RunwayAllocationJob implements Runnable {

    RunwayQueue runwayQueue;

    List<Runway> availableRunways;

    LinkedBlockingQueue<RunwayAllocation> runwayAllocationQueue;

    public RunwayAllocationJob(RunwayQueue runwayQueue, List<Runway> availableRunways,   LinkedBlockingQueue<RunwayAllocation> runwayAllocationQueue) {
        this.runwayQueue = runwayQueue;
        this.availableRunways = availableRunways;
        this.runwayAllocationQueue = runwayAllocationQueue;
    }



    @Override
    public void run() {

       while(true) {
           if(!runwayQueue.getFlightRequestQueue().isEmpty()) {
               FlightRequest fr = runwayQueue.getFlightRequestQueue().peek();

               boolean freeRunways = false;

               for (Runway r : availableRunways) {
                 if(r.isFree()) {
                     r.isFree(false);
                     freeRunways = true;
                     runwayAllocationQueue.add(new RunwayAllocation(UUID.randomUUID().toString(), r, fr.getPlane(), new Timestamp(System.currentTimeMillis())));
                     fr.getPlane().notify("Flight : "+ fr.getPlane().getCallSign()+" Cleared to land on runway : " +  r.getRunwayName());
                     runwayQueue.getFlightRequestQueue().poll();
                     break;

                 }
               }

               if(!freeRunways) {
                   fr.getPlane().notify("No available runways, please wait for further instructions : " + fr.getPlane().getCallSign());
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }

           else {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }

           }


       }
    }


}
