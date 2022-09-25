// DAO Implementation for SQLite.
package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:vet2021.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create table SQLite
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            // Table cliente:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente( \n"
                    + "idCliente INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "endereco VARCHAR, \n"
                    + "telefone VARCHAR, \n"
                    + "cep VARCHAR, \n"
                    + "email VARCHAR); \n");
            executeUpdate(stmt);
            // Table animal:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal( \n"
                    + "idAnimal INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR, \n"
                    + "idade INTEGER, \n"
                    + "sexo VARCHAR, \n"
                    + "idEspecie INTEGER, \n"
                    + "idCliente INTEGER); \n");
            executeUpdate(stmt);
            // Table especie:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie( \n"
                    + "idEspecie INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);
            // Table veterinario:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS veterinario( \n"
                    + "idVeterinario INTEGER PRIMARY KEY, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);        
            // Table tratamento:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tratamento( \n"
                    + "idTratamento INTEGER PRIMARY KEY, \n"
                    + "dataInicio VARCHAR, \n"
                    + "dataFim VARCHAR, \n"
                    + "idAnimal INTEGER); \n");
            executeUpdate(stmt);
            // Table consulta:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n"
                    + "idConsulta INTEGER PRIMARY KEY, \n"
                    + "data VARCHAR, \n"
                    + "hora VARCHAR, \n"
                    + "idAnimal INTEGER, \n"
                    + "idVeterinario INTEGER, \n"
                    + "idTratamento INTEGER; \n");
            executeUpdate(stmt);            
             // Table exame:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame( \n"
                    + "idExame INTEGER PRIMARY KEY, \n"
                    + "descricao VARCHAR, \n"
                    +"idConsulta INTEGER); \n");
            executeUpdate(stmt);      
           
            // Padrão para especie
            stmt = DAO.getConnection().prepareStatement("INSERT OR IGNORE INTO especie (id, nome) VALUES (1, 'Cachorro')");
            executeUpdate(stmt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
