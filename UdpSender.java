import java.io.* ;
import java.net.* ;
import java.util.* ;

public class UdpSender implements Runnable{

	String file;
	int timeout;
	Integer seqnum,isEot;
	DatagramPacket packet = null;
	DatagramPacket ack=null;
	DatagramSocket seqport = null;
	DatagramSocket ackport = null;
	byte[] ackbuf = new byte[4];
	byte[] buf = new byte[124];
	public UdpSender(DatagramSocket s,String file, int timeout,DatagramSocket ackport) throws Exception{
		this.file=file;
		this.timeout=timeout;
		this.ackport=ackport;
		this.seqport=s;
		this.seqnum=new Integer(0);
		this.isEot=new Integer(0);
		try {
		this.run();
		}catch(Exception e) {
			throw new Exception();
		}
	}
	public void run(){
		
		
		try {
			FileInputStream reader = new FileInputStream("test.txt");
			SendPacket(reader);
			reader.close();
		}catch(Exception e) {
    		e.printStackTrace(System.out);
		}
	}
	public void SendPacket(FileInputStream reader) throws Exception {
		int bytestr;
		byte[] seqoffset;
		byte[] eotoffset;
		byte[] offdata = new byte[124];
		int offsetlen;
		boolean isRun = true;
		System.out.println("insnd");
		while(isRun) {
			bytestr=reader.read(this.buf);
			String input;
			//this.buf=input.getBytes();
			int lenseq = this.seqnum.toString().getBytes().length;
			System.out.println(this.seqnum.toString().getBytes());
			if(bytestr!=-1 && reader.available()>0) {
			try {				
				ByteArrayOutputStream read = new ByteArrayOutputStream(119);
				read.write(this.buf);
				System.out.println("eotlen"+ this.isEot.toString());
				input=  this.seqnum.toString() +this.isEot.toString()+read.toString();
				System.out.println(input);
				System.out.println(input.getBytes());
				offdata=input.getBytes();
				this.packet = new DatagramPacket(offdata,offdata.length,InetAddress.getByName("localhost"),0123);
				this.seqport.send(this.packet);
				this.seqnum+=1;
				//this.timeout();
			}catch(Exception e) {
        		e.printStackTrace(System.out);

				isRun=false;
			}
			}else {
				System.out.println("endoffile");
				isRun=false;
				this.isEot=1;
				//ByteArrayOutputStream read = new ByteArrayOutputStream(124);
				//read.write(this.buf);
				//input= this.isEot.toString();
				//System.out.println(input);
				//System.out.println(input.getBytes());
				try {
					//this.packet = new DatagramPacket(offdata,offdata.length,InetAddress.getByName("localhost"),0123);
					//this.seqport.send(this.packet);
//				this.timeout();
				}catch(Exception e) {
					throw new Exception();
				}
				
			}
			
		}
	}
	public void timeout() throws Exception{
		long starttime = System.currentTimeMillis();
		boolean isRunning = true;
		while(isRunning) {
			if(System.currentTimeMillis()>=(starttime+this.timeout)) {
				isRunning= false;
			}else {
		try {
			this.ack = new DatagramPacket(ackbuf,ackbuf.length);
			this.ackport.receive(this.ack);
		}catch(Exception e) {
    		e.printStackTrace(System.out);

			throw e;
		}
			}
	}
	}
}
