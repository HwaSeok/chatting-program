package 채팅프로그램_symmetric;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener{
   private JTextArea jta = new JTextArea(40,25);
   private JTextField jtf = new JTextField(25);
   private ClientBackground client = new ClientBackground();
   
   public ClientGUI(){
       
       add(jta, BorderLayout.CENTER);
       add(jtf, BorderLayout.SOUTH);
       jtf.addActionListener(this);
       
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
       setBounds(800, 100, 400, 600);
       setTitle("클라이언트부분");
       
       client.setGui(this);
       client.connect();
   }
   public static void main(String[] args) {
       new ClientGUI();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   Symmetric_Key s = new Symmetric_Key();
       String msg = jtf.getText() ;
       String msg2 = s.Encrypt(msg, ClientBackground.key);
       jta.append("Client : " + msg+"\n");
       try {
           client.getOut().writeUTF(msg2+"\n");
       } catch (IOException e1){ 
           e1.printStackTrace();
       }
       jtf.setText("");

   }
   public void appendMsg(String msg) {
       jta.append(msg);
   }

}