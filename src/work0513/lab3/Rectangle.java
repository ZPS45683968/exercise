import java.awt.*;

public class Rectangle {
    double l;
    double w;
    public Rectangle(double l,double w) {
        this.l = l;
        this.w = w;
    }

    public Rectangle() {
    }
     
    public double  area(){
        double k=l*w;
        return  k;
    }

    public static void main(String[] args) {
        double area1,area2;
        Rectangle a1 =new Rectangle(8,6);
        Rectangle a2 =new Rectangle(7,7);
        area1=a1.area();
        area2=a2.area();
        System.out.println(area1);
        System.out.println(area2);

    }
}