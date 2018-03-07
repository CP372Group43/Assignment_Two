
import java.io.* ;
import java.net.* ;
import java.nio.ByteBuffer;
import java.util.* ;

public class UdpSender {

	String file;
	int timeout;
	String seqnum,isEot;
	DatagramPacket packet = null;
	DatagramPacket ack=null;
	DatagramSocket seqport = null;
	DatagramSocket ackport = null;
	byte[] ackbuf = new byte[4];
<<<<<<< HEAD
	byte[] buf = new byte[118];
	public UdpSender(DatagramSocket s,String file, int timeout,DatagramSocket ackport) throws Exception{
=======
	byte[] buf = new byte[124];
	boolean isRun = true;
	public UdpSender(DatagramSocket s,String file, int timeout) throws Exception{
		System.out.println("new udp sender created..");

>>>>>>> 68c3f8dc5e3258d90d502d31b2b15a738c0938d6
		this.file=file;
		this.timeout=timeout;
		this.ackport=ackport;
		this.seqport=s;
		this.seqnum="0000";
		this.isEot="0";
		try {
			FileInputStream reader = new FileInputStream("test.txt");
			this.SendPacket(reader);
			reader.close();
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
<<<<<<< HEAD
		boolean isRun = true;
		System.out.println("insnd");

=======
		isRun = true;
>>>>>>> 68c3f8dc5e3258d90d502d31b2b15a738c0938d6
		while(isRun) {
			bytestr=reader.read(this.buf);
			String input;
			//this.buf=input.getBytes();
			int lenseq = this.seqnum.toString().getBytes().length + this.isEot.toString().getBytes().length;
			System.out.println(this.seqnum.toString().getBytes());
<<<<<<< HEAD
			this.packet = new DatagramPacket(offdata,offdata.length, InetAddress.getByName("localhost"),4000);
			//ByteBuffer bbuf = ByteBuffer.allocate(124)
=======
			System.out.print("bytestr = " + bytestr + "   available = " + reader.available());
>>>>>>> 68c3f8dc5e3258d90d502d31b2b15a738c0938d6
			if(bytestr!=-1 && reader.available()>0) {
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
<<<<<<< HEAD

					System.out.println(read.toString());
					offdata=read.toByteArray();
=======
					System.out.println("eotlen"+ this.isEot.toString());
					input=  this.seqnum.toString() +this.isEot.toString()+read.toString();
					System.out.println(input);
					System.out.println(input.getBytes());
					offdata=input.getBytes();
					System.out.print("-------HEREHEREHEREHEREHERE------");
					this.packet = new DatagramPacket(offdata,offdata.length, InetAddress.getByName("localhost"), 2222);
>>>>>>> 68c3f8dc5e3258d90d502d31b2b15a738c0938d6
					this.seqport.send(this.packet);
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
<<<<<<< HEAD
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
				
=======
				this.isEot=1;
>>>>>>> 68c3f8dc5e3258d90d502d31b2b15a738c0938d6
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
