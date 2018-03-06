import java.io.* ;
import java.net.* ;
import java.util.* ;

public class UdpSender implements Runnable{

	String file;
	int timeout;
	String seqnum,isEot;
	DatagramPacket packet = null;
	DatagramPacket ack=null;
	DatagramSocket seqport = null;
	DatagramSocket ackport = null;
	byte[] ackbuf = new byte[4];
	byte[] buf = new byte[124];
	public UdpSender(DatagramSocket s,String file, int timeout,DatagramSocket ackport){
		this.file=file;
		this.timeout=timeout;
		this.ackport=ackport;
		this.seqport=s;
		this.seqnum="0";
		this.isEot="0";
	}
	public void run() 	{
		
		
		try {
			FileInputStream reader = new FileInputStream(file);
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
		int offsetlen;
		boolean isRun = true;
		while(isRun) {
			seqoffset= this.seqnum.getBytes();
			eotoffset= this.isEot.getBytes();
			offsetlen= seqoffset.length+ eotoffset.length;
			if((bytestr=reader.read(this.buf, offsetlen, 124-offsetlen))!=-1) {
				
			try {
				this.packet = new DatagramPacket(buf,buf.length,InetAddress.getByName("localhost"),0020);
				this.seqport.send(packet);
				this.seqnum+=1;
				this.timeout();
			}catch(Exception e) {
        		e.printStackTrace(System.out);

				isRun=false;
			}
			}else {
				this.isEot="1";
				this.timeout();
				seqport.close();
				ackport.close();
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
