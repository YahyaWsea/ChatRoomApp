
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatHandler extends Thread {
    
    PrintStream ps;
    DataInputStream dis;
    static Vector <ChatHandler> clients = new Vector<ChatHandler>();
    
    public ChatHandler(Socket s) {
        try {
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            clients.add(this);
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void run(){
        while(true){
            try {
                String str = dis.readLine();
                sendMessageToAll(str);
            } catch (IOException ex) {
                Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
    void sendMessageToAll(String msg){
        for(ChatHandler ch : clients){
            ch.ps.println(msg);
        }
    }
}
