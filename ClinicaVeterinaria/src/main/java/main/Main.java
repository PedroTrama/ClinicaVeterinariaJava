package main;

import model.AnimalDAO;
import model.ClienteDAO;
import model.EspecieDAO;

public class Main {
    public static void main(String[] args) {        
     
        System.out.println(ClienteDAO.getInstance().retrieveAll());
        System.out.println(AnimalDAO.getInstance().retrieveAll());
    }
}

//https://www.youtube.com/watch?v=I1gFwAnPRF4&list=PLohEp4M4wZUrm2BjDMqayJ4vEsgnOBhO4&index=7

/*
NA MAIN DA VIEW
if(!jTextField1.getText()isEmpty()){
    int idCliente = Integer.parseInt(jTextField1.getText());
    List<Animal> animais = AnimalDAO.getInstance().retrieveByIdCliente(idCliente);
    for(Animal a : animais){
        jTextArea1.append(a.getNome()+"\n");
    } 
    else {
        List<Cliente> clientes = ClienteDAO.getInstance().retrieveAll();
        
        for(Cliente c : cliente){
            jTextArea1.append(c.getId()+") "+c.getNome()+"\n")
        }
    }
}*/

//ClienteDAO.getInstance().create("Plinio", "Rua dos Bobos", "707070", "435678", "email@email.com");