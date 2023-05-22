public class Troll extends Monster{

    public Troll(String name1) {
        super(name1);
        if(name1.equals("Saul")||name1.equals("Salomon")){
            System.out.println("The name given by you is wrong!");
            setName("Detritus");
        }
    }
    public void setName(String name2) {
        this.name=name2;;
        if(name2.equals("Saul")||name2.equals("Salomon")){
            System.out.println("The name given by you is wrong!");
            this.name="Detritus";
    }
    }
}
