package 채팅프로그램_symmetric;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class ServerBackground {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerGUI gui;
    static int key;
    
    public DataOutputStream getOut() {
        return out;
    }
 
    public void setGui(ServerGUI gui) {
        this.gui = gui;
    }
    public void setting(){
        try{
            serverSocket = new ServerSocket(7777);
            Symmetric_Key s = new Symmetric_Key();
            System.out.println("symmetric key값을 입력해 주세요");
        	Scanner in2 = new Scanner(System.in);
            key = in2.nextInt();
            System.out.println("대기중.....");
            socket = serverSocket.accept();
            
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
                        
            while(true){
                String msg = in.readUTF();
                String msg2 = s.Decrypt(msg, key);
                gui.appendMsg("Client : "+msg2+"\n");
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }
}