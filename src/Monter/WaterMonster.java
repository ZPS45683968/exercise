package Monter;

public class WaterMonster extends Monster {
    public WaterMonster(String type, int hit_points, int attack_points, String[] weaknesses) {
        super(type, hit_points, attack_points, weaknesses);
    }

    @Override
    public boolean dodge() {
        if(getHit_points()<100){
            return true;
        }
        return false;
    }
}
