public class Monster {
    public String name;
    public double spAttackProbability=0.2;

    public String getName() {
        return name;
    }



    public Monster(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move(int direction){
        switch(direction) {
            case 1:
                System.out.println(this.name + "is moving 1 step NORTH.");
                break;
            case 2:
                System.out.println(this.name + "is moving 1 step EAST.");
                break;
            case 3:
                System.out.println(this.name + "is moving 1 step SOUTH.");
                break;
            default:
                System.out.println(this.name + "is moving 1 step WEST.");
                break;
        }
    }
    public  int attack(){
        int damage=((int)Math.random()*5);
        System.out.println(this.name+", "+this.getClass()+", "+"attacks generically: "+damage+" points damage caused.");
        return damage;
    }
}
