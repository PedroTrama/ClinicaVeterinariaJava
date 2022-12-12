package model;
/**
 *
 * @author Pedro Trama
 */
public class Exame {
    private int idExame;
    private String desExame;
    private int idConsulta;

    public Exame(int idExame, String desExame, int idConsulta) {
        this.idExame = idExame;
        this.desExame = desExame;
        this.idConsulta = idConsulta;
    }
   

    public String getDesExame() {
        return desExame;
    }

    public void setDesExame(String desExame) {
        this.desExame = desExame;
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }
    
    
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    @Override
    public String toString(){
        return "Exame{\nCódigo: "+idExame+
                "\nCódigo da Consulta: "+idConsulta+
                "\nDescrição: "+desExame+
                "\n}";
    }
    
}