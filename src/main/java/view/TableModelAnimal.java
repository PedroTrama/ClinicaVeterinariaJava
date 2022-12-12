package view;

import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Especie;
import model.EspecieDAO;
/**
 *
 * @author Pedro Trama
 */
public class TableModelAnimal extends TableModelGenerico {

    public TableModelAnimal(List vDados) {
        super(vDados, new String[]{"Nome", "Idade", "Sexo", "Espécie"});

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
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return animal.getNome();
            case 1:
                return animal.getAnoNasc();
            case 2:
                return animal.getSexo();
            case 3:
                if(animal.getId_especie()==0)
                    return "";
                Especie species = EspecieDAO.getInstance().retrieveById(animal.getId_especie());
                if (species != null) {
                    return species.getNome();
                }
                return "";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                animal.setNome((String) aValue);
                break;
            case 1:
                animal.setAnoNasc((String) aValue);
                break;
            case 2:
                animal.setSexo((String) aValue);
                break;
            case 3:
                Especie species = EspecieDAO.getInstance().retrieveByName((String) aValue);
                if (species == null) {
                    species = EspecieDAO.getInstance().create((String) aValue);
                    animal.setId_especie(species.getIdEspecie());
                    
                } else {
                    animal.setId_especie(species.getIdEspecie());
                }
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }

        AnimalDAO.getInstance().update(animal);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
