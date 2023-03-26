package brick_based_building_toy;

import java.util.Currency;
import java.util.Objects;

public class BrickSet implements Comparable<BrickSet>{
    int number;
    String name;
    String theme;
    int number_of_pieces;
    double  retail_price;
    public BrickSet(int number, String name, String theme, int number_of_pieces, double retail_price) {
        this.number = number;
        this.name = name;
        this.theme = theme;
        this.number_of_pieces = number_of_pieces;
        this.retail_price = retail_price;
    }
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public int getNummber_of_pieces() {
        return number_of_pieces;
    }

    public double getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(double retail_price) {
        this.retail_price = retail_price;
    }

    double getPricePerPiece(){
        return retail_price/number_of_pieces;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof BrickSet){
            BrickSet other = (BrickSet) obj;
            return Objects.equals(this.number, other.number)
                    && Objects.equals(this.name, other.name)
                    && Objects.equals(this.theme, other.theme)
                    && Objects.equals(this.number_of_pieces, other.number_of_pieces)
                    && Objects.equals(this.retail_price, other.retail_price);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, theme, number_of_pieces, retail_price);
    }

    @Override
    public String toString() {
        return this.number + ": " + this.name + " (" + this.theme + ") " +
                this.number_of_pieces + "pcs "
                + Currency.getInstance("GBP").getSymbol() + this.retail_price;
    }

    @Override
    public int compareTo(BrickSet o) {
        return Integer.compare(this.number, o.number);
    }
}
