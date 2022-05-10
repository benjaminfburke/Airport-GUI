import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Delta implements Airline {

    private int numPassengers;
    private ArrayList<String> passengers;
    private String gate;

    public Delta() {
        numPassengers = 0;
        passengers = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Delta delta = new Delta();
        delta.readPassengers();
        System.out.println(delta.displayFlight());
        delta.addPassenger(new Passenger("Ben", "Burke", "19"));
        delta.readPassengers();
        System.out.println(delta.displayFlight());
    }

    public void readPassengers() throws FileNotFoundException {
        passengers.clear();
        numPassengers = 0;
        File file = new File("reservations.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean tracker = false;
        boolean flag = false;
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.equals("DELTA")) {
                flag = true;
            }

            if (line.equals("SOUTHWEST"))
                tracker = true;


            if (flag) {
                if (line.contains(",") && !tracker) {
                    passengers.add(line);
                    numPassengers++;
                }

            }
        }
    }

    public String displayFlight() {

        String output = numPassengers + " : 100\n";
        for (int i = 0; i < passengers.size(); i++) {
            output += passengers.get(i) + "\n";
        }
        return output;
    }

    public void addPassenger(Passenger p) throws IOException {
        numPassengers = 0;
        String first = p.getFirstName();
        String last = p.getLastName();
        String age = p.getAge();
        String temp = first.charAt(0) + ". " + last + ", " + age;

        File file = new File("reservations.txt");
        //File file2 = new File("testReservations.txt");

        Scanner sc = new Scanner(file);
        String line = "";
        int lineCount = 0;
        int counter = 0;
        ArrayList<String> fileLines = new ArrayList<>();

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            lineCount++;
            fileLines.add(line);
            if (line.equals("Delta passenger list"))
                counter = lineCount;

        }
        fileLines.set(counter - 2, (Integer.parseInt(fileLines.get(counter - 2).substring(0, fileLines.get(counter - 2).indexOf('/'))) + 1) + "/100");
        fileLines.add(counter, temp);
        fileLines.add(counter + 1, "---------------------DELTA");
        System.out.println(fileLines);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < fileLines.size(); i++) {
            bw.write(fileLines.get(i) + "\n");
        }
        bw.close();
    }



    public int getNumPassengers() {
        return numPassengers;
    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }
    public String flightDescription() {
        return "Delta Airlines is proud to be one of the five premier Airlines at Purdue University.\n" +
                "We are extremely proud to offer exceptional services, with free limited WiFi for all customers.\n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits.\n" +
                "We are also happy to offer power outlets in each seat for passenger use.\n" +
                "We hope you chose to fly Delta as-your next Airline.";

    }
    public boolean checkIfFull() {
        if (numPassengers < 100) {
            return false;
        } else {
            return true;
        }
    }

    public String getAirline() {
        return "Delta Airlines";
    }
}