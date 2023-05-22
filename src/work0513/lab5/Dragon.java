public class Dragon extends Monster{
    public Dragon(String name){
        super(name);
    }
    @Override
    public  int attack(){
        double probability=Math.random();
        if (probability<0.3) {
            int damage=(int)(Math.random()*5)+1;
            System.out.println(this.getName()+", "+this.getClass()+", "+"scratches : "+damage+" points damage caused.");
            return damage;
        }else{
            int damage=(int)(Math.random()*50)+1;
            System.out.println(this.getName()+", "+this.getClass()+", "+"breathes fire : "+damage+" points damage caused.");
            return damage;
        }
    }
}
