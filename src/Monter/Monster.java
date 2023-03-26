package Monter;

import java.util.Arrays;

public abstract class Monster {
    private String type;
    private int hit_points;
    private int attack_points;
    private String [] weaknesses;
    public Monster(String type, int hit_points, int attack_points, String [] weaknesses) {
        this.type = type;
        this.hit_points = hit_points;
        this.attack_points = attack_points;
        this.weaknesses = weaknesses;
    }

    public String getType() {
        return type;
    }

    public int getHit_points() {
        return hit_points;
    }

    public int getAttack_points() {
        return attack_points;
    }

    public String[] getWeaknesses() {
        return weaknesses;
    }
    public boolean isWeakAgainst (String otherType) {
        for (String weakness : weaknesses) {
            if (weakness.equals(otherType)) {
                return true;
            }
        }
        return false;
    }
    public void removeHitPoints (int pointsToRemove) {
        hit_points -= pointsToRemove;
        if (hit_points < 0) {
            hit_points = 0;
        }
    }
    public abstract boolean dodge();
    public boolean attack (Monster otherMonster) {
        // If otherMonster is actually this monster (use “==” to check), return false immediately
        if (otherMonster == this) {
            return false;
        }
        // If either the current monster or the other monster hit points == 0, return false immediately
        if (this.hit_points <= 0 || otherMonster.hit_points <= 0) {
            return false;
        }
        if(otherMonster.dodge()){
            removeHitPoints(10);
            return false;
        }else {
            if (otherMonster.isWeakAgainst(this.type)) {
                otherMonster.removeHitPoints(this.attack_points + 20);
            } else {
                otherMonster.removeHitPoints(this.attack_points);
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return "Monster[" +
                "type='" + type + '\'' +
                ", hit_points=" + hit_points +
                ", attack_points=" + attack_points +
                ", weaknesses=" + Arrays.toString(weaknesses) +
                ']';
    }
}
