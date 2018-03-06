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
	
	public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
    
    public static JTextField type_text_field;
    public static JTextField host_text_field;
    public static JTextField ackport_text_field;
    public static JTextField port_text_field;
    public static JTextArea body_text_area;
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
    		JPanel server_panel = new JPanel(new FlowLayout());
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
    		// body text area
    		body_text_area = new JTextArea(10, 20);
    		body_text_area.setBackground(Color.WHITE);
    		body_text_area.setLineWrap(true);
    		body_text_area.setWrapStyleWord(true);
    		JScrollPane body_scroll_panel = new JScrollPane(body_text_area);
    		
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
    		server_panel.add(new JLabel("Sender Port: "));
    		server_panel.add(port_text_field);
    		server_panel.add(new JLabel("Sender ACK Port: "));
    		server_panel.add(ackport_text_field);
    		message_panel.add(new JLabel("Request Body: "));
    		message_panel.add(body_scroll_panel);
    		response_panel.add(new JLabel("Response: "));
    		response_panel.add(response_scroll_panel);
    		wrapper_panel.add(server_panel, BorderLayout.NORTH);
    		wrapper_panel.add(message_panel, BorderLayout.CENTER);
    		
    		// add Send Button
        JButton send_button = new JButton("Send"); 
        send_button.setActionCommand("send");
        send_button.addActionListener(this);
        actions_panel.add(send_button); 
        
        // add Connect/Disconnect Button
        connect_button = new JButton("Connect"); 
        connect_button.setActionCommand("connect");
        connect_button.addActionListener(this);
        actions_panel.add(connect_button);
        
        wrapper_panel.add(actions_panel, BorderLayout.CENTER);
        wrapper_panel.add(response_panel, BorderLayout.CENTER);
    		
    		add(wrapper_panel);
    		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}