package main;

import model.Animal;
import model.Cliente;
import model.ClienteDAO;

public class Main {
    public static void main(String[] args) {        

        Animal a1 = new Animal(1,"Leo",11,"M");
        Animal a2 = new Animal(2,"Lucy",3,"F");
        
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        
        c1.addAnimal(a1);
        c1.addAnimal(a2);
        
        System.out.println(c1);
        
    }
}

//https://www.youtube.com/watch?v=NU1z19uPHW0&list=PLohEp4M4wZUrm2BjDMqayJ4vEsgnOBhO4&index=3
//40:36