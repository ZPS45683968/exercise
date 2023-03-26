package txt_read_and_split;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Match{
    private int match_num;
    private String city_name;
    private String Team1_name;
    private String Team2_name;
    public Match(int match_num, String city_name, String team1_name, String team2_name) {
        this.match_num = match_num;
        this.city_name = city_name;
        Team1_name = team1_name;
        Team2_name = team2_name;
    }
    public int getMatch_num() {
        return match_num;
    }
    public String getCity_name() {
        return city_name;
    }

    public String getTeam1_name() {
        return Team1_name;
    }

    public String getTeam2_name() {
        return Team2_name;
    }
}
class InvalidTeamNameException extends Exception{
    public InvalidTeamNameException(String message) {
        super(message);
    }
}
public class Readinfo {
    List<Match> matchList = new ArrayList<Match>();
    public void readinfo() throws IOException {
        String file_name = "schedule.txt";
        InputStream in = getClass().getResourceAsStream(file_name);
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] str = line.split(",");
            Match match = new Match(Integer.parseInt(str[0]), str[1], str[2], str[3]);
            matchList.add(match);
        }
    }
    public void findMatch(String teamName) throws InvalidTeamNameException {
        boolean flag = false;
        Map<String,Integer> result = new HashMap<>();
        for (Match match : matchList) {
            if (match.getTeam1_name().equals(teamName) || match.getTeam2_name().equals(teamName)) {
                flag = true;
                if (result.containsKey(match.getCity_name())) {
                    result.put(match.getCity_name(), result.get(match.getCity_name()) + 1);
                } else {
                    result.put(match.getCity_name(), 1);
                }
            }
        }
        System.out.println(teamName + " will play:");
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if(entry.getValue() > 1){
                System.out.println(entry.getValue() +" matches in " + entry.getKey());
            }else{
                System.out.println(entry.getValue() +" match in " + entry.getKey());
            }

        }
        if (!flag) {
            throw new InvalidTeamNameException("Invalid team name exception");
        }
    }
    public static void main(String[] args) throws IOException {
        Readinfo readinfo = new Readinfo();
        readinfo.readinfo();
        try {
            readinfo.findMatch(args[0]);
        } catch (InvalidTeamNameException e) {
            e.printStackTrace();
        }
    }
}
