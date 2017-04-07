/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmysz_000
 */
public class Group {
    
    private final ArrayList<Person> listMembers = new ArrayList<>();
    
    public void addPerson (Person person) {
        listMembers.add(person);
    }
    public List<Person> getList() {
        return new ArrayList<>(listMembers);
    }
    
}
