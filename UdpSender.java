

import java.io.* ;
import java.net.* ;
import java.nio.ByteBuffer;
import java.util.* ;

public class UdpSender implements Runnable{

	String file;
	long time;
	String seqnum,isEot;
	DatagramPacket packet = null;
	DatagramPacket ack=null;
	DatagramSocket seqport = null;
	DatagramSocket ackport = null;
	byte[] ackbuf = new byte[4];
	byte[] buf = new byte[119];
	public UdpSender(DatagramSocket s,String file, long timeout,DatagramSocket ack) throws Exception{
		this.file=file;
		this.time=timeout;
		this.ackport=ack;
		this.seqport=s;
		this.seqnum="0000";
		this.isEot="0";
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
		byte[] seqoffset = new byte[4];
		byte[] eotoffset = new byte[1];
		byte[] offdata = new byte[124];
		int offsetlen;
		boolean isRun = true;

		while(isRun) {
			bytestr=reader.read(this.buf);
			String input;
			this.packet = new DatagramPacket(offdata,offdata.length, InetAddress.getByName("localhost"),4000);
			if(bytestr!=-1 && reader.available()>=1) {
				try {				
					ByteArrayOutputStream read = new ByteArrayOutputStream(124);
					ByteArrayOutputStream out = new ByteArrayOutputStream(5);
					
					seqoffset=this.seqnum.getBytes();
					eotoffset=this.isEot.getBytes();
					out.write(seqoffset);
					out.write(eotoffset);
					byte[] c= out.toByteArray();
					read.write(c);
					read.write(this.buf);

					offdata=read.toByteArray();
					this.seqport.send(this.packet);
					
					
					int acktype=this.timeout(offdata);
					if(acktype==1 ||acktype==2) {
						System.exit(1);
					}
					Integer nextseq= Integer.parseInt(this.seqnum)+1;
					int len=nextseq.toString().length();
					if(len==4) {
						this.seqnum=nextseq.toString();
					}else if(len==3) {
						this.seqnum="0"+nextseq.toString();
					}else if(len==2) {
						this.seqnum="00"+nextseq.toString();
					}else if(len==1) {
						this.seqnum="000"+nextseq.toString();
					}
				}catch(Exception e) {
	        			e.printStackTrace(System.out);
	
					isRun=false;
				}
			}else {
				isRun=false;
				this.isEot="1";
				try {
					ByteArrayOutputStream read = new ByteArrayOutputStream(124);
					ByteArrayOutputStream out = new ByteArrayOutputStream(5);
					
					seqoffset=this.seqnum.getBytes();
					eotoffset=this.isEot.getBytes();
					out.write(seqoffset);
					out.write(eotoffset);
					byte[] c= out.toByteArray();
					read.write(c);
					read.write(this.buf);

					offdata=read.toByteArray();
					this.seqport.send(this.packet);
					int acktype=this.timeout(offdata);
					if(acktype==1 ||acktype==2) {
						System.exit(1);
					}
				}catch(Exception e) {
        			e.printStackTrace(System.out);

				}
				
			}
			
		}
	}
	//returns 0, good Ack
	//returns 1, timeout ACK
	//returns 2, out of order ACK
	public int timeout(byte[] offdata) throws Exception{
		long starttime = System.currentTimeMillis();
		int ackd = 0;
		boolean isRunning = true;
		while(isRunning) {
			if(System.currentTimeMillis()>(starttime+this.time)) {

				isRunning= false;
				ackd=0;
				System.out.println("timeout");
			}else {
				try {

					this.ack = new DatagramPacket(this.ackbuf,this.ackbuf.length);
					this.ackport.receive(this.ack);
					ackd=0;
					isRunning=false;
				}catch(Exception e) {
			    		e.printStackTrace(System.out);
			
					throw e;
				}
			}
		}
		return ackd;
	}
}
