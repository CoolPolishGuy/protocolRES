/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *@author Myszkorowski et Zanone
 */
package labo3.configurations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.spi.DirStateFactory;
import labo3.model.Person;


public class Configurator {
    
    private int port;
    private String smtpServer;
    private int numberGroup;
    private List<Person> peopleCC;
    private final List<Person> peopleRecieving;
    private final List<String> messages;
    
    public Configurator() throws IOException {
        loadconfigs("D:\\HEIG4semestre\\RES\\Mock\\Smtp\\Labo3\\src\\labo3\\configurations\\config.properties");
        messages = loadmessages("D:\\HEIG4semestre\\RES\\Mock\\Smtp\\Labo3\\src\\labo3\\configurations\\messages.utf8");
        peopleRecieving = loadpeople("D:\\HEIG4semestre\\RES\\Mock\\Smtp\\Labo3\\src\\labo3\\configurations\\people.utf8");
        
        
    }
    private void loadconfigs(String fileConfig) throws IOException {
        FileInputStream file = new FileInputStream(fileConfig);
        
        Properties prop = new Properties();
        prop.load(file);
        this.smtpServer = prop.getProperty("smtpServeurAdress");
        this.port = Integer.parseInt(prop.getProperty("smtpServerPort"));
        this.numberGroup = Integer.parseInt(prop.getProperty("numberGroup"));
        this.peopleCC = new ArrayList<>();
        String cc = prop.getProperty("witnessToCc");
        String [] peoplecc = cc.split(",");
        
        for(String email : peoplecc) {
            this.peopleCC.add(new Person(email));
        } 
        
    }
    private List<Person> loadpeople(String filePeople) throws IOException { 
        FileInputStream file = new FileInputStream(filePeople);
        InputStreamReader fileRead = new InputStreamReader(file,"UTF-8");
        BufferedReader buffer = new BufferedReader(fileRead);
        List<Person> peopleMails = new ArrayList<>();
        String mail = buffer.readLine();
        while(mail != null) {        
            peopleMails.add(new Person(mail));
            mail = buffer.readLine();
        }
        
        return peopleMails;
        
    }
        public List<String> getMessages() {
        return messages;
    }
    public int getNumberGroup()  {
        return numberGroup;
    }
    public  List<Person> getPeopleCc () {
        return peopleCC;
    }
    public  List<Person> getPeopleRecieving () {
        return peopleRecieving;
    }
    private List<String> loadmessages(String fileMessages) throws IOException {
        
        List<String> messages =new ArrayList<>();
        //Reading the files
        FileInputStream file = new FileInputStream(fileMessages);
        InputStreamReader fileRead = new InputStreamReader(file,"UTF-8");
        BufferedReader buffer = new BufferedReader(fileRead);
        String message = buffer.readLine();     
        while(message != null){
            StringBuilder messageMail= new StringBuilder();
            //seperating the different emails
            while(message != null &&(!message.equals("=="))) {
                messageMail.append(message);
                messageMail.append("\r\n");
                message = buffer.readLine();
            }         
            messages.add(messageMail.toString());
            message = buffer.readLine();
        }           
        return messages;
    } 

}
