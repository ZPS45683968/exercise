package Monter;

public class FireMonster extends Monster {
    public static int count = 1;
    public FireMonster(String type, int hit_points, int attack_points, String[] weaknesses) {
        super(type, hit_points, attack_points, weaknesses);
    }

    @Override
    public boolean dodge() {
        if(count % 2 != 0){
            count++;
            return true;
        }
        return false;
    }
}
