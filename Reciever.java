import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import java.io.*;
import java.net.*;


@SuppressWarnings("serial")
public class Reciever extends JFrame implements ActionListener {
	
	public static final int WIDTH = 500;
    public static final int HEIGHT = 350;
    
    public static JTextField type_text_field;
    public static JTextField host_text_field;
    public static JTextField ackport_text_field;
    public static JTextField port_text_field;
    public static JTextField body_text_area;
    public static JTextArea response_text_area;
    public static JButton connect_button;

    StringReader read = null;
	public Socket RecieverSocket = null;
	public static void main(String[] args) {
		Reciever Reciever = new Reciever();
        Reciever.setVisible(true);
	}
	
	public Reciever() {
		// setting up the JFrame
		setTitle("Reciever");
		setSize(WIDTH, HEIGHT);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setResizable(false);

    		// init all our panels
    		JPanel server_panel = new JPanel();
    		JPanel port_panel = new JPanel();
        	JPanel message_panel = new JPanel();
        	JPanel response_panel = new JPanel();
        	JPanel wrapper_panel = new JPanel();
        	JPanel actions_panel = new JPanel();
    		
        	// host field
    		host_text_field = new JTextField("", 10);
    		host_text_field.setBackground(Color.WHITE);
    		
    		
    		// port field
    		port_text_field = new JTextField("", 10);
    		port_text_field.setBackground(Color.WHITE);
    		
    		ackport_text_field = new JTextField("",10);
    		ackport_text_field.setBackground(Color.WHITE);
    		
    		// body field
    		body_text_area = new JTextField("", 10);
    		body_text_area.setBackground(Color.WHITE);
    		
    		// response text area
    		response_text_area = new JTextArea(10, 20);
    		response_text_area.setEditable(false);
    		response_text_area.setBackground(Color.WHITE);
    		response_text_area.setLineWrap(true);
    		response_text_area.setWrapStyleWord(true);
    		JScrollPane response_scroll_panel = new JScrollPane(response_text_area);
    		
    		// adding labels and inputs to panels
    		server_panel.add(new JLabel("Sender IP: "));
    		server_panel.add(host_text_field);
    		port_panel.add(new JLabel("Reciever Port: "));
    		port_panel.add(port_text_field);
    		port_panel.add(new JLabel("Sender ACK Port: "));
    		port_panel.add(ackport_text_field);
    		message_panel.add(new JLabel("File Name: "));
    		message_panel.add(body_text_area);
    		response_panel.add(new JLabel("Response: "));
    		response_panel.add(response_scroll_panel);
    		wrapper_panel.add(server_panel, BorderLayout.NORTH);
    		wrapper_panel.add(port_panel, BorderLayout.NORTH);
    		wrapper_panel.add(message_panel, BorderLayout.CENTER);
    		
    		// add Send Button
        JButton send_button = new JButton("Send"); 
        send_button.setActionCommand("send");
        send_button.addActionListener(this);
        actions_panel.add(send_button, BorderLayout.WEST); 
        
        wrapper_panel.add(response_panel, BorderLayout.CENTER);
        wrapper_panel.add(actions_panel, BorderLayout.CENTER);
    		
    		add(wrapper_panel);
    		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}