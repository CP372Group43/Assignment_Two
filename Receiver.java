<<<<<<< HEAD

=======
>>>>>>> parent of 496132e... acks


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
import java.nio.*;
import java.nio.channels.FileChannel;
@SuppressWarnings("serial")
public class Receiver extends JFrame implements ActionListener,Runnable {
	
	public static final int WIDTH = 300;
    public static final int HEIGHT = 475;
    
    public static JTextField type_text_field;
    public static JTextField host_text_field;
    public static JTextField senderport_text_field;
    public static JTextField port_text_field;
    public static JTextField body_text_area;
    public static JTextField rec_packets_text_field;
    public static JTextArea response_text_area;
    public static JButton reliable_toggle_button;
    
    public static  Boolean is_reliable = false;
    int total=0,prev;
    Integer inorder = new Integer(0);
    
<<<<<<< HEAD
    public  DatagramSocket seq = null;
    public  DatagramSocket ack=null;
	String host,rcvport,sendport,file;

=======
    public static DatagramSocket seq = null;
    
>>>>>>> parent of 496132e... acks

    StringReader read = null;
	
	public static void main(String[] args) {
		Receiver Reciever = new Receiver();
        Reciever.setVisible(true);
	}
	
	public void run() {
		System.out.println("Run called..");
		byte[] buf =new byte[124];
		byte[] seqbyte = new byte[4];
		byte[] isEot = new byte[1];
		byte[] ackbuf = new byte[4];
		try {
			this.seq=new DatagramSocket(new Integer(this.rcvport));
			this.ack=new DatagramSocket();

			File infile = new File(this.file);
			BufferedWriter writef = new BufferedWriter(new FileWriter(infile));
			ByteArrayOutputStream input= new ByteArrayOutputStream(124);
			DatagramPacket packet = new DatagramPacket(buf,buf.length);
<<<<<<< HEAD
			FileOutputStream stream = new FileOutputStream(this.file);
			DatagramPacket ack = new DatagramPacket(ackbuf,ackbuf.length,InetAddress.getByName(this.host),new Integer(this.sendport));
=======
			FileOutputStream stream = new FileOutputStream("testme.txt");
>>>>>>> parent of 496132e... acks
			while(true) {
				if(this.is_reliable==true) {
				System.out.println("reliable");
				packet.setLength(buf.length);
				this.seq.receive(packet);
				byte[] str = packet.getData();
				System.out.println("str"+str.length);
				seqbyte[0]= str[0];
				seqbyte[1]= str[1];
				seqbyte[2]= str[2];
				seqbyte[3]= str[3];
				isEot[0]=str[4];
				ByteArrayOutputStream readseq = new ByteArrayOutputStream(4);
				ByteArrayOutputStream readeot = new ByteArrayOutputStream(1);
				readseq.write(seqbyte);
				readeot.write(isEot);
				String seqnum=readseq.toString();
				String eot = readeot.toString();
				System.out.println("seqnum"+seqnum);
				System.out.println("eot"+eot);
				
				if(!eot.equals("0") && this.total!=0) {
					stream.write(str, 5, 119);
					System.out.println(str.toString());
					System.exit(1);

				}else{
					stream.write(str, 5, 119);
				}
				ackbuf=readseq.toByteArray();
				this.ack.send(ack);
				this.inorder++;
				this.total++;
			}else if(this.is_reliable==false){
				packet.setLength(buf.length);
				this.seq.receive(packet);
				byte[] str = packet.getData();
				System.out.println("str"+str.length);
				seqbyte[0]= str[0];
				seqbyte[1]= str[1];
				seqbyte[2]= str[2];
				seqbyte[3]= str[3];
				isEot[0]=str[4];
				ByteArrayOutputStream readseq = new ByteArrayOutputStream(4);
				ByteArrayOutputStream readeot = new ByteArrayOutputStream(1);
				readseq.write(seqbyte);
				readeot.write(isEot);
				String seqnum=readseq.toString();
				String eot = readeot.toString();
				System.out.println("seqnum"+seqnum);
				System.out.println("eot"+eot);
			if(this.total%10==0) {
				if(!eot.equals("0") && this.total!=0) {
					stream.write(str, 5, 119);

					System.exit(1);

				}else{
					stream.write(str, 5, 119);

				}
<<<<<<< HEAD
				ackbuf=readseq.toByteArray();
				this.ack.send(ack);
				this.total++;
			}else {
				if(!eot.equals("0") && this.total!=0) {
					stream.write(str, 5, 119);
					System.exit(1);

				}else{
					stream.write(str, 5, 119);
				}
				ackbuf=readseq.toByteArray();
				this.ack.send(ack);
				this.total++;
				this.inorder++;
				}
=======
				total++;
>>>>>>> parent of 496132e... acks
			}
			this.rec_packets_text_field.setText(this.inorder.toString());

		}
			//write.close();
		}catch(FileNotFoundException e){
			this.response_text_area.setText("file not found");
		}catch(Exception e) {
			e.printStackTrace(System.out);

		}
		
	}
	
	public void transfer() {
		this.sendport = senderport_text_field.getText();
		this.host = host_text_field.getText();
		this.rcvport = port_text_field.getText();
		this.file = body_text_area.getText();
		
		try {
			if(this.seq != null) {
				seq.close();
			}
			this.run();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Receiver() {
		// setting up the JFrame
		setTitle("Receiver");
		setSize(WIDTH, HEIGHT);
    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setResizable(false);

    		// init our wrapper panel
        	JPanel wrapper_panel = new JPanel();
    		
        	// sender host field
        	JPanel sender_ip_panel = new JPanel();
        	sender_ip_panel.add(new JLabel("Sender Host: "));
    		host_text_field = new JTextField("", 10);
    		host_text_field.setBackground(Color.WHITE);
    		host_text_field.setText("localhost");
    		sender_ip_panel.add(host_text_field);

    		// sender port field
        	JPanel sender_port = new JPanel();
        	sender_port.add(new JLabel("Sending Port: "));
    		senderport_text_field = new JTextField("",10);
    		senderport_text_field.setBackground(Color.WHITE);
    		senderport_text_field.setText("2222");
    		sender_port.add(senderport_text_field);
    		
    		// receiver ack port field
        	JPanel receiver_port_panel = new JPanel();
        	receiver_port_panel.add(new JLabel("Receiving Port: "));
    		port_text_field = new JTextField("", 10);
    		port_text_field.setBackground(Color.WHITE);
    		port_text_field.setText("3333");
    		receiver_port_panel.add(port_text_field);

    		// file name
        	JPanel file_panel = new JPanel();
        	file_panel.add(new JLabel("File Name: "));
    		body_text_area = new JTextField("", 10);
    		body_text_area.setBackground(Color.WHITE);
    		file_panel.add(body_text_area);
    		
    		// received packets 
        	JPanel rec_packets_panel = new JPanel();
    		rec_packets_text_field = new JTextField("",10);
    		rec_packets_text_field.setBackground(Color.WHITE);
    		rec_packets_text_field.setEditable(false);
    		rec_packets_panel.add(rec_packets_text_field);

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
    		
    		// add actions panel
        	JPanel actions_panel = new JPanel();
    		
    		// add transfer Button
        JButton transfer_button = new JButton("Transfer"); 
        transfer_button.setActionCommand("transfer");
        transfer_button.addActionListener(this);
        
        // add reliable/unreliable Button - default false
        reliable_toggle_button = new JButton("Reliable"); 
        reliable_toggle_button.setActionCommand("reliable_toggle");
        reliable_toggle_button.addActionListener(this);
        
        actions_panel.add(transfer_button); 
        actions_panel.add(reliable_toggle_button); 
    		
    		// laying out the wrapper pannel
    		wrapper_panel.add(sender_ip_panel);
    		wrapper_panel.add(sender_port);
    		wrapper_panel.add(receiver_port_panel);
    		wrapper_panel.add(file_panel);
        wrapper_panel.add(actions_panel);
        wrapper_panel.add(new JLabel("Current # of received in-order packets: "));
		wrapper_panel.add(rec_packets_panel);
        wrapper_panel.add(response_panel);
    		
    		add(wrapper_panel);

	}
	
	public void actionPerformed(ActionEvent e) {
		if("reliable_toggle".equals(e.getActionCommand())) {
			is_reliable = !is_reliable;
			if(is_reliable) {
				reliable_toggle_button.setText("Unreliable");
			} else {
				reliable_toggle_button.setText("Reliable");
			}
		} else if("transfer".equals(e.getActionCommand())) {
			transfer();
		}
	}
	
}