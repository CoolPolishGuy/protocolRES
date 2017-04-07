/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3.model;

/**
 *
 * @author wmysz_000
 */
public class Person {
    
    private String lastName;
    private String firstName;
    private String mail;
    
    public Person(String mail) {
        this.mail = mail;
        /*int pos1 = mail.indexOf(".");
        int pos2 = mail.indexOf("@");
        this.firstName = mail.substring(0,pos1);
        this.lastName = mail.substring(pos1+1,pos2);*/
    }
    
    public Person (String lasName ,String firstName,String mail) {
        this.lastName = lasName;
        this.firstName = firstName;
        this.mail = mail;
    }
    
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMail() {
        return mail;
    }
}
