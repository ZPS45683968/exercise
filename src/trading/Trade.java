package trading;

import java.util.List;
import java.util.Objects;

public class Trade {
    private int gems;
    private int amount;
    private Goods goods;

    public Trade(int gems, int amount, Goods goods) {
        this.gems = gems;
        this.amount = amount;
        this.goods = goods;
    }

    public int getGems() {
        return gems;
    }

    public int getAmount() {
        return amount;
    }

    public Goods getGoods() {
        return goods;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.gems, ((Trade) obj).gems)
                && Objects.equals(this.amount, ((Trade) obj).amount)
                && Objects.equals(this.goods, ((Trade) obj).goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gems, amount, goods);
    }

    @Override
    public String toString() {
        return gems + " gems" + " for " + amount + " " + goods;
    }
    public void execute(Trader trader, Citizen citizen){
        List<Trade> trades = trader.getTrades();
        if (!trades.contains(this)){
            throw new IllegalArgumentException("Trade not found");
        }else {
            if (citizen.executeTrade(this)){
                trader.addRandomTrade();
            }
        }
    }
}
