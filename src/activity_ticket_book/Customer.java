package activity_ticket_book;

import java.util.ArrayList;

public class Customer {
    private String customer_name;       //the name of the customer
    public Customer(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_name() {          //get the name of the customer
        return customer_name;
    }
    public String getCustomer_firstname() {     //get the first name of the customer
        return customer_name.split(" ")[0];
    }
    public String getCustomer_lastname() {      //get the last name of the customer
        return customer_name.split(" ")[1];
    }
    ArrayList<Activity> activities = new ArrayList<Activity>();
    public boolean addActivity(Activity activity) {     //add an activity
        if (activities.size() >=3) {                    //the number of activities is less than 3
            return false;
        } else {
            activities.add(activity);
            return true;
        }
    }
    public boolean removeActivity(Activity activity) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getActivity_name().equals(activity.getActivity_name())) {

                if (activities.get(i).getTicket_num() < activity.getTicket_num()) {
                    //the  number of returned tickets is more than the number of booked tickets
                    return false;
                } else if (activities.get(i).getTicket_num() == activity.getTicket_num()) {
                    //the  number of returned tickets is equal to the number of booked tickets
                    activities.remove(i);
                    return true;
                } else {
                    //the  number of returned tickets is less than the number of booked tickets
                    activities.get(i).setTicket_num(activities.get(i).getTicket_num() - activity.getTicket_num());
                    return true;
                }
            }
        }
        return false;
    }
}
