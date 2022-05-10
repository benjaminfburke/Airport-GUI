import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Alaska implements Airline {
    private int numPassengers;
    private ArrayList<String> passengers;

    public static void main(String[] args) throws IOException {
        Alaska alaska = new Alaska();
        alaska.readPassengers();
        System.out.println(alaska.displayFlight());
        alaska.addPassenger(new Passenger("Ben", "Burke", "19"));
        alaska.readPassengers();
        System.out.println(alaska.displayFlight());
    }

    public Alaska() {
        numPassengers = 0;
        passengers = new ArrayList<>();
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public String displayFlight() {
        String output = numPassengers + " : 100\n";
        for (int i = 0; i < passengers.size(); i++) {
            output += passengers.get(i) + "\n";
        }
        return output;
    }

    public void readPassengers() throws FileNotFoundException {
        passengers.clear();
        numPassengers = 0;
        File file = new File("reservations.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean tracker = false;

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.equals("DELTA"))
                tracker = true;


            if (line.contains(",") && !tracker) {
                passengers.add(line);
                numPassengers++;
            }
        }
    }

    public ArrayList<String> getPassengers() {
        return passengers;
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
            if (line.equals("Alaska passenger list"))
                counter = lineCount;

        }

        fileLines.set(counter - 2, (Integer.parseInt(fileLines.get(counter - 2).substring(0, fileLines.get(counter - 2).indexOf('/'))) + 1) + "/100");
        fileLines.add(counter, temp);
        fileLines.add(counter + 1, "---------------------ALASKA");
        System.out.println(fileLines);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < fileLines.size(); i++) {
            bw.write(fileLines.get(i) + "\n");
        }
        bw.close();
    }



    public boolean checkIfFull() {
        if (numPassengers < 100) {
            return false;
        } else {
            return true;
        }
    }

    public String flightDescription() {
        return "Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue Univeristy.\n" +
                "We primarily fly westward, and often have stops in Alaska and California.\n" +
                "We have first class amenities, even in coach class.\n" +
                "We provide fun snacks, such as pretzels and goldfish.\n" +
                "We also have comfortable seats, and free WiFi." +
                "We hope you choose Alaska Airlines for your next itinerary!";

    }

    public String getAirline() {
        return "Alaska Airlines";
    }
}