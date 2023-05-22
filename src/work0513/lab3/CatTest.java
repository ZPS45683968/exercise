import java.awt.*;

/**
 * Title      : CatTest.java
 * Description: This class contains the test class for Cat.
 * Copyright  : Copyright (c) 2006 - 2023
 * @author  Laurissa Tokarchuk
 * @version 1.0
 * @author  Paula Fonseca
 * @version 1.4
 */
public class CatTest {
    public static void main(String[] args) {
        Cat myCat = new Cat("Napoleon","short",true,Color.ORANGE,300);
        Cat cat1 = new Cat("Tom","short",true,Color.BLACK,500);
        Cat cat2 = new Cat("Moggy","long",false,Color.WHITE,400);
        myCat.setName("Napoleon");
        myCat.setFurType("short");
        myCat.setTail(true);
        myCat.setColour(Color.ORANGE);
        myCat.setSpeed(300); // in metres per minute
        String a=myCat.getName();
        System.out.println(a);
        myCat.sleep(5);
        int numMetres = myCat.run(10, true);
        System.out.println("I have run " + numMetres + " metres.");
    }
}

/*
name = “Tom”
tail = true
speed = 500
furType = “short”
colour = Color.BLACK
name = “Moggy”
tail = false
speed = 400
furType = “long”
colour = Color.WHITE
 */