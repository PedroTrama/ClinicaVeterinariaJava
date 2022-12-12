package view;

import java.util.List;
import model.Exame;
import model.ExameDAO;
/**
 *
 * @author Pedro Trama
 */
public class TableModelExame extends TableModelGenerico {

    public TableModelExame(List vDados) {
        super(vDados, new String[]{"Nome"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return exame.getDesExame();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame exame = (Exame) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                exame.setDesExame((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        ExameDAO.getInstance().update(exame);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
