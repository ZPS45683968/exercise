package calculators;

import java.util.Scanner;
import java.util.regex.Pattern;

public class OOPCalculator {
    private float   firstNumber;    // first number in the calculation
    private float   secondNumber;   // second number in the calculation
    private String  choice;         // choice of operation
    public OOPCalculator() {        // constructor
        firstNumber = 0;
        secondNumber = 0;
        choice = "";
    }
    public int askCalcChoice(){
        Scanner input = new Scanner(System.in);     // create a Scanner object
        // print the menu
        System.out.println("Welcome to <John Doe's> Handy Calculator");
        System.out.println();
        System.out.println("      1. Addition");
        System.out.println("      2. Subtraction");
        System.out.println("      3. Multiplication");
        System.out.println("      4. Division");
        System.out.println("      5. Exit");
        System.out.print("      What would you like to do?");
        while(true) {
            choice = input.nextLine();
            // check if the user entered a valid choice
            if (choice.length() == 1 && (choice.charAt(0) >= '1' && choice.charAt(0) <= '5')) {
                return Integer.parseInt(choice);
            } else if (choice.length() == 1 && (Character.isDigit(choice.charAt(0)))) {
                System.out.print("      You have not entered a number between 1 and 5. please re-enter your choice:");
            } else if(choice.length() == 1){
                // put the user's choice into the choice variable
                switch (choice.charAt(0)) {
                    case 'a':
                    case 'A':
                        choice = "1";
                        return 1;
                    case 's':
                    case 'S':
                        choice = "2";
                        return 2;
                    case 'm':
                    case 'M':
                        choice = "3";
                        return 3;
                    case 'd':
                    case 'D':
                        choice = "4";
                        return 4;
                    case 'x':
                    case 'X':
                        choice = "5";
                        return 5;
                    default:
                        System.out.print("      You have entered an invalid choice, please re-enter your choice:");
                }
            } else {
                System.out.print("      You have entered an invalid choice, please re-enter your choice:");
            }
        }
    }
    public void askTwoValues(){  // ask for two values
        Scanner input = new Scanner(System.in);
        switch (choice.charAt(0)) {
            case '1':
                System.out.print("      Please enter two numbers to add separate by space:");
                break;
            case '2':
                System.out.print("      Please enter two numbers to subtract separate by space:");
                break;
            case '3':
                System.out.print("      Please enter two numbers to multiply separate by space:");
                break;
            case '4':
                System.out.print("      Please enter two numbers to divide separate by space:");
                break;
        }
        while(true){
            String s = input.nextLine();
            String[] s1 = s.split(" ");
            String re1 = "[0-9]+\\.[0-9]+";
            String re2 = "[0-9]+";
            //judge if the input is valid
            if(s1.length == 2 && (Pattern.matches(re1, s1[0]) || Pattern.matches(re2, s1[0])) && (Pattern.matches(re1, s1[1]) || Pattern.matches(re2, s1[1]))) {
                firstNumber = Float.parseFloat(s1[0]);
                secondNumber = Float.parseFloat(s1[1]);
                //if the operation is division, judge if the second number is 0
                if(secondNumber == 0 && choice.charAt(0) == '4'){
                    System.out.print("      You cannot divide by zero, please re-enter your numbers:");
                }else{
                    return;
                }

            }
            else{
                System.out.print("      You have entered invalid floats please re-enter:");
            }
        }
    }
    public void displayResult(){ // display the result
        switch (choice.charAt(0)) {
            case '1':
                System.out.println("      Result of adding " + String.format("%.2f",firstNumber) + " and " + String.format("%.2f",secondNumber) + " is " + String.format("%.2f",firstNumber + secondNumber));
                break;
            case '2':
                System.out.println("      Result of subtracting " + String.format("%.2f",firstNumber) + " and " + String.format("%.2f",secondNumber) + " is " + String.format("%.2f",firstNumber - secondNumber));
                break;
            case '3':
                System.out.println("      Result of multiplying " + String.format("%.2f",firstNumber) + " and " + String.format("%.2f",secondNumber) + " is " + String.format("%.2f",firstNumber * secondNumber));
                break;
            case '4':
                System.out.println("      Result of dividing " + String.format("%.2f",firstNumber) + " and " + String.format("%.2f",secondNumber) + " is " + String.format("%.2f",firstNumber / secondNumber));
                break;
        }
        System.out.println();
        System.out.println("      Press enter key to continue ....");
    }
    public void displayBye(){   // display the bye message
        System.out.println("      Thank you for using and waits for press enter key");
    }
}
class TestCalculator {      // test class
    public static void main(String[] args) {
        OOPCalculator calc = new OOPCalculator();

        while(calc.askCalcChoice()!= 5){
            calc.askTwoValues();        // ask for two values
            calc.displayResult();       // display the result
        }
        calc.displayBye();              // display the bye message
    }
}