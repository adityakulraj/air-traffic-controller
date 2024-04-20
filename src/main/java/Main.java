import model.FlightRequest;
import model.PlaneType;
import service.ATC;

public class Main {

    public static void main(String [] args) throws InterruptedException {

        ATC atc = new ATC();


        atc.requestFromFlight("AI", "AI101", PlaneType.A320, FlightRequest.RequestType.LAND);
        atc.requestFromFlight("IG", "IG40", PlaneType.A320, FlightRequest.RequestType.LAND);
        Thread.sleep(3000);
        atc.requestFromFlight("IG", "IG42", PlaneType.A320, FlightRequest.RequestType.LAND);
        atc.requestFromFlight("IG", "IG43", PlaneType.A320, FlightRequest.RequestType.LAND);
        atc.requestFromFlight("IG", "IG44", PlaneType.A320, FlightRequest.RequestType.LAND);
    }
}
