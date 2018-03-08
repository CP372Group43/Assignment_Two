

import java.io.* ;
import java.net.* ;
import java.nio.ByteBuffer;
import java.util.* ;

public class UdpSender implements Runnable{

	String file;
	long time;
	String seqnum,isEot,sendaddr;
	DatagramPacket packet = null;
	DatagramPacket ack=null;
	DatagramSocket seqport = null;
	DatagramSocket ackport = null;
	byte[] ackbuf = new byte[4];
<<<<<<< HEAD
	byte[] buf = new byte[119];
	int sendport;
	public UdpSender(DatagramSocket s,String file, long timeout,DatagramSocket ack,int port,String address) throws Exception{
=======
	byte[] buf = new byte[118];
	public UdpSender(DatagramSocket s,String file, long timeout,DatagramSocket ack) throws Exception{
>>>>>>> parent of 4d453c8... varnames
		this.file=file;
		this.time=timeout;
		this.ackport=ack;
=======
	byte[] buf = new byte[118];
	public UdpSender(DatagramSocket s,String file, int timeout) throws Exception{
		this.file=file;
		this.timeout=timeout;
		this.ackport=ackport;
<<<<<<< HEAD
>>>>>>> parent of 496132e... acks
=======
>>>>>>> parent of 496132e... acks
		this.seqport=s;
		this.seqnum="0000";
		this.isEot="0";
		this.sendport=port;
		this.sendaddr=address;
		try {
		this.run();
		}catch(Exception e) {
			throw new Exception();
		}
	}
	public void run(){
		
		
		try {
			FileInputStream reader = new FileInputStream(this.file);
			SendPacket(reader);
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("file not found");
			System.exit(1);
	}catch(Exception e) {
    		e.printStackTrace(System.out);
		}
	}
	public void SendPacket(FileInputStream reader) throws Exception {
		int bytestr;
		byte[] seqoffset = new byte[4];
		byte[] eotoffset = new byte[2];
		eotoffset[0]=0;
		eotoffset[1]=0;
		byte[] offdata = new byte[124];
		int offsetlen;
		boolean isRun = true;
		System.out.println("insnd");

		while(isRun) {
			bytestr=reader.read(this.buf);
			String input;
<<<<<<< HEAD
			this.packet = new DatagramPacket(offdata,offdata.length, InetAddress.getByName(this.sendaddr),this.sendport);
=======
			//this.buf=input.getBytes();
			int lenseq = this.seqnum.toString().getBytes().length + this.isEot.toString().getBytes().length;
			System.out.println(this.seqnum.toString().getBytes());
			this.packet = new DatagramPacket(offdata,offdata.length, InetAddress.getByName("localhost"),4000);
			//ByteBuffer bbuf = ByteBuffer.allocate(124)
>>>>>>> parent of 4d453c8... varnames
			if(bytestr!=-1 && reader.available()>=1) {
				try {				
					ByteArrayOutputStream read = new ByteArrayOutputStream(124);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					
					System.out.println(lenseq);
					seqoffset=this.seqnum.getBytes();
					eotoffset=this.isEot.getBytes();
					out.write(seqoffset);
					out.write(eotoffset);
					byte[] c= out.toByteArray();
					read.write(c);
					read.write(this.buf);
					System.out.println(offdata);

					System.out.println(read.toString());
					offdata=read.toByteArray();
					this.seqport.send(this.packet);
<<<<<<< HEAD
<<<<<<< HEAD
					
					
					int acktype=this.timeout(offdata);
					if(acktype==1 ||acktype==2) {
						System.out.println("exit");
						System.exit(1);
					}
=======
>>>>>>> parent of 496132e... acks
=======
>>>>>>> parent of 496132e... acks
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
					
					//this.timeout();
				}catch(Exception e) {
	        			e.printStackTrace(System.out);
	
					isRun=false;
				}
			}else {
				System.out.println("endoffile");
				isRun=false;
				this.isEot="1";
				try {
					ByteArrayOutputStream read = new ByteArrayOutputStream(124);
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					
					System.out.println("waduhek");
					System.out.println(lenseq);
					seqoffset=this.seqnum.toString().getBytes();
					eotoffset=this.isEot.toString().getBytes();
					out.write(seqoffset);
					out.write(eotoffset);
					byte[] c= out.toByteArray();
					read.write(c);
					read.write(this.buf);

					System.out.println(read.toString());
					offdata=read.toByteArray();
					this.seqport.send(this.packet);
	//				this.timeout();
				}catch(Exception e) {
				}
				
			}
			
		}
	}
	public void timeout() throws Exception{
		long starttime = System.currentTimeMillis();
		boolean isRunning = true;
		while(isRunning) {
<<<<<<< HEAD
			System.out.println("loopin");
=======
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> c73dd41fe0b65b5934cc06b119e01b83544e0661
			if(System.currentTimeMillis()>(starttime+this.time)) {
				System.out.println("bad");

				isRunning= false;
				ackd=0;
				System.out.println("timeout");
			}else {
				try {
					System.out.println("not bad");

					this.ack = new DatagramPacket(this.ackbuf,this.ackbuf.length);
					this.ackport.receive(this.ack);
					byte[] str = this.ack.getData();
					String cmp = new String(str);
					System.out.println(cmp);
					ackd=0;
					isRunning=false;
=======
			if(System.currentTimeMillis()>=(starttime+this.timeout)) {
				isRunning= false;
			}else {
				try {
					this.ack = new DatagramPacket(ackbuf,ackbuf.length);
					this.ackport.receive(this.ack);
>>>>>>> parent of 496132e... acks
=======
			if(System.currentTimeMillis()>=(starttime+this.timeout)) {
				isRunning= false;
			}else {
				try {
					this.ack = new DatagramPacket(ackbuf,ackbuf.length);
					this.ackport.receive(this.ack);
>>>>>>> parent of 496132e... acks
				}catch(Exception e) {
			    		e.printStackTrace(System.out);
			
					throw e;
				}
			}
		}
	}
}
