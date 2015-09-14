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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
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
        /// We hebben hier gekozen voor een HashSet
        /// De keuze om een set ipv een map te gebruiken
        /// komt voor uit het feit dat we geen key - value 
        /// paar willen hebben, dat zou voor deze methode geen nut hebben
        /// We hebben gekozen voor een HashSet omdat een treeset meteen
        /// je lijst sorteerd, wat extra processor kracht kost.
        /// en dit hebben we totaal niet nodig aangezien de woorden
        /// hier niet gessorteerd hoeven te worden   

        // Get list of words
        ArrayList<String> wordCount = getInput(taInput.getText());
        //clear output
        taOutput.setText("");
        // Add total number of words to output 
        taOutput.setText("Totaal aantal woorden: " + wordCount.size() + "\n");
        
        //TreeSet<String> differentWordCount = new TreeSet<>();
        Set<String> differentWordCount = new HashSet<>();
        differentWordCount.addAll(wordCount);
        // Add number of different words to the output field
        taOutput.setText(taOutput.getText() + "Aantal verschillende woorden: " + differentWordCount.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        /// We hebben hier gekozen voor een treeset
        /// We hebben ten eerste gekozen voor een set
        /// aangezien we geen gebruik willen maken van een key - value paar
        /// Vervolgens hebben we voor de Treeset gekozen aangezien
        /// we gebruik willen maken van de sorteering van de Treeset.
        /// Aangezien we de woorden op (omgekeerde)volgorde willen printen
        
        taOutput.clear();
        // Get list of words
        ArrayList<String> words = getInput(taInput.getText());
        TreeSet<String> uniqueWords = new TreeSet<>(Collections.reverseOrder());
        uniqueWords.addAll(words);
        
        for (String s : uniqueWords) {
            taOutput.setText(taOutput.getText() + s + "\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        /// We hebben gekozen voor een HashMap
        /// we hebben gekozen voor een Map omdat een key - value paar
        /// wenselijk is, we willen namelijk het woord als key
        /// en daarbij het aantal keer dat het woord voorkomt als value.
        /// We hebben gekozen voor een Hashmap aangezien we het aantal woorden
        /// willen sorteren en dit niet als key kunnen gebruiken.
        /// Aan de TreeSet zullen we dus niets hebben
     
        // Het output veld leeg maken
        taOutput.clear();
        // Alle woorden ophalen
        ArrayList<String> words = getInput(taInput.getText());
        HashMap<String, Integer> map = new HashMap<>();
        
        // als de key nog niet voorkomt dan voer je het nieuwe veld in met value 1
        // komt hij wel voor dan hoog je de value op met 1
        int hoogsteWaarde = 0;
        
        for(String s : words) {
            if(!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.replace(s, map.get(s) + 1);
                if(hoogsteWaarde < map.get(s)) {
                    hoogsteWaarde = map.get(s);
                }
            }
        }
        
        for(int i = 1; i <= hoogsteWaarde; i++) {
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                if(entry.getValue() == i) {
                    taOutput.setText(taOutput.getText() + entry.getKey() + ":\t" + entry.getValue() + "\n");
                }
            }
        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        // we hebben gekozen voor een map aangezien we gebruik gaan maken van een key(het woord) en een value (de positie)
        // we doen dit door het woord in de key op te slaan, en de verschillende zinslocaties in de value
        // we hebben daarvoor een TreeMap gekozen omdat deze automatisch sorteert op de volgorde van de woorden (keys)
        TreeMap map = new TreeMap();
        //aanmaken van een int(eerste zin)
        int i = 1;
        //zinnen ophalen om te kijken waar de woorden staan
        for (String sentence : taInput.getText().split("\n")) {
            //kijken of het woord al in de treemap staat, zo niet voeg het toe aan de map, anders updaten van de value
            for (String word : getInput(sentence)) { 
                if (map.containsKey(word)) {
                    // We gaan voorkomen dat de
                    // woorden 2 keer in een zin gevonden worden.
                    // hiervoor bouwen we een simpele check in
                    
                    if(!map.get(word).toString().contains(String.valueOf(i))) {
                        map.replace(word, map.get(word) + ", " + String.valueOf(i));
                    }
                } else {
                    map.put(word, String.valueOf(i));
                }
            }
            //naar de volgende regel
            i++;
        }
        //omzetten naar text 
        String output = "";
        for (Object s : map.keySet()) {
            output += s + ": " + "[" +map.get(s)+"]" + "\n";
        }
        taOutput.setText(output);
    }

    public ArrayList<String> getInput(String input) {
        //split input text with a comma,dot, a new line or whitespace 
        String[] numberOfWords = input.split("\\.|,|\\n| ");
        ArrayList<String> list = new ArrayList<>();
        
        for(String s : numberOfWords) {
            list.add(s.toLowerCase());
        }

        // Remove empty string from the list
        list.removeAll(Arrays.asList("", null));

        // Return new list
        return list;
    }
}
