/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;




/**
 *
 * @author Micke
 */


public class JavaApplication1 {
    
 static sqllite sql = new sqllite();
 
 static botClass bot[] = new botClass[7];
 
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Proxy for the ircbot.. 
        
        //System.setProperty("socksProxyHost", "localhost");
            
        //System.setProperty("socksProxyPort", "9050");
           
        try{
            
            for(int i = 0;i<1;i++)
            {    
                
                bot[i] = new botClass("Megatron"+i);
                
            }   
            
            sql.run();
            
            } catch(Exception e) { e.printStackTrace(); }
    
    }
}

