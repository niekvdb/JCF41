package HuffmanCodering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCodering {

    private Map<Character, String> codesMap;
    public String inputString;
    public String outputString;

    public HuffmanCodering(String word) {
        /// Step 1 counting the frequency of characters ///
        inputString = word;

        //Print the word
        System.out.println("Word to compress: " + word + "\n");
        //Put unique words together with their frequency in a list
        List<CharFrequency> freqList = getCharacterFrequency(word);
        for (CharFrequency cf : freqList) {
            System.out.println(cf);
        }

        /// END OF STEP 1 ///
        /// Step 2 Sorting the characters on frequency /// 
        freqList = sortCharFrequencyList(freqList);
        System.out.println("");
        for (CharFrequency f : freqList) {
            System.out.println(f.toString());
        }

        /// END OF STEP 2 ///
        /// Step 3 creating the Huffman tree
        HuffmanNode tree = createTree(freqList);

        /// END OF STEP 3 ///
        /// Step 4 reading the codes from the tree ///
        //Initialize code map
        codesMap = new HashMap<>();
        //Put the codes for each letter in 'codesMap'
        getCodes(tree, "");
        //Print all codes
        System.out.println("Character codes: ");

        for (Map.Entry<Character, String> entry : codesMap.entrySet()) {
            //Prints character and code in output
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        /// END OF STEP 4 ///
        
        /// Step 5 Encode message and print it ///
        String codedMessage = codeMessage(getWordAsCharacters(word));
        System.out.println("Coded message: " + codedMessage);
        System.out.println("Amount of bytes: " + codedMessage.length());
        
        /// END OF STEP 5 ///
        
        /// step 6 Decode message and print it ///        
        outputString = decodeMessage(codedMessage, tree);
        System.out.println(outputString);
        
        /// END OF STEP 6 ///
    }
    
    public List<CharFrequency> sortCharFrequencyList(List<CharFrequency> freqList) {
        freqList.sort(null);
        return freqList;
    }
    
    public String decodeMessage(String inputMessage, HuffmanNode tree) {
        // Create a string which will hold the message that needs to be decoded further
        String message = inputMessage;
        // A string which will contain the decoded message
        String result = "";
        
        // keep looping until all of the message is decoded
        while(message.length() > 0) {
            // an integer to keep track of the working character in
            // the message which needs to be decoded
            int characterCount = 0;
            // place where the decoded character is placed in
            CharFrequency character = null;
            // The working node of that loop step
            HuffmanNode node = tree;
            
            while(character == null) {
                if(!node.getIsCharacterNode()) {
                    // check if the next node is the left or right branch in the tree
                    if("0".equals(message.substring(characterCount, characterCount + 1))) {
                        node = node.getLeftChildNode();
                    } else if("1".equals(message.substring(characterCount, characterCount + 1))) {
                        node = node.getRightChildNode();
                    } else {
                        throw new IllegalArgumentException("The input string does not consist of only binairy information!");
                    }
                    
                    characterCount++;
                } else {
                    // character is found!!
                    character = node.getCharacter();
                }
            }
            
            result += character.getCharacter();
            message = message.substring(characterCount);
        }
        
        return result;
    }
    
    /**
     * Converts a List of characters to a List of CharFrequency objects which
     * contain a character and the frequency of it's appearance.
     *
     * @param word the List of words to convert
     * @return a List with every character and it's frequency
     */
    public List<CharFrequency> getCharacterFrequency(String word) {
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

    public void getCodes(HuffmanNode node, String previousSteps) {

        if (!node.getIsCharacterNode()) {
            getCodes(node.getRightChildNode(), previousSteps.concat("1"));
            getCodes(node.getLeftChildNode(), previousSteps.concat("0"));
        } else {
            codesMap.put(node.getCharacter().getCharacter(), previousSteps);
        }
    }

    public String codeMessage(List<Character> message) {
        String result = "";
        if (!codesMap.isEmpty()) {
            for (char c : message) {
                result = result.concat(codesMap.get(c) + "");
            }
        }
        return result;
    }
    
    
    /**
     * Converts a String into a List of it's characters
     *
     * @param word the word to convert
     * @return a List with all characters of the given word
     */
    public List<Character> getWordAsCharacters(String word) {
        List<Character> wordToCompress = new ArrayList<>();
        //Put all char objects in the ArrayList
        for (char c : word.toCharArray()) {
            wordToCompress.add(c);
        }
        return wordToCompress;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HuffmanCodering huffcode = new HuffmanCodering("autoventieldopfabriek");
    }

    public HuffmanNode createTree(List<CharFrequency> freqList) {
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
        
        return nodes.get(0);
    }
}
