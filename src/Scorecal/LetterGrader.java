package Scorecal;

import java.io.*;
import java.util.*;

public class LetterGrader {
    private String input_file;
    private String output_file;
    Map<String, List<Integer>> grade_map = null;
    Map<String, Character> result = null;
    Map<String, List<Double>> ave_min_max = null;
    public LetterGrader(String input_file, String output_file) {
        this.input_file = input_file;
        this.output_file = output_file;
        grade_map = new HashMap<String, List<Integer>>();
        result = new TreeMap<String,Character>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                }
        );
        ave_min_max = new HashMap<String, List<Double>>();
    }
    // read the score from the input file, and store the score in a grade_map
    public void readScore() throws IOException {
        //TODO
        InputStream in = getClass().getResourceAsStream(input_file);    //get the input file
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while ((line = br.readLine()) != null) {        //read the file line by line
            String[] split = line.split(",");
            String name = split[0];                     //get the name
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i < split.length; i++) {        //get the score
                list.add(Integer.parseInt(split[i].trim()));
            }
            grade_map.put(name, list);              //put the name and score into the map
        }
    }
    //calculate the final grade for each student and store the result in result map
    //calculate the average, minimum and maximum score for each test and store the result in ave_min_max map
    public void calcLetterGrade() {
        //TODO
        List<Double> weight = new ArrayList<Double>();
        //0.1,0.1,0.1,0.1,0.2,0.15,0.25
        weight.add(0.1);
        weight.add(0.1);
        weight.add(0.1);
        weight.add(0.1);
        weight.add(0.2);
        weight.add(0.15);
        weight.add(0.25);
        //calculate the final score
        for (String name : grade_map.keySet()) {
            List<Integer> list = grade_map.get(name);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i) * weight.get(i);
            }
            if (sum >= 90) {
                result.put(name, 'A');
            } else if (sum >= 80) {
                result.put(name, 'B');
            } else if (sum >= 70) {
                result.put(name, 'C');
            } else if (sum >= 60) {
                result.put(name, 'D');
            } else {
                result.put(name, 'F');
            }
        }
        //calculate the average, minimum and maximum score for each test

        for(int i = 0; i < 7; i++) {
            double sum = 0;
            int min = 101;
            int max = 0;
            for (String name : grade_map.keySet()) {
                List<Integer> list = grade_map.get(name);
                for (int j = 0; j < list.size(); j++) {
                    if (j == i) {
                        sum += list.get(j);
                        if (list.get(j) < min) {
                            min = list.get(j);
                        }
                        if (list.get(j) > max) {
                            max = list.get(j);
                        }
                    }
                }
            }
            List<Double> ave_min_max_list = new ArrayList<Double>();
            ave_min_max_list.add(sum / grade_map.size());
            ave_min_max_list.add((double) min);
            ave_min_max_list.add((double) max);
            ave_min_max.put("test" + (i + 1), ave_min_max_list);
        }
    }
    //control the output format
    public String appentStr4Length(String str , int length){
        if(str == null){
            str = "";
        }
        try {
            int strLen = 0;// calculate the length of the string
            for(int i = 0 ; i<str.length(); i++){
                    strLen = strLen + 1;
            }
            if(strLen>=length){
                return str;
            }
            int remain = length - strLen;//calculate the number of spaces to be added
            str = str + " ".repeat(Math.max(0, remain));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    //write the result map to output_file
    public void printGrade(){
        BufferedWriter out = null;
        try {
            //open the output_file by append mode
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(output_file, true)));
            //write the result map to output_file
            for (String name : result.keySet()) {
                out.write(appentStr4Length(name, 25) + result.get(name) + "\r\n");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //print the average, minimum and maximum score for each test
    public void displayAverages(){
        //TODO
        System.out.println("Here is the class averages:");
        //output the average, minimum and maximum score for each test, format as below:
        System.out.printf("\t\t\t%-8s%-8s%-8s%-8s%-8s%-8s%-8s\n", "Q1", "Q2", "Q3", "Q4", "Mid1", "Mid2", "Final");
        for(int i = 0; i < 3; i++){
            if(i == 0){
                System.out.print("Average:\t");
            }else if(i == 1){
                System.out.print("Minimum:\t");
            }else{
                System.out.print("Maximum:\t");
            }
            for(int j = 0; j < 7; j++){
                if(i == 0){
                    System.out.printf("%-8.2f",(ave_min_max.get("test" + (j + 1)).get(i)));
                }else{
                    System.out.print((int) Math.round(ave_min_max.get("test" + (j + 1)).get(i)) + "\t\t");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Press ENTER to continue . ..\n");
    }
    public void doCleanup(){
        //TODO
        grade_map.clear();
        result.clear();
        ave_min_max.clear();
    }
}
