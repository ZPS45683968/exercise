package calculator;

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

public class Calculator {
    public static void menu(){      //打印菜单
        System.out.println("Welcome to <John Doe's> Handy Calculator");
        System.out.println("      1. Addition");
        System.out.println("      2. Subtraction");
        System.out.println("      3. Multiplication");
        System.out.println("      4. Division");
        System.out.println("      5. Exit");

    }
    public static String getUserChoice(Scanner input) {     //获取用户输入
        System.out.print("What would you like to do?");
        while (true) {
            String choice = input.nextLine();

            if(choice.length() == 1 && choice.charAt(0) >= '1' && choice.charAt(0) <= '5') {
                return choice;
            }
            else if(choice.length() == 1 && (isDigit(choice.charAt(0)))) {
                System.out.print("You have not entered a number between 1 and 5. please re-enter your choice:");
            }
            else {
                System.out.print("You have entered an invalid choice, please re-enter your choice:");
            }
        }
    }
    public static void getTwoFloats(Scanner input,float[] f) {      //获取两个数
        while(true){
            String s = input.nextLine();
            String[] s1 = s.split(" ");
            String re1 = "[0-9]+\\.[0-9]+";
            //整数部分为1到多位
            String re2 = "[0-9]+";
            if(s1.length == 2 && (Pattern.matches(re1, s1[0]) || Pattern.matches(re2, s1[0])) && (Pattern.matches(re1, s1[1]) || Pattern.matches(re2, s1[1]))) {
                f[0] = Float.parseFloat(s1[0]);
                f[1] = Float.parseFloat(s1[1]);
                return;
            }
            else{
                System.out.print("You have entered invalid floats please re-enter:");
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            menu();
            int choice = Integer.parseInt(getUserChoice(input));
            System.out.println();
            switch (choice){
                case 1:
                    float[] f1 = new float[2];
                    System.out.print("Please enter two floats to add separated by a space:");
                    getTwoFloats(input,f1);
                    System.out.println("Result of adding " + String.format("%.2f",f1[0]) + " and " + String.format("%.2f",f1[1]) + " is " + String.format("%.2f",f1[0] + f1[1]));
                    break;
                case 2:
                    float[] f2 = new float[2];
                    System.out.print("Please enter two floats to subtract separated by a space:");
                    getTwoFloats(input,f2);
                    System.out.println("Result of subtracting " + String.format("%.2f",f2[0]) + " and " + String.format("%.2f",f2[1]) + " is " + String.format("%.2f",(f2[0] - f2[1])));
                    break;
                case 3:
                    float[] f3 = new float[2];
                    System.out.print("Please enter two floats to multiply, separated by a space:");
                    getTwoFloats(input,f3);
                    System.out.println("Result of multiplying " + String.format("%.2f",f3[0]) + " and " + String.format("%.2f",f3[1]) + " is " + String.format("%.2f",f3[0] * f3[1]));
                    break;
                case 4:
                    float[] f4 = new float[2];
                    System.out.print("Please enter two floats to divide, separated by a space:");
                    while(true){
                        getTwoFloats(input,f4);
                        if(f4[1] == 0){
                            System.out.print("You can't divide by zero please re-enter both floats:");
                        }
                        else{
                            break;
                        }
                    }
                    System.out.println("Result of dividing " + String.format("%.2f",f4[0]) + " by " + String.format("%.2f",f4[1]) + " is " + String.format("%.2f",f4[0] / f4[1]));
                    break;
                case 5:
                    System.out.println("Thank you for using <John Doe's> Handy Calculator");
                    System.exit(0);
            }
            System.out.println("Press enter key to continue ....\n");
        }

    }

}
