package 채팅프로그램_symmetric;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
 
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class ServerGUI extends JFrame implements ActionListener{
    
    private JTextArea jta = new JTextArea(40,25);
    private JTextField jtf = new JTextField(25);
    private ServerBackground server = new ServerBackground();
    
    public ServerGUI(){
        
        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("¼­¹öºÎºÐ");
        server.setGui(this);
        server.setting();
    }
    public static void main(String[] args) {
        new ServerGUI();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	Symmetric_Key s = new Symmetric_Key();
        String msg = jtf.getText();
        String msg2 = s.Encrypt(msg,ServerBackground.key);
        jta.append("Server : " +msg+"\n");
        try {
            server.getOut().writeUTF(msg2);
        } catch (IOException e1){ 
            e1.printStackTrace();
        }
        jtf.setText("");
 
    }
    public void appendMsg(String msg) {
        jta.append(msg);
    }
    
 
}
