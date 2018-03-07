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
public class Receiver extends JFrame implements ActionListener {
	
	public static final int WIDTH = 300;
    public static final int HEIGHT = 455;
    
    public static JTextField type_text_field;
    public static JTextField host_text_field;
    public static JTextField ackport_text_field;
    public static JTextField port_text_field;
    public static JTextField body_text_area;
    public static JTextField timeout_text_field;
    public static JTextArea response_text_area;
    public static JButton connect_button;

    StringReader read = null;
	public Socket ReceiverSocket = null;
	public static void main(String[] args) {
		Receiver Reciever = new Receiver();
        Reciever.setVisible(true);
	}
	
	public Receiver() {
		// setting up the JFrame
		setTitle("Receiver");
		setSize(WIDTH, HEIGHT);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setResizable(false);

    		// init our wrapper panel
        	JPanel wrapper_panel = new JPanel();
    		
        	// receiver IP field
        	JPanel receiver_ip_panel = new JPanel();
        	receiver_ip_panel.add(new JLabel("Receiver IP: "));
    		host_text_field = new JTextField("", 10);
    		host_text_field.setBackground(Color.WHITE);
    		receiver_ip_panel.add(host_text_field);
    		
    		// receiver port field
        	JPanel receiver_port_panel = new JPanel();
        	receiver_port_panel.add(new JLabel("Receiver Port: "));
    		port_text_field = new JTextField("", 10);
    		port_text_field.setBackground(Color.WHITE);
    		receiver_port_panel.add(port_text_field);
    		
    		// sender ack port field
        	JPanel sender_ack_port = new JPanel();
        	sender_ack_port.add(new JLabel("Sender Ack Port: "));
    		ackport_text_field = new JTextField("",10);
    		ackport_text_field.setBackground(Color.WHITE);
    		sender_ack_port.add(ackport_text_field);

    		// file name
        	JPanel file_panel = new JPanel();
        	file_panel.add(new JLabel("File Name: "));
    		body_text_area = new JTextField("", 10);
    		body_text_area.setBackground(Color.WHITE);
    		file_panel.add(body_text_area);
    		
    		// timeout 
        	JPanel timeout_panel = new JPanel();
        	timeout_panel.add(new JLabel(" Timeout: "));
    		timeout_text_field = new JTextField("",10);
    		timeout_text_field.setBackground(Color.WHITE);
    		timeout_panel.add(timeout_text_field);

    		// response
        	JPanel response_panel = new JPanel();
    		response_text_area = new JTextArea(10, 17);
    		response_text_area.setEditable(false);
    		response_text_area.setBackground(Color.WHITE);
    		response_text_area.setLineWrap(true);
    		response_text_area.setWrapStyleWord(true);
    		JScrollPane response_scroll_panel = new JScrollPane(response_text_area);
    		response_panel.add(new JLabel("Response: "));
    		response_panel.add(response_scroll_panel);
    		
    		// add Send Button
        	JPanel actions_panel = new JPanel();
        JButton send_button = new JButton("Send"); 
        send_button.setActionCommand("send");
        send_button.addActionListener(this);
        actions_panel.add(send_button); 
    		
    		// laying out the wrapper pannel
    		wrapper_panel.add(receiver_ip_panel);
    		wrapper_panel.add(receiver_port_panel);
    		wrapper_panel.add(sender_ack_port);
    		wrapper_panel.add(file_panel);
    		wrapper_panel.add(timeout_panel);
        wrapper_panel.add(actions_panel);
        wrapper_panel.add(response_panel);
    		
    		add(wrapper_panel);
    		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}