# Low Level Design of an Air Traffic Controller


## Requirements 

1. There is a Air Traffic Controller at an airport that is responsible for giving the go ahead to the planes to take off/land.
2. It also allocates the fixed no. of runways at the airport to the planes it clears for take off/landing.
3. It makes sure that a runway is not allocated twice at the same time, as this would be fatal to the crew/passengers.
4. If there are no runways free, you have to make the flight wait till a runway is available.
5. You can asssume that each landing/takeoff takes 2 mins, and after that a runway is available for further use.



## Design

We expose a single API from the ATC perspective which is requestFromFlight(). This takes in the flightNo, the callsign, plane type ( A320, A380 ...) and the requestType( LAND/TAKEOFF)

Now we add it to a queue of requests and return a confirmation to the flight that it's request was taken a not of.

We work as a pub/sub model where the requests are added to the queue and a consumer job separately processes the requests and clears the planes to takeoff/land and assigns them runways. 

We also run another scheduled job every second which clears all the runways after 2 seconds for usage. 

For our pub/sub queue we use a LinkedBlockingQueue as it is thread safe, can be read and inserted in giving consistency when multiple threads are involved.
