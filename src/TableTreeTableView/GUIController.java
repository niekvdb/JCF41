/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableTreeTableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author Niek
 */
public class GUIController implements Initializable {

    @FXML
    private TreeView treeViewDepartments;
    @FXML
    private TableView tableViewEmployees;

    @FXML
    private TableColumn tcFirstname;
    @FXML
    private TableColumn tcLastname;
    @FXML
    private TextField tbFirstname;
    @FXML
    private TextField tbLastname;
    @FXML
    private TextField tbDepartmentName;

    private TreeItem<Department> rootItem;

    private ObservableList<TreeItem<Department>> observableDepartments;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //maak nieuwe root item/department aan met naam departments en SET deze in de treeview
        rootItem = new TreeItem<>(new Department("Departments"));
        rootItem.setExpanded(true);
        tableViewEmployees.setEditable(true);
        treeViewDepartments.setRoot(rootItem);
        //Editable moet op true worden gezet zodat je de afdelingnaam kunt aanpassen
        treeViewDepartments.setEditable(true);

        //Zorgt er voor dat de treeview ook editable is. Bron: http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm 
        treeViewDepartments.setCellFactory(new Callback<TreeView<Department>, TreeCell<Department>>() {

            @Override
            public TreeCell<Department> call(TreeView<Department> param) {
                return new TextFieldTree(param);
            }

        });
        
        
        //De handler die wordt aangeroepen als er een wijziging word gecommit
        treeViewDepartments.setOnEditCommit(new EventHandler<TreeView.EditEvent<Department>>() {

            @Override
            public void handle(TreeView.EditEvent<Department> event) {
                //De geslecteerde department wordt opgehaald uit de TreeItem en de naam wordt veranderd in de nieuwe opgegeven naam
                TreeItem<Department> selectedDep = (TreeItem<Department>) treeViewDepartments.getSelectionModel().getSelectedItem();
                selectedDep.getValue().setName(event.getNewValue().getName());
            }

        });
        //Voornaam Collumn
        tcFirstname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        //Set de CellFactory zodat deze Editable is door er op te dubbelklikken
        tcFirstname.setCellFactory(TextFieldTableCell.forTableColumn());
        tcFirstname.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFirstname(t.getNewValue());
                    }
                });
        //Achternaam Collumn
        tcLastname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        //Set de CellFactory zodat deze Editable is door er op te dubbelklikken
        tcLastname.setCellFactory(TextFieldTableCell.forTableColumn());
        tcLastname.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setLastname(t.getNewValue());
                    }
                });
        //Maak een observable list van de afdelingen in de Tree Item
        ArrayList<TreeItem<Department>> departments = new ArrayList<>();
        observableDepartments = FXCollections.observableArrayList(departments);
        //Voeg een listener toe aan de observable list van afdelingen
        observableDepartments.addListener(new ListChangeListener<TreeItem<Department>>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends TreeItem<Department>> c) {
                //Als er een afdeling wordt toegevoegd en er is geen afdeling geselecteerd, wordt deze aan de RootAfdeling toegevoegd 
                if (treeViewDepartments.getSelectionModel().getSelectedItem() == null) {
                    rootItem.getChildren().setAll(observableDepartments);
                } //Als er een afdeling wordt toegevoegd en er is wel een afdeling geselecteerd, wordt deze aan de geselecteerde afdeling toegevoegd
                else {
                    TreeItem<Department> selectedItem = (TreeItem<Department>) treeViewDepartments.getSelectionModel().getSelectedItem();
                    System.out.println(c.getList());
                    selectedItem.getChildren().add(c.getList().get(c.getList().size() - 1));
                }
            }
        });
    }

    /**
     * Methode om de tableView met werknemers van de geselecteerde afdeling te
     * updaten
     */
    public void updateTableView() {
        TreeItem<Department> selectedDep = (TreeItem<Department>) treeViewDepartments.getSelectionModel().getSelectedItem();
        tableViewEmployees.setItems(selectedDep.getValue().getEmployees());
    }

    /**
     * Methode om een nieuwe department toe te voegen aan de treeview.
     */
    public void createNewDepartment() {
        if (tbDepartmentName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vul een department naam in.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TreeItem<Department> newItem = new TreeItem<>(new Department(tbDepartmentName.getText()));
        newItem.setExpanded(true);
        observableDepartments.add(newItem);
        tbDepartmentName.clear();
    }

    /**
     * Methode om een nieuwe Employee toe te voegen aan de treeview.
     */
    public void addNewEmployee() {
        if (tbFirstname.getText().isEmpty() || tbLastname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vul een voor en achternaam in.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (treeViewDepartments.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Selecteer een department.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Employee emp = new Employee(tbFirstname.getText(), tbLastname.getText());
        TreeItem<Department> selectedItem = (TreeItem<Department>) treeViewDepartments.getSelectionModel().getSelectedItem();
        selectedItem.getValue().addEmployee(emp);
        tbFirstname.clear();
        tbLastname.clear();
    }

}
