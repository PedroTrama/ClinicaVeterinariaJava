package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.EspecieDAO;
import model.Exame;
import model.ExameDAO;
import model.Tratamento;
import model.TratamentoDAO;
import model.Veterinario;
import model.VeterinarioDAO;
import view.TableModelAnimal;
import view.TableModelGenerico;
import view.Status;
/**
 *
 * @author Pedro Trama
 */
public class Controller {
    /*
    As declarações das linhas 41 a 58 foram adaptados do código de autoria do Arthur Conti
    Disponível em: https://github.com/arthurgconti/TT001/tree/main/src/main/java/controller
    Acesso em 18/11/2022
    Esse pedaço de código foi reutilizadoo para corrigir erros na camada View.
    */
    public final static int TRATAMENTO = 0;
    public final static int CONSULTA = 1;
    public final static int VETERINARIO = 2;
    public final static int ANIMAL = 3;
    public final static int CLIENTE = 4;
    public final static int EXAME = 5;

    private static Cliente selectedClient = null;
    private static Animal selectedAnimal = null;
    private static String selectedAnimalSpecie = null;
    private static Tratamento selectedTreatment = null;
    private static Exame selectedExam = null;
    private static Consulta selectedAppointment = null;
    private static Veterinario selectedVet = null;

    private static JLabel selectedClientLabel = null;
    private static JLabel selectedAnimalLabel = null;
    private static JLabel selectedSpecieLabel = null;

    public static Cliente getSelectedClient() {
        return selectedClient;
    }

    public static Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    public static Tratamento getSelectedTreatment() {
        return selectedTreatment;
    }

    public static Exame getSelectedExam() {
        return selectedExam;
    }

    public static Consulta getSelectedAppointment() {
        return selectedAppointment;
    }

    public static Veterinario getSelectedVet() {
        return selectedVet;
    }

    public static void setLabel(JLabel client, JLabel animal, JLabel specie) {
        selectedClientLabel = client;
        selectedAnimalLabel = animal;
        selectedSpecieLabel = specie;
    }

    public static void setTableModel(JTable table, TableModelGenerico tableModel) {
        table.setModel(tableModel);
    }

    public static void atualizarClientesNomesParecidos(JTable table, String nome) {
        if (!nome.isBlank()) {
            ((TableModelGenerico) table.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveBySimilarName(nome));
        } else {
            ((TableModelGenerico) table.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveAll());
        }
    }

    public static void atualizarVeterinariosNomesParecidos(JTable table, String nome) {
        if (!nome.isBlank()) {
            ((TableModelGenerico) table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveBySimilarName(nome));
        } else {
            ((TableModelGenerico) table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveAll());
        }
    }

    public static void atualizarAnimaisNomeParecidos(JTable table, String text) {
        if (!text.isBlank() && selectedClient != null) {
            ((TableModelGenerico) table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveBySimilarName(text, selectedClient.getId()));
        } else {
            if (selectedClient == null) {
                ((TableModelGenerico) table.getModel()).addListOfItems(new ArrayList());
            } else {
                table.setModel(new TableModelAnimal(AnimalDAO.getInstance().retrieveByClientId(selectedClient.getId())));
            }
        }
    }

    public static Cliente criarCliente(String nome, String endereco, String cep, String email, String telefone) {
        return ClienteDAO.getInstance().create(nome, endereco, cep, email, telefone);
    }

    public static void deletarCliente(JTable table) {
        ClienteDAO.getInstance().delete(selectedClient);
        ((TableModelGenerico) table.getModel()).removeItem(table.getSelectedRow());

    }

    public static Animal criarAnimal(String nome, String anoNasc, String sexo) {
        return AnimalDAO.getInstance().create(nome, anoNasc, sexo, selectedClient.getId());
    }

    public static void deletarAnimal(JTable table) {
        AnimalDAO.getInstance().delete(selectedAnimal);
        ((TableModelGenerico) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static void criarTratamento(String tratamento) {
        TratamentoDAO.getInstance().create(selectedAnimal.getId(),
                tratamento, new SimpleDateFormat("dd/MM/yyyy").format(new Date()), "", 0);
    }

    public static Veterinario criarVeterinario(String nome, String email, String telefone) {
        return VeterinarioDAO.getInstance().create(nome, email, telefone);
    }

    public static void deletarVeterinario(JTable table) {
        VeterinarioDAO.getInstance().delete(selectedVet);
        ((TableModelGenerico) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static Exame criarExame(String nome) {
        return ExameDAO.getInstance().create(nome, selectedAppointment.getIdConsulta());
    }

    public static void deletarExame(JTable table) {
        ExameDAO.getInstance().delete(selectedExam);
        ((TableModelGenerico) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static void criarConsulta(Calendar data, String horario, String comentario) {
        ConsultaDAO.getInstance().create(data, horario, comentario, selectedAnimal.getId(),
                selectedVet.getIdVeterinario(), selectedTreatment.getIdTratamento(),
                0);

    }

    public static void deletarAppointment(JTable table) {
        ConsultaDAO.getInstance().delete(selectedAppointment);
        ((TableModelGenerico) table.getModel()).removeItem(table.getSelectedRow());
    }

    public static void setSelected(Object selected) {
        if (selected instanceof Cliente) {
            selectedClient = (Cliente) selected;
            selectedClientLabel.setText(selectedClient.getNome());
            selectedAnimalLabel.setText("");
            selectedSpecieLabel.setText("");
        } else if (selected instanceof Animal) {
            selectedAnimal = (Animal) selected;

            if (selectedAnimal.getId_especie() != 0) {
                selectedAnimalSpecie = EspecieDAO.getInstance().retrieveById(selectedAnimal.getId_especie()).getNome();
            } else {
                selectedAnimalSpecie = "";
            }
            selectedAnimalLabel.setText(selectedAnimal.getNome());
            selectedSpecieLabel.setText(selectedAnimalSpecie);
        } else if (selected instanceof Tratamento) {
            selectedTreatment = (Tratamento) selected;
        } else if (selected instanceof Consulta) {
            selectedAppointment = (Consulta) selected;
        } else if (selected instanceof Exame) {
            selectedExam = (Exame) selected;
        } else if (selected instanceof Veterinario) {
            selectedVet = (Veterinario) selected;
        }
    }

    public static void clearSelection(int option) {
        switch (option) {
            case TRATAMENTO:
                selectedTreatment = null;
                break;
            case VETERINARIO:
                selectedVet = null;
                break;
            case CONSULTA:
                selectedAppointment = null;
                break;
            case ANIMAL:
                selectedAnimal = null;
                selectedSpecieLabel.setText("");
                selectedAnimalLabel.setText("");
                break;
            case CLIENTE:
                selectedClient = null;
                selectedClientLabel.setText("");
                break;
            case EXAME:
                selectedExam = null;
                break;
            default:
                break;
        }
    }

    public static void setLabelValues() {
        selectedClientLabel.setText(selectedClient.getNome());
        selectedAnimalLabel.setText(selectedAnimal.getNome());
        selectedSpecieLabel.setText(selectedAnimalSpecie);
    }

    public static List getAppointmentsAnimal() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
        List lista = AnimalDAO.getInstance().getLastAppointments(selectedAnimal.getId());
        List listaString = new ArrayList();

        for (Object obj : lista) {
            listaString.add("Data: " + dataFormat.format(((Consulta) obj).getDataConsulta().getTime())
                    + " | Horário:" + ((Consulta) obj).getHora()
                    + " | Vet: " + (VeterinarioDAO.getInstance().retrieveById(((Consulta) obj).getIdVeterinario()).getNome())
            );
        }
        return listaString;
    }

    public static void endTreatment() {
            if (!selectedTreatment.isTerminou()) {
            TratamentoDAO.getInstance().endTreatment(selectedTreatment);
        } else {
            JOptionPane.showMessageDialog(null, "Tratamento já finalizado");
        }
    }

    public static void endAppointment() {
       if (!selectedAppointment.isTerminou()) {
            ConsultaDAO.getInstance().endAppointment(selectedAppointment);
        } else {
            JOptionPane.showMessageDialog(null, "Consulta já realizada");
        }
    }

    public static void atualizarToggleButtons(JTable table, Status.TreatmentOption opt) {
        switch (opt) {
            case TODOS:
                ((TableModelGenerico) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveAllByAnimalId(selectedAnimal.getId()));
                break;
            case ANDAMENTO:
                ((TableModelGenerico) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), Status.TreatmentOption.ANDAMENTO));
                break;
            default:
                ((TableModelGenerico) table.getModel()).addListOfItems(TratamentoDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), Status.TreatmentOption.FINALIZADO));
                break;
        }
    }

    public static void atualizarFiltrosAppointmentButtons(JTable table, Status.AppointmentOption opt) {
        switch (opt) {
            case TODOS:
                ((TableModelGenerico) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveAllByTreatmentId(selectedTreatment.getIdTratamento()));
                break;
            case MARCADA:
                ((TableModelGenerico) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), Status.AppointmentOption.MARCADA));
                break;

            case REALIZADA:
                ((TableModelGenerico) table.getModel()).addListOfItems(ConsultaDAO.getInstance().retrieveByStatus(selectedAnimal.getId(), Status.AppointmentOption.REALIZADA));
                break;
        }
    }
}
