package com.pluralsight;

import com.pluralsight.dealership.DealershipDataManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MainApp {

        public static void logs(String[] args) {
            if (args.length != 2) {
                System.out.println(
                        "Application needs two arguments to run: " +
                                "java com.hca.jdbc.UsingDriverManager <username> " +
                                "<password>");
                System.exit(1);
            }

            // Get the username and password
            String username = args[0];
            String password = args[1];

            DealershipDataManager dataManager = new DealershipDataManager(username, password);
            userScreen(dataManager);

        }
    public static void userScreen(DealershipDataManager dataManager) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("00 to cancel or Please enter Vehicle model or VIN to search: ");
            String vehicleDescri = scanner.nextLine();
            if (vehicleDescri.equals("00")) break;

            List<Vehicle> matchedVehicle = dataManager.getVehicleByVin(vehicleDescri.trim());
            matchedVehicle.stream().forEach(vehicle -> {System.out.println(vehicle);});

            System.out.print("00 to cancel or, search for contracts by VIN: ");
            String vechicleLookup = scanner.nextLine().trim();
            if (vechicleLookup.equals("00")) break;

            List<Contract> matchedContract = dataManager.getContractByVin(vehicleDescri.trim());
            matchedContract.stream().forEach(contract -> {System.out.println(contract);});
        }
        scanner.close();
    }
        public static void main(String[] args) {
        ArrayList<Vehicle> showRoom = getShowRoom();
        ArrayList<Vehicle> inquiry = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int option = 0;

        while(true) {
            try {
                System.out.println("Select an option by typing the number:\n" +
                        "1.Display ShowRoom\n" +
                        "2.Display Purchases\n" +
                        "3.Exit");
                option = input.nextInt();

                switch (option) {

                    case 1:
                        displayVehicles(showRoom);
                        System.out.println("Select an option by number:\n" +
                                "(1) Add vehicle\n" +
                                "(2) Search for a product by model\n" +
                                "(3) Search for products by a price range\n" +
                                "(4) Search for products by their make\n" +
                                "(5) Go back to the home page");
                        int option1 = input.nextInt();
                        switch (option1) {
                            case 1:
                                addToPurchases(showRoom, inquiry);
                                break;
                            case 2:
                                searchByModel(showRoom);
                                break;
                            case 3:
                                searchByPrice(showRoom);
                                break;
                            case 4:
                                searchByMake(showRoom);
                                break;
                            case 5:
                                continue;
                            default:
                                System.out.println("Error. Invalid input.");
                                break;
                        }
                        break;

                    case 2:
                        System.out.println(displayPurchases(inquiry));
                        System.out.println("Select an option by typing the number:\n" +
                                "1.Checkout\n" +
                                "2.Remove from inquiry\n" +
                                "3.Go back to home screen");
                        int selection2 = input.nextInt();
                        switch (selection2) {
                            case 1:
                                checkOut(inquiry);
                                break;
                            case 2:
                                removeFromPurchases(inquiry);
                                break;
                            case 3:
                                continue;
                            default:
                                System.out.println("Error. Invalid input.");
                                break;
                        }

                        break;

                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Error. Invalid input.");
                        break;

                }

            } catch (Exception e){
                System.out.println("Invalid input");
                input.nextLine();
                e.printStackTrace();
            }
        }

    }
    public static ArrayList<Vehicle> getShowRoom() {
        ArrayList<Vehicle> showRoom = new ArrayList<Vehicle>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input ;
            bufferedReader.readLine();
            //Skipping the first line with the column headings
            while ((input = bufferedReader.readLine()) != null) {
                if (input.startsWith("Audi of DFW")){
                String[] description = input.split("|");
                Vehicle newVehicle = new Vehicle((description[0]),
                        (description[1]),
                        description[2],
                        (description[3]),
                        (description[4]),
                        (description[5]),
                        Double.parseDouble(description[6]));
                showRoom.add(newVehicle);
                }
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        return showRoom;
    }
    public static void displayVehicles(ArrayList<Vehicle> inventory){
        System.out.println("Vehicles for sale:");
        for( Vehicle sR : inventory){
            System.out.println(sR);
        }
    }
    public static void searchByModel(ArrayList<Vehicle> showRoom){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Vehicle Model: ");
        String vehicleModel = input.nextLine().trim();
        System.out.println("Models found:");
        for( Vehicle sR : showRoom){
            if( sR.getModel().equalsIgnoreCase(vehicleModel)){
                System.out.println(sR);
            }
        }
    }
    public static void searchByPrice(ArrayList<Vehicle> inventory){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the minimum price:");
        double lowerRange = input.nextDouble();
        System.out.print("Enter the maximum price:");
        double upperRange = input.nextDouble();
        System.out.println("Vehicles found:");
        for (Vehicle sR : inventory) {
            if (sR.getPrice() >= lowerRange && sR.getPrice() <= upperRange)
                System.out.println(sR.toString());
        }

    }
    public static void searchByMake(ArrayList<Vehicle> showRoom){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Make from the list below:");
        System.out.println("Audi\n");
        String make = input.nextLine().trim();
        for (Vehicle sR : showRoom){
            if( make.equalsIgnoreCase(sR.getMake())){
                System.out.println(sR);
            }
        }

    }

    public static void addToPurchases(ArrayList<Vehicle> showRoom, ArrayList<Vehicle> purchase){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of an item to add it to your purchase: ");
        String vehicle = input.nextLine().trim();
        for (Vehicle sR : showRoom){
            if( sR.getMake().equalsIgnoreCase(vehicle)){
                purchase.add(sR);
                System.out.println("Vehicle added successfully");
            }
        }
        if (purchase.isEmpty()){
            System.out.println("Error. Nothing added to cart.");
        }

    }
    public static String displayPurchases (ArrayList<Vehicle> purchases){
        StringBuilder output = new StringBuilder("Vehicles in cart:\n");
        HashMap<Vehicle,Integer> items = new HashMap<>();
        for( Vehicle sR : purchases){
            items.put(sR, items.getOrDefault(sR,0)+1);//Frequency counting of vehicles in the cart
        }
        for(Vehicle sR : items.keySet()){
            output.append(sR.getMake())
                    .append(" Amount: ")
                    .append(items.get(sR))
                    .append(String.format("   Cost: $%.2f\n",sR.getPrice() * items.get(sR)));
        }
        return output.toString();
    }
    public static void Contract(ArrayList<Vehicle> purchases){

    }

    public static void checkOut(ArrayList<Vehicle> purchases){
        Scanner option2 = new Scanner(System.in);
        if (!purchases.isEmpty()) {
            System.out.println(displayPurchases(purchases));
            double vehicleCost = 0;
            for (Vehicle sR : purchases) {
                vehicleCost += sR.getPrice();
            }
            System.out.printf("Total cost: $%.2f\n", vehicleCost);
            System.out.print("Enter the payment: ");
            double payment = option2.nextDouble();
            if (payment >= vehicleCost) {
                printReceipt(payment, vehicleCost,purchases);
                System.out.println("Checked out successfully!");
                purchases.clear();
                if (payment > vehicleCost) {
                    System.out.printf("Your change: %.2f\n", (payment - vehicleCost));
                }
            } else {
                System.out.println("Insufficient payment amount\nUnsuccessful checkout");
            }
        }
        else
            System.out.println("Nothing in the cart");
    }
    public static void removeFromPurchases (ArrayList<Vehicle> purchases){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the vehicle you want to remove");
        String vehicleName = input.nextLine();
        Vehicle vehicleToRemove = null;
        for (Vehicle sR : purchases){
            if ( sR.getMake().equalsIgnoreCase(vehicleName)){
                vehicleToRemove = sR;
                break;
            }
        }
        purchases.remove(vehicleToRemove);
    }
    public static void printReceipt(double payment, double cost, ArrayList<Vehicle> purchases){
        System.out.println("Date: "+ LocalDate.now());
        System.out.println(displayPurchases(purchases));
        System.out.printf("Sales total: $%.2f\n",cost);
        System.out.printf("Amount paid: $%.2f\n",payment);
        System.out.printf("Change given: $%.2f\n",payment-cost);
        System.out.println("Purchase Agreement:\n");

        try{
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            FileWriter fileWriter = new FileWriter("Receipts/"+now.format(df)+".txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Date: "+ LocalDate.now()+"\n");
            bufferedWriter.write(displayPurchases(purchases));
            bufferedWriter.write(String.format("Sales total: $%.2f\n",cost));
            bufferedWriter.write(String.format("Amount paid: $%.2f\n",payment));
            bufferedWriter.write(String.format("Change given: $%.2f\n",payment-cost));

            bufferedWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
