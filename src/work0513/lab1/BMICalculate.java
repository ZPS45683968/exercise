public class BMICalculate {
  public static void main(String[] args) { 
        int weight = Integer.parseInt(args[0]);
        double height1 = Integer.parseInt(args[1]);
        double height = height1/100;
        double bmi= weight / (height*height);
           if (bmi<18.5){
                    System.out.println("You are in the Underweight range.");
              }
           if(bmi >= 18.5 && bmi <= 24.9 ) {
                    System.out.println("You are in the Normal range.");
              }
           if(bmi >= 25 && bmi <= 29.9) {
                    System.out.println("You are in the Overweight range.");
              }
           if(bmi>=30) {
                    System.out.println("You are in the Obese range.");
              }
         System.out.println("Your weight: " + weight + "kg");
         System.out.println("Your height: " +height+ "m");
         System.out.println("Your BMI: " + String.format("%.2f", bmi));
}        
} 