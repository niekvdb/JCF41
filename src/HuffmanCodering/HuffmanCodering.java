package HuffmanCodering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCodering {

    private Map<Character, String> codesMap;

    public HuffmanCodering(String word) {
        /// Step 1 counting the frequency of characters ///

        //Print the word
        System.out.println("Word to compress: " + word + "\n");
        //Put unique words together with their frequency in a list
        List<CharFrequency> freqList = getCharacterFrequency(word);
        for (CharFrequency cf : freqList) {
            System.out.println(cf);
        }

        /// END OF STEP 1 ///
        /// Step 2 Sorting the characters on frequency
        freqList.sort(null);
        System.out.println("");
        for (CharFrequency f : freqList) {
            System.out.println(f.toString());
        }

        /// END OF STEP 2 ///
        /// Step 3 creating the Huffman tree
        // making a list of huffmannode objects
        List<HuffmanNode> nodes = new ArrayList<>();

        // fill the nodes list with previous char objects
        for (CharFrequency chr : freqList) {
            nodes.add(new HuffmanNode(chr));
        }

        // keep looping whilst the nodes list contains more than 1 node.
        // if it only has one node than this is the top of the tree,
        // which contains all information about children notes, and
        // therefore contains information about the while tree
        while (nodes.size() != 1) {
            // sorts the smallest frequency nodes on the bottom of the list
            nodes.sort(null);

            // gets the lowest level nodes
            HuffmanNode leftChild = nodes.get(0);
            HuffmanNode rightChild = nodes.get(1);

            // removes old nodes from list
            nodes.remove(leftChild);
            nodes.remove(rightChild);

            // adds new node to list
            nodes.add(new HuffmanNode(leftChild, rightChild));

            for (HuffmanNode node : nodes) {
                System.out.println(node.toString());
            }

            System.out.println("");
        }

        /// END OF STEP 3 ///
        /// Step 4 reading the codes from the tree ///
        
        //Initialize code map
        codesMap = new HashMap<>();
        //Put the codes for each letter in 'codesMap'
        getCodes(nodes.get(0), "");
        //Print all codes
        System.out.println("Character codes: ");

        for (Map.Entry<Character, String> entry : codesMap.entrySet()) {
            //Prints character and code in output
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        /// END OF STEP 4 ///

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

    private void getCodes(HuffmanNode node, String previousSteps) {

        if (node.getCharacter() == null) {
            getCodes(node.getRightChildNode(), previousSteps.concat("1"));
            getCodes(node.getLeftChildNode(), previousSteps.concat("0"));
        } else {
            codesMap.put(node.getCharacter().getCharacter(), previousSteps);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HuffmanCodering huffcode = new HuffmanCodering("kunstgeschiedenis");
    }
}
