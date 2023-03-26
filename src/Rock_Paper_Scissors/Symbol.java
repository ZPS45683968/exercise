package Rock_Paper_Scissors;

public enum Symbol {
    ROCK, PARER, SCISSORS;
    public GameResult getResult(Symbol s){
        if(this == s){
            return GameResult.DRAW;
        }
        if(this == ROCK && s == PARER ||this == PARER && s == SCISSORS||this == SCISSORS && s == ROCK)
            return GameResult.LOSE;
        else
            return GameResult.WIN;
    }
}
