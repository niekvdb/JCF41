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
        voorbeeldZin = "Een, twee, drie, vier\n" + "Hoedje van, Hoedje van\n"
                + "Een, twee, drie, vier\n" + "Hoedje van papier";
    }

    //test om de aantal methode te testen
    @Test
    public void testAantalActionsMethod() {
        String output = instantie.aantalActionsMethod(voorbeeldZin);
        String output2 = instantie.aantalActionsMethod("Hallo, Merel ,Hallo");
        assertEquals(output, "Totaal aantal woorden: 15\n"
                + "Aantal verschillende woorden: 7");
        assertEquals(output2, "Totaal aantal woorden: 3\n"
                + "Aantal verschillende woorden: 2");
    }

    //test om de sorteer methode te testen
    @Test
    public void testSorteerActionMethod() {
        String output = instantie.sorteerActionMethod(voorbeeldZin);
        String output2 = instantie.sorteerActionMethod("Hallo, Merel ,Hallo");
        assertEquals(output, "vier\n" + "van\n" + "twee\n" + "papier\n"
                + "hoedje\n" + "een\n" + "drie\n");
        assertEquals(output2, "merel\n" + "hallo\n");
    }

    //test om de frequentie methode te testen
    @Test
    public void testFrequentieActionMethod() {
        String output = instantie.frequentieActionMethod(voorbeeldZin);
        String output2 = instantie.frequentieActionMethod("Hallo, Merel ,Hallo");
        assertEquals(output, "papier:	1\n" + "twee:	2\n" + "drie:	2\n" + "vier:	2\n" + "een:	2\n"
                + "hoedje:	3\n" + "van:	3\n");
        assertEquals(output2, "merel:	1\n" + "hallo:	2\n");
    }

    //Test om de concordatie methode te testen.
    @Test
    public void testConcordatieActionMethod() {
        String output = instantie.concordatieActionMethod(voorbeeldZin);
        String output2 = instantie.concordatieActionMethod("Hallo, Merel ,Hallo");
        assertEquals(output, "drie: [1, 3]\n" + "een: [1, 3]\n" + "hoedje: [2, 4]\n"
                + "papier: [4]\n" + "twee: [1, 3]\n" + "van: [2, 4]\n" + "vier: [1, 3]\n");
        assertEquals(output2, "hallo: [1]\n" + "merel: [1]\n");
    }
}
