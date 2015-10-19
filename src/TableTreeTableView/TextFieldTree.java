/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableTreeTableView;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Niek
 */
public class TextFieldTree extends TreeCell<Department> {

    private TextField textField;
    private TreeView tree;

    public TextFieldTree(TreeView<Department> tree) {
        this.tree = tree;
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem().toString());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(Department item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
            }
        }
    }
/**
 * Methode voor het maken van een textfield, bron : http://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
 */
    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                //als er op enter wordt geduwdt dan wordt de edit gecommit
                if (t.getCode() == KeyCode.ENTER) {
                    TreeItem<Department> selectedDep = (TreeItem<Department>) tree.getSelectionModel().getSelectedItem();
                    commitEdit(new Department(textField.getText(), selectedDep.getValue().getEmployees()));
                } 
                //als er op esca[e wordt geduwdt dan wordt de edit gecancled.
                else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

}
