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
public class VeterinarioDAO extends DAO {

    private static VeterinarioDAO instance;

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    public static VeterinarioDAO getInstance() {
        return (instance == null ? (instance = new VeterinarioDAO()) : instance);
    }

    public Veterinario create(String nome, String email, String telefone) {
        try {
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO vet"
                    + " (nome,email,telefone) VALUES (?,?,?)");
            pstm.setString(1, nome);
            pstm.setString(2, email);
            pstm.setString(3, telefone);
            executeUpdate(pstm);

        } catch (SQLException e) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return this.retrieveById(lastId("vet", "id"));
    }

    private Veterinario buildObject(ResultSet rs) {
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario(rs.getInt("id"), rs.getString("nome"),
                    rs.getString("email"), rs.getString("telefone"));

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return veterinario;
    }

    public List retrieve(String sql) {
        List<Veterinario> veterinarios = new ArrayList();
        ResultSet rs = getResultSet(sql);

        try {
            while (rs.next()) {
                veterinarios.add(buildObject(rs));
            }

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return veterinarios;
    }

    public Veterinario retrieveById(int id) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM "
                + "vet WHERE id = " + id);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }

    public List retrieveBySimilarName(String name) {
        return this.retrieve("SELECT * FROM vet WHERE nome LIKE '%" + name + "%'");
    }

    public List retrieveAll() {
        return this.retrieve("SELECT * FROM vet");
    }

    public void update(Veterinario veterinario) {
        PreparedStatement pstm;

        try {
            pstm = VeterinarioDAO.getConnection().prepareStatement("UPDATE vet "
                    + "SET nome=?, email = ?, telefone = ? WHERE id=?");
            pstm.setString(1, veterinario.getNome());
            pstm.setString(2, veterinario.getEmail());
            pstm.setString(3, veterinario.getTelefone());
            pstm.setInt(4, veterinario.getIdVeterinario());

            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public void delete(Veterinario veterinario) {
        PreparedStatement pstm;
        try {
            pstm = VeterinarioDAO.getConnection().prepareStatement("DELETE FROM vet WHERE id = ?");
            pstm.setInt(1, veterinario.getIdVeterinario());
            executeUpdate(pstm);

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    void create(String jorge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
