package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioDAO extends DAO {
    private static VeterinarioDAO instance;

    public static Object getIstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VeterinarioDAO getInstance() {
        return (instance==null?(instance = new VeterinarioDAO()):instance);
    }

    // CRUD    
    public Veterinario create(String nome) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO veterinario (nome) VALUES (?)");
            stmt.setString(1, nome);

            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("veterinario","idVeterinario"));
    }

    private Veterinario buildObject(ResultSet rs) {
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario(rs.getInt("idVeterinario"), rs.getString("nome"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinario;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Veterinario> veterinarios = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                veterinarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM veterinario");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM veterinario WHERE idVeterinario = " + lastId("veterinario","idVeterinario"));
    }

    // RetrieveById
    public Veterinario retrieveById(int idVeterinario) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM veterinario WHERE idVeterinario = " + idVeterinario);
        return (veterinarios.isEmpty()?null:veterinarios.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM veterinario WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Veterinario veterinario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE veterinario SET nome=? WHERE idVeterinario=?");
            stmt.setString(1, veterinario.getNome());
            stmt.setInt(2, veterinario.getIdVeterinario());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Veterinario veterinario) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM veterinario WHERE idVeterinario = ?");
            stmt.setInt(1, veterinario.getIdVeterinario());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
