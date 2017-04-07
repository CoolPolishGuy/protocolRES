/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import labo3.configurations.Configurator;
import labo3.model.Group;
import labo3.model.Joke;
import labo3.model.Person;
import labo3.smtp.SmtpClient;


public class Labo3 {
    
    private final Configurator config;
    
    public Labo3(Configurator config) {
        this.config = config;
    }
    public List<Group> groupCreator(int nbGroup,List<Person> peopleSendto) {
        List<Group> groups = new ArrayList<>();
        List<Person> victims = new ArrayList<>(peopleSendto);
        Collections.shuffle(victims);
        for (int i = 0; i < nbGroup ;++i) {
            Group gr = new Group();
            groups.add(gr);
        }
        int numerGroup = 0;
        Group target;
        while(victims.size()>0) {
            target =groups.get(numerGroup);
            numerGroup = (numerGroup + 1) % groups.size();
            Person p = victims.remove(0);
            target.addPerson(p);
        }
       return groups; 
    }
    public List<Joke> jokeCreator() {
        int numberGroups = config.getNumberGroup();
        List<Person> listpeopleRecieving = config.getPeopleRecieving();
        
        int numberOfPeople = listpeopleRecieving.size();
        if(numberOfPeople/numberGroups < 3) {
            System.out.println("NOT ENOUGH PEOPLE");
        }
        List<String> messages = config.getMessages();
        
        List<Group> groups = groupCreator(numberGroups, listpeopleRecieving);
        
        List<Joke> jokes = new ArrayList<>();
        int compteurMessage = 0;
        for(Group gro : groups) {
            Joke joke = new Joke();
                 
            List<Person> people = gro.getList();
            Collections.shuffle(people);
            Person sender = people.remove(0);
            
            joke.setSender(sender);
            joke.addPeopleCC(config.getPeopleCc());
            joke.addPeopleTo(listpeopleRecieving);
            String message = messages.get(compteurMessage);
            compteurMessage = (compteurMessage +1) % messages.size();
            joke.SetMessage(message); 
            jokes.add(joke);
        }
        return jokes;
        
    }
    public static void main(String [] args) {
        
        try {
            Configurator config = new Configurator();
            Labo3 joking = new Labo3(config);
            List<Joke> listeToDo = new ArrayList<>(joking.jokeCreator());
            
            SmtpClient client = new SmtpClient("localhost", 25);
            client.connect();
            
            for(Joke prank : listeToDo) {
                System.out.println("hello");
                client.sendMessage(prank.createMail());
            }
            client.disconnect();   
        } catch(IOException eio) {
            System.out.println("ERROR_final");
        } 
    }   
}
