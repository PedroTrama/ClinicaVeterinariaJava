package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecieDAO extends DAO {
    private static EspecieDAO instance;

    public static Object getIstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private EspecieDAO() {
        getConnection();
        createTable();
    }

// Singleton
    public static EspecieDAO getInstance() {
        return (instance==null?(instance = new EspecieDAO()):instance);
    }

// CRUD    
    public Especie create(String nome){
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO especie(nome) VALUES (?)");
            stmt.setString(1, nome);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("especie","idEspecie"));
    }
    
    private Especie buildObject(ResultSet rs) {
        Especie especie = null;
        try {
            especie = new Especie(rs.getInt("idEspecie"), rs.getString("nome"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especie;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Especie> especies = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                especies.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especies;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM especie");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM especie WHERE idEspecie = " + lastId("especie","idEspecie"));
    }

    // RetrieveById
    public Especie retrieveById(int idEspecie) {
        List<Especie> especies = this.retrieve("SELECT * FROM especie WHERE idEspecie = " + idEspecie);
        return (especies.isEmpty()?null:especies.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM especie WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Especie especie) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE especie SET nome=? WHERE idEspecie=?");
            stmt.setString(1, especie.getNome());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    // Delete   
    public void delete(Especie especie) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM especie WHERE idEspecie = ?");
            stmt.setInt(1, especie.getIdEspecie());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}


