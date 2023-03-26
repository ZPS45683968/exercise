package trading;

import java.util.HashMap;
import java.util.Map;

public class Citizen {
    private int amount;
    private int gems;
    private Map<Goods, Integer> goods;
    Citizen citizen = null;
    public Citizen(int amount) {
        this.amount = amount;
        citizen  = new Citizen(amount);
        goods = new HashMap<Goods, Integer>();
    }
    public int getGems() {
        return gems;
    }

    public int getAmount(Goods good) {
        return goods.get(good);
    }
    public boolean executeTrade (Trade trade) {
        if (trade.getGems() > gems) {
            return false;
        }
        gems -= trade.getGems();
        goods.put(trade.getGoods(), trade.getAmount());
        return true;
    }
}
