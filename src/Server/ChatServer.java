/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {
    
    ServerSocket SS ;
    
    public ChatServer(){
        try { 
            SS = new ServerSocket(7777);
            while(true){
                Socket s = SS.accept();
                new ChatHandler(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
