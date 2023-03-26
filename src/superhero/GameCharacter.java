package superhero;

import java.util.*;
import java.util.stream.Collectors;

public class GameCharacter {
    private String name;
    private int cost;
    private Set<Power> powers = new HashSet<Power>();
    public GameCharacter(String name, int cost, Power... powers) {
        this.name = name;
        this.cost = cost;
        this.powers.addAll(Arrays.asList(powers));
    }

    public String getName() {
        return name;
    }

    public Set<Power> getPowers() {
        return powers;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(name, ((GameCharacter) obj).name)
                && Objects.equals(powers, ((GameCharacter) obj).powers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, powers);
    }

    @Override
    public String toString() {
        return name + "'powers are "+ powers.toString();
    }

}
