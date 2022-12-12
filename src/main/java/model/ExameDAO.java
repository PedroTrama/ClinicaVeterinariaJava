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
public class ExameDAO extends DAO {

    private static ExameDAO instance;

    private ExameDAO() {
        getConnection();
        createTable();
    }

    public static ExameDAO getInstance() {
        return (instance == null ? (instance = new ExameDAO()) : instance);
    }
    
    
    public Exame create(String nome, int idConsulta) {
        try {
            PreparedStatement pstm;
            pstm = DAO.getConnection().prepareStatement("INSERT INTO exame (nome,id_consulta) VALUES (?,?)");
            pstm.setString(1, nome);
            pstm.setInt(2,idConsulta);
            executeUpdate(pstm);

        } catch (SQLException e) {
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return this.retrieveById(lastId("exame","id"));
    }

    private Exame buildObject(ResultSet rs) {
        Exame exame = null;
        try {
            exame = new Exame(rs.getInt("id"), rs.getString("nome"),rs.getInt("id_consulta"));

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return exame;
    }

    public List retrieve(String sql) {
        List<Exame> exames = new ArrayList();
        ResultSet rs = getResultSet(sql);

        try {
            while (rs.next()) {
                exames.add(buildObject(rs));
            }

        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return exames;
    }

    public Exame retrieveById(int id) {
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE id = " + id);
        return (exames.isEmpty() ? null : exames.get(0));
    }
    
    public List retrieveBySimilarName(String name){
        return this.retrieve("SELECT * FROM exame WHERE nome LIKE '%"+name+"%'");
    }
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM exame");
    }
    
    public List retrieveByAppointmentId(int id){
        return this.retrieve("SELECT * FROM exame where id_consulta="+id);
        
    }
    
    public void update(Exame exame){
        PreparedStatement pstm;
        
        try {
            pstm = ExameDAO.getConnection().prepareStatement("UPDATE exame SET nome=?, id_consulta = ? WHERE id=?");
            pstm.setString(1,exame.getDesExame());
            pstm.setInt(2,exame.getIdConsulta());
            pstm.setInt(3,exame.getIdExame());
            
            executeUpdate(pstm);
                    
            
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    public void delete(Exame exame){
        PreparedStatement pstm;
         try {
             pstm = ExameDAO.getConnection().prepareStatement("DELETE FROM exame WHERE id = ?");
             pstm.setInt(1,exame.getIdExame());
             executeUpdate(pstm);
             
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}

