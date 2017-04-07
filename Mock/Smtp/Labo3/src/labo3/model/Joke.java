/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author wmysz_000
 */
public class Joke {
    
    private Person senderEmail;
    private List<Person> sendTo = new ArrayList<>();
    private List<Person> sendCC = new ArrayList<>();
    private String message;
    
    public Person getSender() {
        return senderEmail;
    }
    public void setSender(Person sender) {
         senderEmail= sender;
    }
    public String getMessage() {
        return message;
    }
    public void SetMessage(String message) {
        this.message = message;
    }
    public List<Person> getSendto() {
        return new ArrayList<>(sendTo);
    }
    public List<Person> getSendCC() {
        return new ArrayList<>(sendCC);
    }
    public void addPeopleCC(List<Person> cc) {
        sendCC.addAll(cc);
    }
    public void addPeopleTo(List<Person> to) {
        sendTo.addAll(to);
    }
    public Message createMail() {
        Message message = new Message();
        
        message.setMainBody(this.message + "\r\n" + senderEmail.getFirstName());
        int i = 0;
        String[] peopleTo = new String [sendTo.size()];
        for(Person victim : sendTo) {
            peopleTo[i] = victim.getMail() + " ";
            ++i;
        }
        message.setTo(peopleTo);
        
        int k = 0;
        String[] peopleCc = new String [sendCC.size()];
        for(Person victim : sendCC) {
            peopleCc[k] = victim.getMail() + " ";
            ++k;
        }
        message.setCc(peopleCc);
        message.setFrom(senderEmail.getMail());
        return message;
    }
}
