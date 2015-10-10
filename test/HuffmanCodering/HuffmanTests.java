/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HuffmanCodering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author Thomas Kleinendorst
 */
public class HuffmanTests {
    private HuffmanCodering kunstgeschiedenis;
    private HuffmanCodering autoventieldopfabriek;
    private HuffmanCodering randomKarakters;
    
    public HuffmanTests() {
        kunstgeschiedenis = new HuffmanCodering("kunstgeschiedenis");
        autoventieldopfabriek = new HuffmanCodering("autoventieldopfabriek");
        
        // whilst creating the various objects all of the methods are already tested for errors,
        // this is why we loop the random generator to test a set of 20 random instances for general errors:
        for(int i = 0; i < 20; i++) {
            randomKarakters = genereerRandomHuffmanObject();
        }
        
    }
    
    private HuffmanCodering genereerRandomHuffmanObject() {
        // characters to include in random string
        char[] chars = "abcdefghijklmnopqrstuvwxyz ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(70) + 50; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return new HuffmanCodering(sb.toString());
    }
    
    @Test
    public void testSortCharFrequencyList() {
        List<CharFrequency> inputList = new ArrayList<>();
        inputList.add(increaseCharCount(new CharFrequency('T'), 2));
        inputList.add(increaseCharCount(new CharFrequency('E'), 3));
        inputList.add(increaseCharCount(new CharFrequency('S'), 5));
        inputList.add(increaseCharCount(new CharFrequency('N'), 1));
    }
    
    private CharFrequency increaseCharCount(CharFrequency character, int count) {
        for(int i = 0; i < count; i++) {
            character.addFrequency();
        }
        return character;
    }
    
}
