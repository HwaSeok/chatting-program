package 채팅프로그램_symmetric;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
 
public class ClientBackground {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGUI gui;
    private String ClientName ;
    static int key;
    
    public DataOutputStream getOut() {
        return out;
    }
    public String getClientName() {
        return ClientName;
    }
    public void setGui(ClientGUI gui) {
        this.gui = gui;
    }
    public void connect(){
        try {
        	Symmetric_Key s = new Symmetric_Key();
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("서버에 연결됨");
            
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("symmetric key값을 입력해 주세요");
            key = scanner.nextInt();
            
            while(true){
                String msg = in.readUTF();
                String msg2 = s.Decrypt(msg, key);
                gui.appendMsg("Server : "+msg2+"\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connect();
    }
}