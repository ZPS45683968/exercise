package Scorecal;

import java.io.IOException;

public class TestLetterGrader {
    public static void main(String[] args) throws IOException {

        LetterGrader letterGrader = new LetterGrader(args[0], args[1]);
        //LetterGrader letterGrader1 = new LetterGrader("input.txt", "output.txt");
        letterGrader.readScore();   // read the score from the input file
        letterGrader.calcLetterGrade(); // calculate the letter grade
        letterGrader.printGrade();  // print the letter grade to the output file
        letterGrader.displayAverages(); // display the averages
        letterGrader.doCleanup();   // do the cleanup
    }
}
