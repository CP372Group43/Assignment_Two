
<<<<<<< HEAD
import java.io.* ;
import java.net.* ;
import java.util.* ;


=======
>>>>>>> 16c4a8dc3d715211c75a8e3b1b20320e56f3229a


public class Sender implements Runnable{
	private int hostport,hostackport,timeout;
	private String datafile,hostaddress;
	DatagramSocket serversocket = null;
	DatagramSocket acksocket = null;
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
			this.serversocket = new DatagramSocket(3000);
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
		this.run();
	}

	public void run() {
    	System.out.print("Sender On \n");
    	try {
    		
    		//System.out.println(this.acksocket.getInetAddress());
    		//System.out.println(this.serversocket.getInetAddress());

    	}catch(Exception e){
    		e.printStackTrace(System.out);
    	}
    	while(true) {
    	try {

			new Thread(new UdpSender(this.serversocket,this.datafile,this.timeout,this.acksocket));
		}catch(Exception e) {
			System.out.println("???");
			
    		e.printStackTrace(System.out);
    		this.serversocket.close();
    		this.acksocket.close();
			System.exit(1);
    	}
    	}

	}
}
