package model;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    public List<Animal> getAllAnimais(){
        List<Animal> animais = new ArrayList();
        animais.add(new Animal(1,"Leo",11,'M'));
        animais.add(new Animal(2,"Ramon",8,'M'));
        animais.add(new Animal(3,"Julie",10,'F'));   
        animais.add(new Animal(4,"Tina",10,'F'));   
        animais.add(new Animal(5,"Kuro",5,'M'));   
        animais.add(new Animal(6,"Luci",3,'F'));
        animais.add(new Animal(7,"Tóqui",2,'F'));
        return animais;
    }
}
