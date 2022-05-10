import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Southwest implements Airline {

    private int numPassengers;
    private ArrayList<String> passengers;


    public Southwest() {
        numPassengers = 0;
        passengers = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        Southwest southwest = new Southwest();
        southwest.readPassengers();
        System.out.println(southwest.displayFlight());
        southwest.addPassenger(new Passenger("Will", "Gerber", "19"));
        southwest.readPassengers();
        System.out.println(southwest.displayFlight());

    }

    public boolean checkIfFull() {
        if (numPassengers < 100) {
            return false;
        } else {
            return true;
        }
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
            if (line.equals("Southwest passenger list"))
                counter = lineCount;

        }

        fileLines.set(counter - 2, (Integer.parseInt(fileLines.get(counter - 2).substring(0, fileLines.get(counter - 2).indexOf('/'))) + 1) + "/100");
        fileLines.add(counter, temp);
        fileLines.add(counter + 1, "---------------------SOUTHWEST");
        System.out.println(fileLines);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < fileLines.size(); i++) {
            bw.write(fileLines.get(i) + "\n");
        }
        bw.close();
    }

    public String flightDescription() {
        return "Southwest Airlines is proud to offer flights to Purdue University.\n" +
                "We are happy to offer in flight wifi, as well as our amazing snacks.\n" +
                "In addition, we offer flights for much cheaper than other airlines, and offer two free checked bags.\n" +
                "We hope you choose Southwest for your next flight.";

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

            if (line.equals("SOUTHWEST")) {
                flag = true;
            }

            if (flag) {
                if (line.contains(",")) {
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
    public int getNumPassengers() {
        return numPassengers;
    }
    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public String getAirline() {
        return "Southwest Airlines";
    }
}
