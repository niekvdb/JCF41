/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Kleinendorst
 */
public class WoordenControllerTest {
    // instantie van de controller
    private static WoordenController instantie;
    private static String voorbeeldZin;
    
    @BeforeClass
    public static void setUpClass() {
        instantie = new WoordenController();
        voorbeeldZin = "Een, twee, drie, vier\n" + "Hoedje van, Hoedje van\n" +
                        "Een, twee, drie, vier\n" + "Hoedje van papier";
    }
    
    @Test
    public void testAantalActionsMethod() {
        String output = instantie.aantalActionsMethod(voorbeeldZin);
        
        assertEquals(output, "Totaal aantal woorden: 15\n" +
                                "Aantal verschillende woorden: 7");
    }
    
    @Test
    public void testSorteerActionMethod() {
        String output = instantie.sorteerActionMethod(voorbeeldZin);
        
        assertEquals(output, "vier\n" + "van\n" + "twee\n" + "papier\n" + 
                                        "hoedje\n" + "een\n" + "drie\n");
    }
    
    @Test
    public void testFrequentieActionMethod() {
        String output = instantie.frequentieActionMethod(voorbeeldZin);
        
        assertEquals(output, "papier:	1\n" + "twee:	2\n" + "drie:	2\n" + "vier:	2\n" + "een:	2\n" + 
                                             "hoedje:	3\n" + "van:	3\n");
    }
    
    @Test
    public void testConcordatieActionMethod() {
        String output = instantie.concordatieActionMethod(voorbeeldZin);
        
        assertEquals(output, "drie: [1, 3]\n" + "een: [1, 3]\n" + "hoedje: [2, 4]\n" +
                                    "papier: [4]\n" + "twee: [1, 3]\n" + "van: [2, 4]\n" + "vier: [1, 3]\n");
    }
}
