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
public class Message {
    
    private String from;
    private String [] to ;
    private String [] cc ;
    private String mainBody;
    private String subject;
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String newFrom) {
        this.from = newFrom;
    }
    public String[] getTo() {
        return to;
    }
    public void setTo(String[] to) {
        this.to = to;
    }
    public String[] getCc() {
        return cc;
    }
    public void setCc(String[] cc) {
        this.cc = cc;
    }
    public String getMainBody() {
        return mainBody;
    }
    public void setMainBody(String body) {
       mainBody = body;
    }
    public String getSubject () {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }    
}
