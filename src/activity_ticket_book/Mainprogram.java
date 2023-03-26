package activity_ticket_book;

import java.io.*;
import java.util.Scanner;

public class Mainprogram {
    SortedArrayList sortedArrayList = new SortedArrayList();
    public void writeLog(String log) {
        /*
         *   put the log into the clerk.txt
         */
        BufferedWriter out = null;
        try {
            //open the clerk.txt by append mode
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("clerk.txt", true)));
            //write the log into the clerk.txt
            out.write(log + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readinfo() throws IOException {
        /*
         *   read the clerk.txt and put the information into the sortedArrayList
         */
        String file_name = "input.txt";
        InputStream in = getClass().getResourceAsStream(file_name);
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        int num  = 0;

        // read the activities
        Activity activity = null;
        num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            String activity_name = br.readLine();
            int ticket_num = Integer.parseInt(br.readLine());
            activity = new Activity(activity_name, ticket_num);
            sortedArrayList.add(activity);
        }

        // read the customers
        Customer customer;
        int num1 = Integer.parseInt(br.readLine());
        for (int i = 0; i < num1; i++) {
            String str = br.readLine();
            customer = new Customer(str);
            sortedArrayList.add(customer);
        }

    }
    void menu() throws IOException {
        /*
         *   the menu of the program
         */
        System.out.println("f - to finish running the program.");
        writeLog("f - to finish running the program.");
        System.out.println("a - to display on the screen information about all the activities.");
        writeLog("a - to display on the screen information about all the activities.");
        System.out.println("c - to display on the screen information about all the customers.");
        writeLog("c - to display on the screen information about all the customers.");
        System.out.println("t - to update the stored data when tickets are bought by one of the registered customers.");
        writeLog("t - to update the stored data when tickets are bought by one of the registered customers.");
        System.out.println("r - to update the stored data when a registered customer cancels tickets for a booking");
        writeLog("r - to update the stored data when a registered customer cancels tickets for a booking");
    }
    public char getChoice() throws IOException {
        /*
         *   get the choice of the user
         */
        String choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your choice:");
        writeLog("Please enter your choice:");
        choice = input.nextLine();
        writeLog(choice);
        if (choice.length() != 1 || (choice.charAt(0) != 'f' && choice.charAt(0) != 'a' && choice.charAt(0) != 'c' && choice.charAt(0) != 't' && choice.charAt(0) != 'r')) {
            // if the choice is  not valid, print the error message
            System.out.println("Invalid input, please enter again:");
            writeLog("Invalid input, please enter again:");
        } else {
            return choice.charAt(0);
        }
        return ' ';
    }
    public void displayActivities() {
        /*
         *   display the activities
         */
        System.out.println("Activities:");
        writeLog("Activities:");
        for (int i = 0; i < sortedArrayList.activities_list.size(); i++) {
            System.out.println(((Activity)(sortedArrayList.activities_list.get(i))).getActivity_name() + " " + ((Activity)(sortedArrayList.activities_list.get(i))).getTicket_num());
            writeLog(((Activity)(sortedArrayList.activities_list.get(i))).getActivity_name() + " " + ((Activity)(sortedArrayList.activities_list.get(i))).getTicket_num());
        }
    }
    public void displayCustomers() {
        /*
         *   display the customers
         */
        System.out.println("Customers:");
        writeLog("Customers:");
        for (int i = 0; i < sortedArrayList.customer_list.size(); i++) {
            System.out.println(((Customer)(sortedArrayList.customer_list.get(i))).getCustomer_name());
            writeLog(((Customer)(sortedArrayList.customer_list.get(i))).getCustomer_name());
        }
    }
    public void bookTickets() throws IOException {
        /*
         *   book the tickets
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the customer name:");
        writeLog("Please enter the customer name:");
        String customer_name = input.nextLine();
        writeLog(customer_name);
        System.out.print("Please enter the activity name:");
        writeLog("Please enter the activity name:");
        String activity_name = input.nextLine();
        writeLog(activity_name);
        System.out.print("Please enter the number of tickets:");
        writeLog("Please enter the number of tickets:");
        int ticket_num = input.nextInt();
        writeLog(String.valueOf(ticket_num));
        for (int i = 0; i < sortedArrayList.customer_list.size(); i++) {
            // find the customer
            if (((Customer)(sortedArrayList.customer_list.get(i))).getCustomer_name().equals(customer_name)) {
                for (int j = 0; j < sortedArrayList.activities_list.size(); j++) {
                    // find the activity
                    if (((Activity)(sortedArrayList.activities_list.get(j))).getActivity_name().equals(activity_name)) {
                        // if the activity has enough tickets
                        Activity book_activity = new Activity(activity_name, ticket_num);
                        boolean c_book_ticket = ((Activity) (sortedArrayList.activities_list.get(j))).buyTicket(ticket_num);
                        if (c_book_ticket) {
                            // if the activity has enough tickets, book the tickets
                            boolean c_book_activity = ((Customer) (sortedArrayList.customer_list.get(i))).addActivity(book_activity);
                            if (c_book_activity) {
                                // if the activities the customer has booked  no more than 3, book the tickets
                                System.out.println("Tickets are successfully booked.");
                                writeLog("Tickets are successfully booked.");
                                return;
                            } else {
                                System.out.println("one customer can only book three activities.");
                                writeLog("one customer can only book three activities.");
                                return;
                            }
                        }else {
                            System.out.println("Tickets are not enough.");
                            writeLog("Tickets are not enough.");
                            writeLetters(book_activity,((Customer)(sortedArrayList.customer_list.get(i))));
                            return;
                        }
                    }else if (j == sortedArrayList.activities_list.size() - 1) {
                        System.out.println("The activity does not exist.");
                        writeLog("The activity does not exist.");
                        return;
                    }
                }
            }else if (i == sortedArrayList.customer_list.size() - 1) {
                System.out.println("The customer is not registered.");
                writeLog("The customer is not registered.");
                return;
            }
        }
    }
    public void writeLetters(Activity activity, Customer customer) {
        /*
         *   if the tickets are not enough ,write the letters
         */
        String letters = "Dear " + customer.getCustomer_name() + ":" + "The tickets of your booking for " + activity.getActivity_name() + " are not enough,we are sorry for the inconvenience.";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("letters.txt", true)));
            out.write(letters+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void returnTickets() throws IOException {
        /*
         *   return the tickets
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the customer name:");
        writeLog("Please enter the customer name:");
        String customer_name = input.nextLine();
        writeLog(customer_name);
        System.out.print("Please enter the activity name:");
        writeLog("Please enter the activity name:");
        String activity_name = input.nextLine();
        writeLog(activity_name);
        System.out.print("Please enter the number of tickets:");
        writeLog("Please enter the number of tickets:");
        int ticket_num = input.nextInt();
        writeLog(String.valueOf(ticket_num));
        for (int i = 0; i < sortedArrayList.customer_list.size(); i++) {
            // find the customer
            if (((Customer)(sortedArrayList.customer_list.get(i))).getCustomer_name().equals(customer_name)) {
                for (int j = 0; j < sortedArrayList.activities_list.size(); j++) {
                    // find the activity
                    if (((Activity)(sortedArrayList.activities_list.get(j))).getActivity_name().equals(activity_name)) {
                        Activity return_activity = new Activity(activity_name, ticket_num);
                        boolean c_return_ticket = ((Activity) (sortedArrayList.activities_list.get(j))).returnTicket(ticket_num);
                        if (c_return_ticket) {
                            boolean c_return_activity = ((Customer) (sortedArrayList.customer_list.get(i))).removeActivity(return_activity);
                            if (c_return_activity) {
                                System.out.println("Tickets are successfully returned.");
                                writeLog("Tickets are successfully returned.");
                                return;
                            } else {
                                System.out.println("The customer has not booked this activity.");
                                writeLog("The customer has not booked this activity.");
                                return;
                            }
                        }else {
                            System.out.println("The customer has not booked this activity.");
                            writeLog("The customer has not booked this activity.");
                            return;
                        }
                    }else if (j == sortedArrayList.activities_list.size() - 1) {
                        System.out.println("The activity does not exist.");
                        writeLog("The activity does not exist.");
                        return;
                    }
                }
            }else if (i == sortedArrayList.customer_list.size() - 1) {
                System.out.println("The customer is not registered.");
                writeLog("The customer is not registered.");
                return;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Mainprogram mainprogram = new Mainprogram();
        mainprogram.readinfo();     // read the information from the file
        while(true){
            mainprogram.menu();     // show the menu
            char choice = mainprogram.getChoice();      // get the choice
            switch (choice) {
                case 'f':
                    System.out.println("Program finished.");
                    mainprogram.writeLog("Program finished.");
                    System.exit(0);
                    break;
                case 'a':
                    mainprogram.displayActivities();
                    break;
                case 'c':
                    mainprogram.displayCustomers();
                    break;
                case 't':
                    mainprogram.bookTickets();
                    break;
                case 'r':
                    mainprogram.returnTickets();
                    break;
            }
        }
    }
}
