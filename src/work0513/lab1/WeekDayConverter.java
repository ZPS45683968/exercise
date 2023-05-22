public class WeekDayConverter {
      public static void main (String[] args) {
           int weekDay = Integer.parseInt(args[0]);
           if(weekDay == 1) {
                System.out.println("The " + args[0] +"st day of the week is Monday" );
           }
           if(weekDay == 2) {
                System.out.println("The " + args[0] +"nd day of the week is Tuesday" );
           }
           if(weekDay == 3) {
                System.out.println("The " + args[0] +"rd day of the week is Wednesday" );
           }
           if(weekDay == 4) {
                System.out.println("The " + args[0] +"th day of the week is Thursday" );
           }
           if(weekDay == 5) {
                System.out.println("The " + args[0] +"th day of the week is Friday" );
           }
           if(weekDay == 6) {
                System.out.println("The " + args[0] +"th day of the week is Saturday") ;
           }
           if(weekDay == 7) {
                System.out.println("The " + args[0] +"th day of the week is Sunday" );
           }
           else {
                System.out.println("The input number is wrong" );
           }
       }
}