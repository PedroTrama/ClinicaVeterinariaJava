package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Consulta;
import model.ConsultaDAO;
import model.VeterinarioDAO;
/**
 *
 * @author Pedro Trama
 */
public class TableModelConsulta extends TableModelGenerico {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    public TableModelConsulta(List vDados) {
        super(vDados, new String[]{"Data", "Hora", "Sintomas", "Veterinário", "Status"});

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dateFormat.format(consulta.getDataConsulta().getTime());
            case 1:
                return consulta.getHora();
            case 2:
                return consulta.getComentario();
            case 3:
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVeterinario()).getNome();
            case 4:
                return consulta.isTerminou() == true ? "Realizada" : "Marcada";

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(dateFormat.parse((String) aValue));
                } catch (ParseException ex) {
                    Logger.getLogger(TableModelConsulta.class.getName()).log(Level.SEVERE, null, ex);
                }
                consulta.setDataConsulta(cal);
                break;
            case 1:
                consulta.setHora((String) aValue);
                break;
            case 2:
                consulta.setComentario((String) aValue);
                break;
            case 3:
                consulta.setTerminou((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        ConsultaDAO.getInstance().update(consulta);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
