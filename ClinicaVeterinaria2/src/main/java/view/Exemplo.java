/*Exemplo de coisas que estariam no banco de dados para treinar essa main da view.
No código final esses codigos n vão existir, na verdade serão gerados pelo cliente que vai adicionar no sistema
*/
package view;

import model.ClienteDAO;

public class Exemplo {

    public static void main(String args[]){
        
        ClienteDAO.getInstance().create("Pedro Trama", "Rua", "19","1234","email.com"); 
        
    }
}
