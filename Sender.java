import java.io.* ;
import java.net.* ;
import java.util.* ;



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
		new Sender(hostaddr,seqport,ackport,sendfile,time);	}
	public Sender(String hostaddr,int seqport,int ackport, String sendfile, int time) {
		
		this.hostaddress=hostaddr;
		this.hostport=seqport;
		this.hostackport=ackport;
		this.datafile=sendfile;
		this.timeout=time;
		
		this.run();
	}

	public void run() {
    	System.out.print("Sender On \n");
    	try {
    		this.serversocket = new DatagramSocket(this.hostport);
    		this.acksocket= new DatagramSocket(this.hostackport,InetAddress.getByAddress(this.hostaddress.getBytes()));
    		System.out.println(this.acksocket.getInetAddress());
    		System.out.println(this.serversocket.getInetAddress());

    	}catch(Exception e){
    		e.printStackTrace(System.out);
    	}
    	while(true) {
    		try {
    			new Thread(new UdpSender(this.serversocket,this.datafile,this.timeout,this.acksocket));
    		}catch(Exception e) {
    			System.out.println(e.getMessage());
        		e.printStackTrace(System.out);
        		this.serversocket.close();
        		this.acksocket.close();
    			System.exit(1);
    		}
    	}

	}
}
