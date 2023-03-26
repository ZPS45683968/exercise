package trading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trader {
    private int gems;
    private int amount;
    private Goods goods;

    private List<Trade> trades = new ArrayList<>();
    Trade trade = null;
    public Trader(){
        gems = new Random().nextInt(1,6);
        amount = new Random().nextInt(1,6);
        goods = Goods.values()[new Random().nextInt(Goods.values().length)];
        trade = new Trade(gems, amount, goods);
    }

    public void addRandomTrade(){
        new Trader();
        trades.add(trade);
    }
    public List<Trade> getTrades(){
        return trades;
    }
}
