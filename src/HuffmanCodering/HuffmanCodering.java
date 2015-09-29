/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanCodering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Niek
 */
public class HuffmanCodering {

    private Map<Character, String> codesMap;

    public HuffmanCodering(String word) {
        //Print the word
        System.out.println("Word to compress: " + word + "\n");
          //Put unique words together with their frequency in a list
        List<CharFrequency> freqList = getCharacterFrequency(word);
        for(CharFrequency cf : freqList)
        {
            System.out.println(cf); 
        }
    }



    /**
     * Converts a List of characters to a List of CharFrequency objects which
     * contain a character and the frequency of it's appearance.
     *
     * @param word the List of words to convert
     * @return a List with every character and it's frequency
     */
    private List<CharFrequency> getCharacterFrequency(String word) {
        List<CharFrequency> frequencies = new ArrayList<>();
        for (Character c : word.toCharArray()) {
            boolean found = false;
            for (CharFrequency cf : frequencies) {
                if (cf.getCharacter().equals(c)) {
                    cf.addFrequency();
                    found = true;
                }
            }
            if (!found) {
                frequencies.add(new CharFrequency(c));
            }
        }
        return frequencies;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HuffmanCodering huffcode = new HuffmanCodering("kunstgeschiedenis");
    }

}
