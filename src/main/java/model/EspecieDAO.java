package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DAO.getConnection;
/**
 *
 * @author Pedro Trama
 */
public class EspecieDAO extends DAO {

    private static EspecieDAO instance;

    private EspecieDAO() {
        createTable();
        getConnection();
    }

    public static EspecieDAO getInstance() {
        return (instance == null ? (instance = new EspecieDAO()) : instance);
    }

    public Especie create(String nome) {
        try {
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO ESPECIE (nome) VALUES (?)");
            pstm.setString(1, nome);
            executeUpdate(pstm);

        } catch (SQLException e) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return this.retrieveById(lastId("especie", "id"));
    }

    private Especie buildObject(ResultSet rs) {
        Especie especie = null;
        try {
            especie = new Especie(rs.getInt("id"), rs.getString("nome"));

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return especie;
    }

    public List retrieve(String sql) {
        List<Especie> especies = new ArrayList();
        ResultSet rs = getResultSet(sql);

        try {
            while (rs.next()) {
                especies.add(buildObject(rs));
            }

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return especies;
    }

    public Especie retrieveById(int id) {
        List<Especie> especies = this.retrieve("SELECT * FROM especie WHERE id = " + id);
        return (especies.isEmpty() ? null : especies.get(0));
    }

    public Especie retrieveByName(String name) {
        List<Especie> especies = this.retrieve("SELECT * FROM especie WHERE nome LIKE '" + name+"%'");
        return (especies.isEmpty() ? null : especies.get(0));
    }

    public List retrieveBySimilarName(String name) {
        return this.retrieve("SELECT * FROM especie WHERE nome LIKE '%" + name + "%'");
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM especie");
    }

    public void update(Especie especie) {
        PreparedStatement pstm;

        try {
            pstm = EspecieDAO.getConnection().prepareStatement("UPDATE especie SET nome=? WHERE id=?");
            pstm.setString(1, especie.getNome());
            pstm.setInt(2, especie.getIdEspecie());
            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Especie especie) {
        PreparedStatement pstm;
        try {
            pstm = EspecieDAO.getConnection().prepareStatement("DELETE FROM especie WHERE id = ?");
            pstm.setInt(1, especie.getIdEspecie());
            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
