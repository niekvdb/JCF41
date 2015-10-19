/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableTreeTableView;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;

/**
 *
 * @author Niek
 */
public class Department {

    private String name;
    private ArrayList<Employee> employees;
    private ObservableList<Employee> observEmployees;
    private Department HeadDepartment;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.observEmployees = FXCollections.observableArrayList(employees);
    }

    public Department(String name, ObservableList<Employee> emps) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.observEmployees = emps;
    }

    /**
     * Get methode om de naam op te halen
     *
     * @return een string met de departmentnaam
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get methode om de hoofdafdeling op te halen
     *
     * @return een afdeling
     */
    public Department getHeadDepartment() {
        return this.HeadDepartment;
    }

    /**
     * Set methode om de hoofdafdeling te setten
     *
     * @param headDepartment hoofdafdeling
     */
    public void setHeadDepartment(Department headDepartment) {
        this.HeadDepartment = headDepartment;
    }

    /**
     * Get methode om de lijst van werknemers op te halen
     *
     * @return lijst van employees
     */
    public ObservableList<Employee> getEmployees() {
        return this.observEmployees;
    }

    /**
     * Methode om een werknemer toe te voegen
     *
     * @param e een werknemer
     */
    public void addEmployee(Employee e) {
        this.observEmployees.add(e);
    }

    /**
     * Set methode om de naam te setten
     *
     * @param name een string met de naam
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
