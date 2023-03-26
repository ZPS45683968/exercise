package Monter;

public class ElectricMonster extends Monster {
    public ElectricMonster(String type, int hit_points, int attack_points, String[] weaknesses) {
        super(type, hit_points, attack_points, weaknesses);
    }

    @Override
    public boolean dodge() {
        return false;
    }


}
