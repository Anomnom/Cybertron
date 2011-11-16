
package botthread;

import java.net.*;

import java.io.*;

import org.jibble.pircbot.*;

import org.jibble.pircbot.User;

import java.util.*;

import java.math.*;



public class autobot extends PircBot implements Runnable{
    
     Thread t;
    
     public autobot(String name) throws Exception {
         
        setName(name);
        
        setVerbose(true);
                
        connect("irc.glowfish.de");
            
        joinChannel("#bottest");
        
        t.start();
         
     }
     
    public void run() {
    
        t = new Thread(this,"autobot");
       
    }
    
    public double kglbs(double d)
    {
        
          
            
            double b = d / 0.456789075;
                  
            return b;
        
    }
    
    public double lbskg(double d)
    {
        
        double b = d * 0.456789075;
        
        return b;
        
    }
    
   public void onMessage(String channel, String sender, String login, String hostname, String message) {
       
            if(message.contains("kg") && message.contains(".")) {
            
                Scanner result2 = new Scanner(message.toString().replaceAll("\\D", ""));
          
                double r2 = kglbs(result2.nextDouble());
          
                sendMessage("#bottest","is " + Double.toString(r2) + " lbs");
            
            }
            else if(message.contains("lbs") && message.contains(".")) {
               
                Scanner result3 = new Scanner(message.toString().replaceAll("\\D", ""));
                
                double r3 = lbskg(result3.nextDouble());
                
                sendMessage("#bottest", "is " + Double.toString(r3) + " lbs");
            }
    

   }

}

