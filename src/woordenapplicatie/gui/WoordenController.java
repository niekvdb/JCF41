package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, Hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {

        // Get list of words
        ArrayList<String> wordCount = getInput();
        //clear output
        taOutput.setText("");
        // Add total number of words to output 
        taOutput.setText("Totaal aantal woorden: " + wordCount.size() + "\n");
        //smarter to use hash set cause it doesn't sort , meaning its quicker
        //TreeSet<String> differentWordCount = new TreeSet<>();
        Set<String> differentWordCount = new HashSet<>();
        differentWordCount.addAll(wordCount);
        // Add number of different words to the output field
        taOutput.setText(taOutput.getText() + "Aantal verschillende woorden: " + differentWordCount.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        taOutput.clear();
        // Get list of words
        ArrayList<String> words = getInput();
        HashSet<String> uniqueWords = new HashSet<>();
        //Put all words in a HashSet so duplicates will be replaced and in lowercase
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        //Add all items from the HashSet to an ArrayList.
        List<String> sortedList = new ArrayList<>(uniqueWords);
        //Put the list in reverse order and sorts.
        sortedList.sort(Collections.reverseOrder());
        //Print all items in the list to output window.
        for (String s : sortedList) {
            taOutput.setText(taOutput.getText() + s + "\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<String> getInput() {
        //split input text with a comma,dot, a new line or whitespace 
        String[] numberOfWords = taInput.getText().split("\\.|,|\\n| ");

        // Remove empty string from the list
        ArrayList<String> list = new ArrayList<>(Arrays.asList(numberOfWords));
        list.removeAll(Arrays.asList("", null));

        // Return new list
        return list;
    }

}
