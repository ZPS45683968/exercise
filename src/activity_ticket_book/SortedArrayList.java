package activity_ticket_book;
import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E> {
    ArrayList<Activity> activities_list = new ArrayList<Activity>();
    ArrayList<Customer> customer_list = new ArrayList<Customer>();
    public SortedArrayList() {
        super();
    }
    public void add(Activity activity) {
        // insert the activity into the list in the correct position based on the activity name
        if (activities_list.size() == 0) {
            activities_list.add(activity);
        } else {
            for (int i = 0; i < activities_list.size(); i++) {
                // if the activity name is smaller than the activity name in the list, insert it before the activity name in the list
                if (activity.getActivity_name().compareTo(((Activity)(activities_list.get(i))).getActivity_name()) < 0) {
                    activities_list.add(i, activity);
                    break;
                }else if (i == activities_list.size() - 1) {
                    // if the activity name is bigger than the activity name in the list, insert it after the activity name in the list
                    activities_list.add(activity);
                    break;
                }
            }
        }
    }
    public void add(Customer customer) {
        //insert the customer into the list in alphabetical order of the customer's last name and firstname.
        if (customer_list.size() == 0) {
            customer_list.add(customer);
        } else {
            for (int i = 0; i < customer_list.size(); i++) {
                if (customer.getCustomer_lastname().compareTo(customer_list.get(i).getCustomer_lastname()) < 0) {
                    customer_list.add(i, customer);
                    break;
                } else if (customer.getCustomer_lastname().compareTo(customer_list.get(i).getCustomer_lastname()) == 0) {
                    if (customer.getCustomer_firstname().compareTo(customer_list.get(i).getCustomer_firstname()) < 0) {
                        customer_list.add(i, customer);
                        break;
                    }
                }else if (i == customer_list.size() - 1) {
                    customer_list.add(customer);
                    break;
                }
            }
        }
    }
}