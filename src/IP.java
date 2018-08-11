
import java.net.*;

class IPP{
	InetAddress myIPaddress=null;
	InetAddress myServer=null;
	//LOCALHOST的IP地址
	public InetAddress getMyIP() {
	try { myIPaddress=InetAddress.getLocalHost();}
	catch (UnknownHostException e) {}
	return (myIPaddress);
	}
	//www.abc.com 的IP地址
	public InetAddress getServerIP(){
	try {myServer=InetAddress.getByName(
	"www.gdzhixiu.com");}
	catch (UnknownHostException e) {}
	return (myServer);
	}
}

public class IP{

	public static void main(String args[])
	{
		IPP ip=new IPP();
		System.out.println(ip.getMyIP());
		System.out.println(ip.getServerIP());

	}
}