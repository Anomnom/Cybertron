package javaapplication1;

import java.io.*;


public class MyLogger implements com.jcraft.jsch.Logger {
    
    public void rFromFile() //reads form file 
    {
        try {
            
            String proxy_host; //Proxy host from file.
            
            String proxy_port; //Proxy port from file.
            
            FileInputStream file = new FileInputStream("conf.fil");
            
            DataInputStream in = new DataInputStream(file);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String strLine;
            
            while(( strLine = br.readLine()) !=null)
            {
                if(strLine.contains("proxy_host"))
                {
                 
                   proxy_host =  strLine.replaceAll("proxy_host=", "");
                   
                   System.setProperty("http.proxyHost", proxy_host);
                }
                
                if(strLine.contains("proxy_port"))
                {
                    
                    proxy_port = strLine.replaceAll("proxy_port=", "");
                    
                    System.setProperty("http.proxyPort", proxy_port);
                    
                }
                
            }
            
        } catch(Exception e) {
            
            System.out.println("File not found" + e);
            
        }
        
    }
    
    public void wToFile(String in)
    {
        
    }
    
    static java.util.Hashtable name=new java.util.Hashtable();
    static{
      
        name.put(new Integer(DEBUG), "DEBUG: ");
      
        name.put(new Integer(INFO), "INFO: ");
      
        name.put(new Integer(WARN), "WARN: ");
      
        name.put(new Integer(ERROR), "ERROR: ");
      
        name.put(new Integer(FATAL), "FATAL: ");
      
    }
    
    public boolean isEnabled(int level){
      return true;
    }
    
    public void log(int level, String message){
      System.err.print(name.get(new Integer(level)));
      System.err.println(message);
    }
  }

