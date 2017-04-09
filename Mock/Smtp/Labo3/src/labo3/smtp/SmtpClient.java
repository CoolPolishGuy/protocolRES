/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *@author Myszkorowski and Zanone
 */
package labo3.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import labo3.model.Message;

public class SmtpClient {
  
    private Socket socketClient;
    private BufferedReader bufferRead;
    private PrintWriter printer;
    
    private final int serverPort;
    private final String smtpServeur;
    private final String EndofLine = "\r\n";
    
    public SmtpClient(String smtpServeur, int port) {
        this.smtpServeur = smtpServeur;
        this.serverPort = port;
    }
    public void connect() throws IOException {
        socketClient = new Socket(smtpServeur,serverPort);
        printer = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream(),"UTF-8"),true);
        bufferRead = new BufferedReader(new InputStreamReader(socketClient.getInputStream(),"UTF-8"));
     
    }
    public void disconnect() throws IOException{
        printer.write("QUIT" + EndofLine);
        printer.flush();
        bufferRead.close();        
        printer.close();
        socketClient.close();
    }
    public void sendMessage(Message m1) throws IOException {
       
        
        String line = bufferRead.readLine();
        printer.write("EHLO BEGIN" + EndofLine);
        printer.flush();
        line = bufferRead.readLine();
        
        if(!line.startsWith("250")) {
            throw new IOException("PROBLEM OCCURED");
        }
        while(line.startsWith("250-")) {
                line = bufferRead.readLine();           
        }
        
        printer.write("MAIL FROM: " + m1.getFrom() + EndofLine);
               
        printer.flush();
        line = bufferRead.readLine();
        
        for(String emailTo : m1.getTo()) {
            printer.write("RCPT TO: ");
            printer.write(emailTo);
            printer.write(EndofLine);
            printer.flush();
            line = bufferRead.readLine();
        }
        for(String emailTo : m1.getCc()) {
            printer.write("RCPT TO:");
            printer.write(emailTo);
            printer.write(EndofLine);
            printer.flush();
            line = bufferRead.readLine();
        }
        
        printer.write(EndofLine);
        
        printer.write("DATA");
        printer.write(EndofLine);
        printer.flush();
        bufferRead.readLine();
        printer.write("From:" + m1.getFrom() + EndofLine);  
        printer.write("To: " +m1.getTo()[0]);
        for(int j = 1 ; j < m1.getTo().length; ++j) {
            printer.write("," + m1.getTo()[j]);
        }
        printer.write(EndofLine);
        printer.write("Cc: " +m1.getCc()[0]);
        for(int j = 1 ; j < m1.getCc().length; ++j) {
            printer.write("," + m1.getCc()[j]);
        }
        printer.write(EndofLine);
        printer.flush();
        printer.write(m1.getMainBody());     
        printer.write(EndofLine);
        printer.write(".");
        printer.write(EndofLine);
        printer.flush();
        bufferRead.readLine();
              
    }   
}
