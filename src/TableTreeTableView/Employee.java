/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableTreeTableView;

/**
 *
 * @author Niek
 */
public class Employee {

    private String firstname;
    private String lastname;

    public Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Get methode om de voornaam op te halen
     *
     * @return een string die de voornaam bevat
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Set methode om de voornaam te setten
     *
     * @param firstname string met de voornaam
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Set methode om de achternaam te setten
     *
     * @param lastname string met de achternaam
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get methode om de achternaam op te halen
     *
     * @return een string die de achternaam bevat
     */
    public String getLastname() {
        return this.lastname;
    }
}
