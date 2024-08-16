package pack2.Interface;
import java.util.Scanner;
public class Main
{
	 static Scanner sc = new Scanner(System.in);
	    public static void main(String[] args) {

	        while (true) {
	            System.out.println("Select vehicle type:");
	            System.out.println("1. Cars");
	            System.out.println("2. Bike");
	            System.out.println("3. Electric Car");
	            System.out.println("4. Electric Bike");
	            System.out.println("5. Exit");

	            int choice = sc.nextInt();

	            Vechicle vechicle = null;

	            switch (choice) {
	                case 1:
	                    vechicle = new Cars();
	                    break;
	                case 2:
	                    vechicle = new Bike();
	                    break;
	                case 3:
	                    vechicle = new Ev_Car();
	                    break;
	                case 4:
	                    vechicle = new Ev_Bike();
	                    break;
	                case 5:
	                    System.out.println("Exiting");
	                    sc.close();
	                    return;
	                default:
	                    System.out.println("Invalid");
	                    continue;
	            }
	            

	            if (vechicle != null) {
	                System.out.println("Name of Vehicle: " + vechicle.Name_of_vehicle());
	                System.out.println("Model Name: " + vechicle.Model_name());
	                System.out.println("Seating Capacity: " + vechicle.Seating_capacity());
	                System.out.println("Color: " + vechicle.color());
	                System.out.println("Type: " + vechicle.type());
	               System.out.println("Mileage:"+vechicle.mileage());
	                System.out.println("Year: " + vechicle.year());
	                System.out.println();
	            }
	        }
	    }
}
