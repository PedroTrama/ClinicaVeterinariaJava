package model;

import static model.DAO.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Pedro Trama
 */
public class AnimalDAO extends DAO {

    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    //Singleton
    public static AnimalDAO getInstance() {
        return (instance == null ? (instance = new AnimalDAO()) : instance);
    }

    public Animal create(String nome, String anoNasc, String sexo, int id_cliente) {
        try {
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO animal"
                    + " (nome,anoNasc,sexo,id_cliente)"
                    + "VALUES (?,?,?,?)");
            pstm.setString(1, nome);
            pstm.setString(2, anoNasc);
            pstm.setString(3, sexo);
            pstm.setInt(4, id_cliente);
            executeUpdate(pstm);

        } catch (SQLException e) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return this.retrieveById(lastId("animal", "id"));
    }

    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            animal = new Animal(rs.getInt("id"), rs.getString("nome"),
                    rs.getString("anoNasc"), rs.getString("sexo"),
                    rs.getInt("id_especie"), rs.getInt("id_cliente"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    public List retrieve(String query) {
        List<Animal> animais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                animais.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animais;
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }

    public Animal retrieveById(int id) {
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animais.isEmpty() ? null : animais.get(0));
    }

    public List<Animal> retrieveByClientId(int id) {
        List<Animal> animais = this.retrieve("SELECT * FROM animal where id_cliente = " + id);

        return (animais.isEmpty() ? null : animais);
    }

    public List retrieveBySimilarName(String name,int id) {
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + name + "%' AND id_cliente = "+id);
    }

    public void update(Animal animal) {
        PreparedStatement pstm;
        try {
            pstm = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, "
                    + "sexo=?, id_especie=?, id_cliente=? WHERE id=?");
            pstm.setString(1, animal.getNome());
            pstm.setString(2, animal.getAnoNasc());
            pstm.setString(3, animal.getSexo());
            pstm.setInt(4, animal.getId_especie());
            pstm.setInt(5, animal.getId_cliente());
            pstm.setInt(6, animal.getId());
            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Animal animal) {
        PreparedStatement pstm;

        try {
            pstm = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            pstm.setInt(1, animal.getId());
            executeUpdate(pstm);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public List<Consulta> getLastAppointments(int id){
            return ConsultaDAO.getInstance().retrieve("SELECT * "
                    + "FROM consulta "
                    + "WHERE id_animal = "+id
                    + " LIMIT 5");
            
    }

}
