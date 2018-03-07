

import java.io.* ;
import java.net.* ;
import java.util.* ;




public class Sender implements Runnable{
	private int hostport,hostackport,timeout;
	private String datafile,hostaddress;
	DatagramSocket ackSocket = null;
	public static void main(String[] args) {
		int seqport,ackport,time;
		String sendfile,hostaddr;
		hostaddr= args[0];
		seqport=Integer.parseInt(args[1]);
		ackport=Integer.parseInt(args[2]);
		sendfile=args[3];
		time=Integer.parseInt(args[2]);
		new Sender(hostaddr,seqport,ackport,sendfile,time);	
	}
	
	public Sender(String hostaddr,int seqport,int ackport, String sendfile, int time) {
		this.hostaddress=hostaddr;
		this.hostport=seqport;
		this.hostackport=ackport;
		this.datafile=sendfile;
		this.timeout=time;
		try {
			this.ackSocket = new DatagramSocket(2222);
			this.run();

		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}
	public void run() {
	    	System.out.print("Sender On \n");
		try {			
			while(true) {
				byte[] buf =new byte[124];
				DatagramPacket packet = new DatagramPacket(buf,buf.length);
				packet.setLength(buf.length);
				 				// wait for next transfer request from receiver
				ackSocket.receive(packet);

				// send receiver the data
				new UdpSender(this.ackSocket, this.datafile,this.timeout);
			}
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
}
