package flightmanage;

import flightmanage.view.FlightView;

import java.util.Scanner;

/**
 * @ author ps_zhao
 * @ description
 */
public class FlightManager {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        FlightView flightView = new FlightView();
        boolean flag = true;
        while(flag){
            flightView.menu();
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    flightView.showFlights();
                    break;
                case 2:
                    flightView.queryFlightByDeparture();
                    break;
                case 3:
                    flightView.queryFlightByDestination();
                    break;
                case 4:
                    flightView.deleteFlight();
                    break;
                case 5:
                    flightView.updateFlight();
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
                    break;
            }
        }
    }
}
