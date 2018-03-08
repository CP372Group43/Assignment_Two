

import java.io.* ;
import java.net.* ;
import java.util.* ;




public class Sender implements Runnable{
	private int hostport,hostackport;
	private String datafile,hostaddress;
	DatagramSocket ackSocket = null;
<<<<<<< HEAD
	DatagramSocket serversocket=null;
	long timeout;
=======
>>>>>>> parent of 496132e... acks
	public static void main(String[] args) {
		int seqport,ackport,time;
		String sendfile,hostaddr;
		hostaddr= args[0];
		seqport=Integer.parseInt(args[1]);
		ackport=Integer.parseInt(args[2]);
		sendfile=args[3];
		time=Integer.parseInt(args[4]);
		new Sender(hostaddr,seqport,ackport,sendfile,time);	
	}
	
	public Sender(String hostaddr,int seqport,int ackport, String sendfile, long time) {
		this.hostaddress=hostaddr;
		this.hostport=seqport;
		this.hostackport=ackport;
		this.datafile=sendfile;
		this.timeout=time;
		try {
<<<<<<< HEAD
			this.serversocket = new DatagramSocket();
			this.ackSocket=new DatagramSocket(this.hostackport);
=======
			this.ackSocket = new DatagramSocket(2222);
>>>>>>> parent of 496132e... acks
			this.run();

		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}
	public void run() {
	    	System.out.print("Sender On \n");
		try {			
			while(true) {
				// wait for next transfer request from receiver
				// send receiver the data
<<<<<<< HEAD
				new UdpSender(this.serversocket, this.datafile,this.timeout,this.ackSocket,this.hostport,this.hostaddress);
=======
				new UdpSender(this.ackSocket, this.datafile,this.timeout);
>>>>>>> parent of 496132e... acks
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
}
