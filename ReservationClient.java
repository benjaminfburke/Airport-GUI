import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;

public class ReservationClient {

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        final Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        String request;
        String response;
        Delta delta = new Delta();
        Alaska alaska = new Alaska();
        Southwest southwest = new Southwest();

        try {
            hostname = (String) JOptionPane.showInputDialog(null,
                    "What is the hostname you'd like to connect to?", "Hostname?", JOptionPane.QUESTION_MESSAGE);
            if (hostname == null || hostname.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank you!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            portString = (String) JOptionPane.showInputDialog(null,
                    "What is the port you'd like to connect to?", "Port?", JOptionPane.QUESTION_MESSAGE);
            if (portString == null || portString.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Thank you for using the Purdue University Airline Management System!",
                        "Thank you!", JOptionPane.INFORMATION_MESSAGE);
            }
            port = Integer.parseInt(portString);
            socket = new Socket(hostname, port);

            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ImageIcon image = new ImageIcon("PurdueLogo.png");
            Object[] buttonOptions = {"Exit", "Book a Flight"};
            int answer = JOptionPane.showOptionDialog(null,
                    "Welcome to the Purdue University Airline Reservation System!",
                    "Purdue University Flight Reservation System", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    image, buttonOptions, buttonOptions[0]);
            if (answer == 0) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            buttonOptions[1] = "Yes, I want to book a flight";
            answer = JOptionPane.showOptionDialog(null,
                    "Do you want to book a flight today?",
                    "Purdue University Flight Reservation System", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, buttonOptions, buttonOptions[0]);
            if (answer == 0) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            JFrame frame = new JFrame("Purdue University Flight Management System");
            frame.setLayout(new GridLayout(4,1));
            frame.setSize(750, 500);
            JPanel[] jp = new JPanel[4];
            for (int i = 0; i < 4; i++) {
                JPanel jPanel = new JPanel();
                frame.add(jPanel);
                jp[i] = jPanel;
            }
            jp[0].add(new JLabel("Choose a flight from the drop down menu."));

            JComboBox<String> comboBox = new JComboBox();
            comboBox.addItem("Delta Airlines");
            comboBox.addItem("Southwest Airlines");
            comboBox.addItem("Alaska Airlines");
            jp[1].add(comboBox);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(delta.flightDescription());
            jp[2].add(textArea);

            JFrame passengersDelta = new JFrame("Delta Airlines");
            passengersDelta.setLayout(new GridLayout(2, 1));
            passengersDelta.setSize(300, 100);
            JPanel[] jpd = new JPanel[2];
            for (int i = 0; i < 2; i++) {
                JPanel jPanel2 = new JPanel();
                passengersDelta.add(jPanel2);
                jpd[i] = jPanel2;
            }
            JTextArea jtd = new JTextArea();
            jtd.setEditable(false);
            jtd.setText(delta.displayFlight());
            jpd[0].add(jtd);
            JButton exit = new JButton("Exit");
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    passengersDelta.setVisible(false);
                }
            });
            jpd[1].add(exit);
            for (int i = 0; i < 2; i++) {
                passengersDelta.add(jpd[i]);
            }
            JFrame passengersSouthwest = new JFrame("Southwest Airlines");
            passengersSouthwest.setLayout(new GridLayout(2, 1));
            passengersSouthwest.setSize(300, 100);
            JPanel[] jps = new JPanel[2];
            for (int i = 0; i < 2; i++) {
                JPanel jPanel2 = new JPanel();
                passengersSouthwest.add(jPanel2);
                jps[i] = jPanel2;
            }
            JTextArea jts = new JTextArea();
            jts.setEditable(false);
            jts.setText(southwest.displayFlight());
            jps[0].add(jts);
            JButton exit2 = new JButton("Exit");
            exit2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    passengersSouthwest.setVisible(false);
                }
            });
            jps[1].add(exit2);
            for (int i = 0; i < 2; i++) {
                passengersSouthwest.add(jps[i]);
            }
            JFrame passengersAlaska = new JFrame("Alaska Airlines");
            passengersAlaska.setLayout(new GridLayout(2, 1));
            passengersAlaska.setSize(300, 100);
            JPanel[] jpa = new JPanel[2];
            for (int i = 0; i < 2; i++) {
                JPanel jPanel2 = new JPanel();
                passengersAlaska.add(jPanel2);
                jpa[i] = jPanel2;
            }
            JTextArea jta = new JTextArea();
            jta.setEditable(false);
            jta.setText(alaska.displayFlight());
            jpa[0].add(jta);
            JButton exit3 = new JButton("Exit");
            exit3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    passengersAlaska.setVisible(false);
                }
            });
            jpa[1].add(exit3);
            for (int i = 0; i < 2; i++) {
                passengersAlaska.add(jpa[i]);
            }

            comboBox.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

                        String choice = (String) comboBox.getSelectedItem();
                        if (choice.equals("Delta Airlines")) {
                            passengersDelta.setVisible(true);

                        } else if (choice.equals("Southwest Airlines")) {
                            passengersSouthwest.setVisible(true);

                        } else {
                            passengersAlaska.setVisible(true);

                        }
                    }
                }
            });
            comboBox.addItemListener(listener -> {
                textArea.setText(null);
                String choice = (String) comboBox.getSelectedItem();
                if (choice.equals("Delta Airlines")) {
                    textArea.setText(delta.flightDescription());
                } else if (choice.equals("Southwest Airlines")) {
                    textArea.setText(southwest.flightDescription());
                } else {
                    textArea.setText(alaska.flightDescription());
                }
            });
            JButton exit22 = new JButton("Exit");
            exit22.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            });
            jp[3].add(exit22);
            JButton chooseFlight = new JButton("Choose this flight");

            chooseFlight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String flightChoice = (String) comboBox.getSelectedItem();
                    if (flightChoice.equals("Delta Airlines")) {
                        if(delta.checkIfFull()) {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry, this flight is full.", "Full Flight",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    } else if (flightChoice.equals("Alaska Airlines")) {
                        if(alaska.checkIfFull()) {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry, this flight is full.", "Full Flight",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    } else {
                        if (southwest.checkIfFull()) {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry, this flight is full.", "Full Flight",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    frame.setVisible(false);
                    Object[] buttonOptions2 = {"Exit", "No, I want a different flight", "Yes, I want this flight"};
                    int confirm = JOptionPane.showOptionDialog(null,
                            "Are you sure you want to book a flight on " + flightChoice + "?",
                            "Purdue University Flight Reservation System", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, buttonOptions2, buttonOptions2[0]);
                    if (confirm == 0) {
                        JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);
                        System.exit(0);
                    } else if (confirm == 1) {
                        frame.setVisible(true);
                    } else {
                        JFrame frame2 = new JFrame("Purdue University Flight Reservation System");
                        frame2.setLayout(new GridLayout(8,1));
                        frame2.setSize(750,500);
                        JPanel[] jp2 = new JPanel[8];
                        for (int i = 0; i < 8; i++) {
                            JPanel jPanel = new JPanel();
                            frame2.add(jPanel);
                            jp2[i] = jPanel;
                        }
                        jp2[0].add(new JLabel("Please input your information below"));
                        jp2[1].add(new JLabel("What is your first name?"));
                        JTextField firstName = new JTextField(30);
                        firstName.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

                                    String choice = (String) comboBox.getSelectedItem();
                                    if (choice.equals("Delta Airlines")) {
                                        passengersDelta.setVisible(true);

                                    } else if (choice.equals("Southwest Airlines")) {
                                        passengersSouthwest.setVisible(true);

                                    } else {
                                        passengersAlaska.setVisible(true);

                                    }
                                }
                            }
                        });
                        jp2[2].add(firstName);
                        jp2[3].add(new JLabel("What is your last name?"));
                        JTextField lastName = new JTextField(30);
                        lastName.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

                                    String choice = (String) comboBox.getSelectedItem();
                                    if (choice.equals("Delta Airlines")) {
                                        passengersDelta.setVisible(true);

                                    } else if (choice.equals("Southwest Airlines")) {
                                        passengersSouthwest.setVisible(true);

                                    } else {
                                        passengersAlaska.setVisible(true);

                                    }
                                }
                            }
                        });
                        jp2[4].add(lastName);
                        jp2[5].add(new JLabel("What is your age?"));
                        JTextField age = new JTextField(30);
                        age.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {

                                    String choice = (String) comboBox.getSelectedItem();
                                    if (choice.equals("Delta Airlines")) {
                                        passengersDelta.setVisible(true);

                                    } else if (choice.equals("Southwest Airlines")) {
                                        passengersSouthwest.setVisible(true);

                                    } else {
                                        passengersAlaska.setVisible(true);

                                    }
                                }
                            }
                        });
                        jp2[6].add(age);
                        JButton exit = new JButton("Exit");
                        exit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                frame2.setVisible(false);
                                JOptionPane.showMessageDialog(null,
                                        "Thank you for using the Purdue University " +
                                                "Airline Management System!", "Thank You!",
                                        JOptionPane.PLAIN_MESSAGE);
                                System.exit(0);
                            }
                        });
                        jp2[7].add(exit);
                        JButton next = new JButton("Next");
                        next.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                boolean valid = false;
                                String fn = firstName.getText();
                                String ln = lastName.getText();
                                String a = age.getText();
                                if (fn == null || fn.equals("")) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid first name input",
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if (ln == null || ln.equals("")) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid last name input",
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                if (a == null || a.equals("")) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid age input",
                                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                for (int i = 0; i < fn.length(); i++) {
                                    char ch = fn.charAt(i);
                                    String temp = "" + ch;
                                    if (!(Character.isLetter(ch)) && !(temp.equals("-"))) {
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid first name input",
                                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }

                                }
                                for (int j = 0; j < ln.length(); j++) {
                                    char ch = ln.charAt(j);
                                    String temp = "" + ch;
                                    if (!(Character.isLetter(ch)) && !(temp.equals("-"))) {
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid last name input",
                                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }
                                for (int i = 0; i < a.length(); i++) {
                                    char ch = a.charAt(i);
                                    if (!(Character.isDigit(ch))) {
                                        JOptionPane.showMessageDialog(null,
                                                "Invalid age input",
                                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }
                                valid = true;
                                if (valid) {
                                    int confirm = JOptionPane.showConfirmDialog(null,
                                            "Are all the details you entered correct?\n " +
                                                    "The passenger's name is " + fn + " " + ln +
                                                    " and their age is " + a + ".\n If all the " +
                                                    "information shown is correct, select the " +
                                                    "yes button below, otherwise select the" +
                                                    " no button.", "Confirm Information",
                                            JOptionPane.YES_NO_OPTION);
                                    if (confirm == 0) {
                                        frame2.setVisible(false);
                                        Passenger p = new Passenger(fn, ln, a);
                                        BoardingPass bp = new BoardingPass(flightChoice, p);
                                        //DISPLAY LIST OF PEOPLE ON PLANE AND THIS PERSONS BOARDING PASS
                                        JFrame frame3 = new JFrame("Purdue University Flight Reservation System");
                                        frame3.setLayout(new GridLayout(6,1));
                                        frame3.setSize(750,750);
                                        JPanel[] jp3 = new JPanel[6];
                                        for (int i = 0; i < 6; i++) {
                                            JPanel jPanel = new JPanel();
                                            frame3.add(jPanel);
                                            jp3[i] = jPanel;
                                        }
                                        jp3[0].add(new JLabel("Flight data displaying for " + flightChoice));
                                        jp3[1].add(new JLabel("Enjoy your flight!"));
                                        jp3[2].add(new JLabel("Flight is now boarding at Gate " + bp.getGate()));
                                        JTextArea textArea1 = new JTextArea();
                                        textArea1.setEditable(false);
                                        if (flightChoice.equals("Delta Airlines")) {
                                            try {
                                                delta.addPassenger(p);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                delta.readPassengers();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            textArea1.setText(delta.displayFlight());

                                        } else if (flightChoice.equals("Alaska Airlines")) {
                                            try {
                                                alaska.addPassenger(p);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                alaska.readPassengers();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            textArea1.setText(alaska.displayFlight());

                                        } else {
                                            try {
                                                southwest.addPassenger(p);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            try {
                                                southwest.readPassengers();
                                            } catch (FileNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                            textArea1.setText(southwest.displayFlight());
                                        }
                                        jp3[3].add(textArea1);
                                        String boardingPass = bp.printBoarding(flightChoice, p);
                                        JTextArea ta = new JTextArea(boardingPass);
                                        ta.setEditable(false);
                                        jp3[4].add(ta);
                                        JButton exit = new JButton("Exit");
                                        exit.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent actionEvent) {
                                                frame3.setVisible(false);
                                                JOptionPane.showMessageDialog(null,
                                                        "Thank you for using the Purdue University " +
                                                                "Airline Management System!", "Thank You!",
                                                        JOptionPane.PLAIN_MESSAGE);
                                                System.exit(0);

                                            }
                                        });
                                        jp3[5].add(exit);
                                        JButton refresh = new JButton("Refresh flight status");
                                        refresh.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent actionEvent) {
                                                if (flightChoice.equals("Delta Airlines")) {
                                                    textArea1.setText(delta.displayFlight());

                                                } else if (flightChoice.equals("Alaska Airlines")) {
                                                    textArea1.setText(alaska.displayFlight());

                                                } else {
                                                    textArea1.setText(southwest.displayFlight());
                                                }
                                            }
                                        });
                                        jp3[5].add(refresh);

                                        for (int i = 0; i < 6; i++) {
                                            frame3.add(jp3[i]);
                                        }
                                        frame3.setVisible(true);


                                    }
                                }

                            }
                        });
                        jp2[7].add(next);

                        for (int i = 0; i < 8; i++) {
                            frame2.add(jp2[i]);
                        }
                        frame2.setVisible(true);
                    }
                }
            });
            jp[3].add(chooseFlight);
            for (int i = 0; i < 4; i++) {
                frame.add(jp[i]);
            }
            frame.setVisible(true);





        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "An unknown port was used. Thank you for using the Purdue Flight Management System.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        if (socketWriter != null) {
            try {
                socketWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch
        } //end if

        if (socketReader != null) {
            try {
                socketReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch
        } //end if
    } //end try catch finally
} //main


