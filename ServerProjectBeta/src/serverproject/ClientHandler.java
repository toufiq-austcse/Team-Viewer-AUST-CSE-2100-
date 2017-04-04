/**
 *@Aminur Rahman Nov 30 2014
 * aminur_3095@outlook.com
 */
package serverproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable
{
    public final Socket socketInstance;
    public ObjectInputStream inputFromClient;
    public ObjectOutputStream outToClient;
    private String[] clientMsg;
    private Character user;
    private Character sender;
    private String onlineUserList="";
    private ClientHandler clientRequested;
    public ClientHandler(Socket s)
    {
        socketInstance = s;
    }
    
    @Override
    public void run()
    {
        try
        {
           outToClient = new ObjectOutputStream(socketInstance.getOutputStream());
           outToClient.flush();
            inputFromClient = new  ObjectInputStream(socketInstance.getInputStream());
            

          /*  while (true)
            {      
                
                clientMsg = (inputFromClient.readLine()).split(":");
               
                for(Map.Entry<Character, ClientHandler> instance : ServerProject.clientHM.entrySet())
                {
                    clientRequested = instance.getValue();
                    if(clientRequested.socketInstance == socketInstance)
                    {
                        sender=instance.getKey();
                        break;
                    }
                }
                
                if(clientMsg[0].equalsIgnoreCase("server"))
                {
                    if(clientMsg[1].equalsIgnoreCase("online user list"))
                    {
                        onlineUserList="";
                        for(Map.Entry<Character, ClientHandler> instance : ServerProject.clientHM.entrySet())
                        {
                            ClientHandler clIns = instance.getValue();
                            user=instance.getKey();
                            if(clIns.socketInstance != socketInstance)
                                onlineUserList+=user+" ";
                        }
                        if(onlineUserList.length()==0)
                            clientRequested.outToClient.writeBytes("server: "+"None"+"\n");
                        else
                            clientRequested.outToClient.writeBytes("server: "+onlineUserList+"\n");
                    }
                    if(clientMsg[1].equalsIgnoreCase("quit"))
                    {
                        clientRequested.outToClient.writeBytes("server: "+"You're logging out mate!"+"\n");
                        ServerProject serverObj = new ServerProject();
                        serverObj.CloseConnection(sender);
                    }
                }
                else
                {    
                    if(clientMsg[0].equalsIgnoreCase("All"))
                    {
                        for(Map.Entry<Character, ClientHandler> instance : ServerProject.clientHM.entrySet())
                        {
                            ClientHandler clIns = instance.getValue();
                            if(clIns.socketInstance != socketInstance)
                                clIns.outToClient.writeBytes(sender+": "+clientMsg[1]+"\n");
                        }
                    }
                    else
                    {
                        for(Map.Entry<Character, ClientHandler> instance : ServerProject.clientHM.entrySet())
                        {
                            user = instance.getKey();
                            if(0==user.compareTo((clientMsg[0]).charAt(0)))
                            {
                                ClientHandler clIns = instance.getValue();
                                if(clIns.socketInstance != socketInstance)
                                    clIns.outToClient.writeBytes(sender+": "+clientMsg[1]+"\n");
                                break;
                            }
                        }
                    }
                }
            }*/
        }   
        catch (Exception e)

        {
            System.err.println("Error:"+e);
        }  
    }
}