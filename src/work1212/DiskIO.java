package work1212;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskIO {
    public static void main(String[] args) throws IOException {
        processCLArguments(args);                   //call method to process command line arguments
        processInputOutputFiles(args);              //call method to process input and output files
    }

    public static void processCLArguments(String[] args) {
        if (args.length < 2)                        //check if there are at least 2 arguments, if not, print error message
            System.out.println("Usage: java DiskIO inputFile outputFile");
        else {                                          //if there are at least 2 arguments, print them
            System.out.println("Input will be read from: " + args[0]);
            System.out.println("Output will be written to: " + args[1]);
        }
    }

    public static void processInputOutputFiles(String[] args) throws IOException {
        //create a Map to store the data from the input file
        Map<String, List<Integer>> grade_map = new LinkedHashMap<String, List<Integer>>();
        String input_file = args[0];                                //get the input file name from the command line
        InputStream in = new FileInputStream(input_file);           //create an input stream to read the input file
        InputStreamReader reader = new InputStreamReader(in);       //create an input stream reader to read the input file
        BufferedReader br = new BufferedReader(reader);             //create a buffered reader to read the input file
        String line = null;
        while ((line = br.readLine()) != null) {                //read the file line by line
            String[] split = line.split(",");               //split the line by comma
            String name = split[0];                             //get the name
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i < split.length; i++) {                //get the score
                list.add(Integer.parseInt(split[i].trim()));        //add the score to the list, trim the space
            }
            grade_map.put(name, list);                              //put the name and score into the map
        }
        br.close();                                                     //close the buffered reader
        reader.close();
        in.close();
        int i = 1;
        for (String key : grade_map.keySet()) {                     //print the map
            System.out.print("Student #: " + i + " " + key);
            List<Integer> list = grade_map.get(key);
            for (Integer integer : list) {
                System.out.print(", " + integer);
            }
            System.out.println();
            i++;
        }
        //写入文件
        String output_file = args[1];                       //get the output file name from the command line
        BufferedWriter out = null;
        try {
            //open the output_file by append mode
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(output_file, true)));      //create an output stream to write the output file
            i = 1;
            for (String key : grade_map.keySet()) {                     //print the map
                out.write("Student #" + i + " is: " + "\"" + key + "\" whose raw scores are: ");
                List<Integer> list = grade_map.get(key);
                for (Integer integer : list) {
                    out.write(integer + ": ");
                }
                out.write("\n");
                i++;
            }
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
}