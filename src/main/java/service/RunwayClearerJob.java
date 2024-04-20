package service;

/*this assumes that a landing or takeoff takes 2 mins. And after 2 mins,
the runway is made free and available
for all other planes to land/takeoff.
*/

import model.FlightRequest;
import model.Runway;
import model.RunwayAllocation;
import model.RunwayQueue;

import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class RunwayClearerJob implements Runnable {


    RunwayQueue runwayQueue;

    List<Runway> runwayList;

    private LinkedBlockingQueue<RunwayAllocation> runwayAllocationQueue;



    public RunwayClearerJob(List<Runway> runwayList, LinkedBlockingQueue<RunwayAllocation> runwayAllocationQueue) {
        this.runwayList = runwayList;
        this.runwayAllocationQueue = runwayAllocationQueue;
    }


    @Override
    public void run() {

        while(true) {
            if(!runwayAllocationQueue.isEmpty()) {

                RunwayAllocation ra = runwayAllocationQueue.peek();
                if(System.currentTimeMillis() - ra.getAllocatedTimestamp().getTime()>2000) {
                    ra.getRunway().isFree(true);
                    runwayAllocationQueue.poll();
                    System.out.println("Runway " +ra.getRunway().getRunwayName() + " is now free for usage");
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
