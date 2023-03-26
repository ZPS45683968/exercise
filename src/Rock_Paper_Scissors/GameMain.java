package Rock_Paper_Scissors;

import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        GamePlayer player = new GamePlayer();
        Scanner in = new Scanner(System.in);
        System.out.println("How many rounds?");
        int round = in.nextInt();
        int userWins = 0;
        int computerWins = 0;
        for(int i = 0;i < round; i++){
            System.out.println("Enter your choice:");
            Symbol userChoice = Symbol.valueOf(in.next().toUpperCase());
            Symbol computerChoice = player.chooseSymbol();
            System.out.println("User chose " + userChoice + ",computer chose " + computerChoice);
            switch (userChoice.getResult(computerChoice)) {
                case WIN:
                    System.out.println("User wins!");
                    userWins++;
                    break;
                case LOSE:
                    System.out.println("System wins!");
                    computerWins++;
                    break;
                case DRAW:
                    System.out.println("Draw!");
                    break;
            }
        }
        System.out.println("User wins " + userWins + ", computer wins " + computerWins);
                in.close();
    }
}
