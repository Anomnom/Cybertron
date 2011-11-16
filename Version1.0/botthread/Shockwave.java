package botthread;

import java.net.*;

import java.io.*;

import org.jibble.pircbot.*;

import org.jibble.pircbot.User;

import java.util.*;
 

public class Shockwave extends PircBot implements Runnable {
    
    Thread t;
    
    public void run()
    {
        
       
    }
    
    public Shockwave() throws Exception {
        
        setName("Shockwave");
        
        t = new Thread(this,"Shockwave");
        
        setVerbose(true);
                
        connect("irc.glowfish.de");
            
        joinChannel("#bottest");
        
        t.start();
        
    }
    
     public void onMessage(String channel, String sender, String login, String hostname, String message) {
        
        
        
            
            if(message.contains("!ps")) { //Enkel portscannare..
             
            //notera att jag skrev denna avdelning sist. därav högre variablar (0.55)
            
            Scanner result4 = new Scanner(message.toString().replaceAll("\\!ps", ""));
            
            sendMessage("#bottest","------ Starting portscan! --------");
            
            String b3 = result4.next();
            
            int startp = 20;
            
            int endp = 1000; //to not let the bot ping out.
            
            for(int i = startp;i<endp;i++)
            {
               try {
              
                Socket sok = new Socket(b3,i);
                
                sendMessage("#bottest","Port in use : " + i);
                
                sok.close();
                
               } catch(Exception e) {
                   
                   //sendMessage("#bottest","Port closed : " + i); This will flood the chan and kick the bot.
               
               }
            }
            
            sendMessage("#bottest","------ End of portscan! --------");
       
            
            }
            
           else if(message.contains("!shr")) {
            
           Scanner result5 = new Scanner(message.toString().replaceAll("\\!shr", "")); 
           
           String b3 = result5.next();
           
           InetAddress address = null;
           
           sendMessage("#botetst","------ Initialising Hostscan --------");
           
           int b4 = b3.lastIndexOf(".");
           
           String b5 = b3.substring(0, b4+1);
           
           for(int i = 0;i<255;i++)
           {
               
               try {
               
               address = InetAddress.getByName(b5 + i);
               
               sendMessage("#bottest","Checking adress : " + b5 + i);
               
               if(address.isReachable(3000))
               {
                   sendMessage("#bottest", b5 + i + " is up");
               }
               
               else { sendMessage("#bottest", " is unreachable"); }
               
               } catch(Exception e) {
                   
                   sendMessage("#bottest","Error " + e);
               }
           
           }
           sendMessage("#botetst","------ End of Hostscan --------");
        
        }    
           
           else if(message.contains("!shockwave -k")) 
           {
                 
               quitServer();
                 
               this.t.destroy();
                 
           }
            
            
            
     }
     
     
}