package activity_ticket_book;
public class Activity {
    private String activity_name;       //the name of the activity
    private int ticket_num;              //the number of tickets

    public Activity(String activity_name, int ticket_num) {
        this.activity_name = activity_name;
        this.ticket_num = ticket_num;
    }
    public void setTicket_num(int ticket_num) {
        this.ticket_num = ticket_num;
    }
    public String getActivity_name() {
        return activity_name;
    }
    public int getTicket_num() {
        return ticket_num;
    }
    public boolean buyTicket(int ticket_num) {      //book tickets
        if (ticket_num <= this.ticket_num) {
            this.ticket_num = this.ticket_num - ticket_num;
            return true;
        } else {
            return false;
        }
    }
    public boolean returnTicket(int ticket_num) {       //return tickets
        this.ticket_num = this.ticket_num + ticket_num;
        return true;
    }
}
