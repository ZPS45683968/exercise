package Rock_Paper_Scissors;

import java.util.Random;

public class GamePlayer {
    private Random random = new Random();
    public Symbol chooseSymbol(){
        return Symbol.values()[random.nextInt(Symbol.values().length)];
    }
}
