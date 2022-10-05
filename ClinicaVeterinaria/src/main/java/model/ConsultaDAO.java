package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }

    // CRUD    
    public Consulta create(String data, String hora, int idAnimal, int idVeterinario, int idTratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, hora, idAnimal, idVeterinario, idTratamento) VALUES (?,?,?,?,?)");
            stmt.setString(1, data);
            stmt.setString(2, hora);
            stmt.setInt(3, idAnimal);
            stmt.setInt(4, idVeterinario);
            stmt.setInt(5, idTratamento);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","idConsulta"));
    }
    

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            consulta = new Consulta(rs.getInt("idConsulta"), rs.getString("data"), rs.getString("hora"), rs.getInt("idAnimal"), rs.getInt("idVeterinario"), rs.getInt("idTratamento"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE idConsulta = " + lastId("consulta","idConsulta"));
    }

    // RetrieveById
    public Consulta retrieveById(int idConsulta) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idConsulta = " + idConsulta);
        return (consultas.isEmpty()?null:consultas.get(0));
    }
        
    // Updade
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, hora=?, idAnimal=?, idVeterinario=?, idTratamento=? WHERE idConsulta=?");
            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getHora());
            stmt.setInt(3, consulta.getIdAnimal());
            stmt.setInt(4, consulta.getIdVeterinario());
            stmt.setInt(5, consulta.getIdTratamento());
            stmt.setInt(6, consulta.getIdConsulta());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE idConsulta = ?");
            stmt.setInt(1, consulta.getIdConsulta());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
