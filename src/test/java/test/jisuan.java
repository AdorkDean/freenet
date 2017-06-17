package test;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class jisuan {
	
	public static void main(String args[]){
		 /*String a = "9999.9999";
		 int b = 9999;
		 double c = 9999.9999;
		 char d = 99;
		 int e = 3;
		
		 BigDecimal ma = new BigDecimal(a);
		 BigDecimal mb = new BigDecimal(b);
		 BigDecimal mc = new BigDecimal(c);
		 BigDecimal md = new BigDecimal(d);
		 BigDecimal me = new BigDecimal(e);
		 
		 BigDecimal add = ma.add(mb);
		 System.out.println("加法："+add);
		 // 减
		 BigDecimal sub = ma.subtract(mb);
		 System.out.println("减法："+sub);
		 // 乘
		 BigDecimal mul = mb.multiply(md);
		 System.out.println("乘法："+mul);
		 // 除
		 BigDecimal div = md.divide(me);
		 System.out.println("除法："+div);*/
		InetAddress netAddress = getInetAddress();  
        System.out.println("host ip:" + getHostIp(netAddress));  //计算机ip
        System.out.println("host name:" + getHostName(netAddress)); 
	}
	
	public static InetAddress getInetAddress(){  
		  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            System.out.println("unknown host!");  
        }  
        return null;  
  
    }
	
	
	public static String getHostIp(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String ip = netAddress.getHostAddress(); //get the ip address  
        return ip;  
    }  
  
    public static String getHostName(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String name = netAddress.getHostName(); //get the host address  
        return name;  
    } 

}
