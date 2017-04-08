# Laboratoire SMTP

### Introduction
In this pratical exercice the the purpose is to create a prank generator 
in order to send email through a smtp protocol to other students. As we could imagine 
in an other purpose we could try to actually send fake emails to some email adress.

However the main purpose in this case is to see how the implementation allows us to send 
different emails to different people.

### Setup the environnement

In order to be able to send emails to fake server. We need to install a mock server which can be download from this page:
https://github.com/tweakers-dev/MockMock
Personnaly i have directly downloaded the .jar

Once the file is downloaded you can launch it by simply going to file where the mockmock.jar is, and you type the following command 

`java -jar mockmock.jar`:

Now the server is listening on the port that you setup , by the default it is the 8282. You can open a browser and type 

`localhost:8282`: you will directly be on the smtp server


### The application itself

We are ready to send some pranks to the server, all left to do is for the application to connect to this address and send some emails

In our case , we have choose the have the following main class that lauches the programm
```
public static void main(String [] args) {
        SmtpClient client = new SmtpClient("localhost", 25);
         
        try {
            client.connect();
            
            Configurator config = new Configurator();
            Labo3 joking = new Labo3(config);
            List<Joke> listeToDo = new ArrayList<>(joking.jokeCreator());
   
            for(Joke prank : listeToDo) {
                client.sendMessage(prank.createMail());
            }
            client.disconnect();   
        } catch(IOException error) {
            System.out.println("ERROR_final:" + error);
        } 
    }
```
what we have here is a SmtpClient that we need to create in order to connect the right port and server then all we have to do is create a prank with the right configurations which takes the right files with the correct encoding and parse them into arrays so that we can easly access them and work with.
Once every group has been created we need to go through the list of jokes and send them to the group randomly defined.
Once every email has been sent. We can disconnect from the channel because we have sent every emails that we wanted.