/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private static String voorbeeldTekst1 = "";
    private Calendar cal;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @BeforeClass
    public static void setUpClass() {
        instantie = new WoordenController();
        voorbeeldZin = "Een, twee, drie, vier\n" + "Hoedje van, Hoedje van\n"
                + "Een, twee, drie, vier\n" + "Hoedje van papier";

        for (int i = 0; i < 10000; i++) {
            voorbeeldTekst1 += " A B C D E ";
        }
    }

    //test om de aantal methode te testen
    @Test
    public void testAantalActionsMethod() {
        Calendar begintijd = Calendar.getInstance();
        String output = instantie.aantalActionsMethod(voorbeeldZin);
        String output2 = instantie.aantalActionsMethod("Hallo, Merel ,Hallo");
        String output3 = instantie.aantalActionsMethod(voorbeeldTekst1);
        assertEquals(output, "Totaal aantal woorden: 15\n"
                + "Aantal verschillende woorden: 7");
        assertEquals(output2, "Totaal aantal woorden: 3\n"
                + "Aantal verschillende woorden: 2");
        assertEquals(output3, "Totaal aantal woorden: 50000\n"
                + "Aantal verschillende woorden: 5");
        Calendar eindtijd = Calendar.getInstance();

        long verschil = eindtijd.getTimeInMillis() - begintijd.getTimeInMillis();
        if (verschil > 75) {
            fail("De test heeft er te lang over gedaan");

        } else {
            System.out.println(verschil);
        }
    }

    //test om de sorteer methode te testen
    @Test
    public void testSorteerActionMethod() {
        Calendar begintijd = Calendar.getInstance();
        String output = instantie.sorteerActionMethod(voorbeeldZin);
        String output2 = instantie.sorteerActionMethod("Hallo, Merel ,Hallo");
        String output3 = instantie.sorteerActionMethod(voorbeeldTekst1);
        assertEquals(output, "vier\n" + "van\n" + "twee\n" + "papier\n"
                + "hoedje\n" + "een\n" + "drie\n");
        assertEquals(output2, "merel\n" + "hallo\n");
        assertEquals(output3, "e\n" + "d\n" + "c\n" + "b\n" + "a\n");
        Calendar eindtijd = Calendar.getInstance();
        long verschil = eindtijd.getTimeInMillis() - begintijd.getTimeInMillis();
        if (verschil > 75) {
            fail("De test heeft er te lang over gedaan");

        } else {
            System.out.println(verschil);
        }
    }

    //test om de frequentie methode te testen
    @Test
    public void testFrequentieActionMethod() {
        Calendar begintijd = Calendar.getInstance();
        String output = instantie.frequentieActionMethod(voorbeeldZin);
        String output2 = instantie.frequentieActionMethod("Hallo, Merel ,Hallo");
        String output3 = instantie.frequentieActionMethod(voorbeeldTekst1);
        assertEquals(output, "papier:	1\n" + "twee:	2\n" + "drie:	2\n" + "vier:	2\n" + "een:	2\n"
                + "hoedje:	3\n" + "van:	3\n");
        assertEquals(output2, "merel:	1\n" + "hallo:	2\n");
        assertEquals(output3, "a:	10000\n" + "b:	10000\n" + "c:	10000\n" + "d:	10000\n" + "e:	10000\n");
        Calendar eindtijd = Calendar.getInstance();
        long verschil = eindtijd.getTimeInMillis() - begintijd.getTimeInMillis();
        if (verschil > 75) {
            fail("De test heeft er te lang over gedaan");

        } else {
            System.out.println(verschil);
        }
    }

    //Test om de concordatie methode te testen.
    @Test
    public void testConcordatieActionMethod() {
        Calendar begintijd = Calendar.getInstance();
        String output = instantie.concordatieActionMethod(voorbeeldZin);
        String output2 = instantie.concordatieActionMethod("Hallo, Merel ,Hallo");
        assertEquals(output, "drie: [1, 3]\n" + "een: [1, 3]\n" + "hoedje: [2, 4]\n"
                + "papier: [4]\n" + "twee: [1, 3]\n" + "van: [2, 4]\n" + "vier: [1, 3]\n");
        assertEquals(output2, "hallo: [1]\n" + "merel: [1]\n");
        Calendar eindtijd = Calendar.getInstance();
        long verschil = eindtijd.getTimeInMillis() - begintijd.getTimeInMillis();
        if (verschil > 75) {
            fail("De test heeft er te lang over gedaan");

        } else {
            System.out.println(verschil);
        }

    }
}
