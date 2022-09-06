package model;

import java.util.List;
import java.util.ArrayList;

public class ClienteDAO {
    public List<Cliente> getAllClientes(){
        List<Cliente> clients = new ArrayList();
        clients.add(new Cliente(1,"Pedro","Avenida Dona Antonia Valverde Cruanes 169 Apto 06","(19)99673-2162","13484-400","pedrotrama29@gmail.com"));
        //clients.add(new Cliente());    
        return clients;       
    }
}
