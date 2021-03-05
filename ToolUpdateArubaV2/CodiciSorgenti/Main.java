package updatearuba;

import java.util.Timer;
import java.util.TimerTask;



/**
 *
 * @author DeveloperForYou
 */
public class Main {
    static String OS=System.getProperty("os.name").toLowerCase();
    
    public static void main(String [] args){
      TimerTask tt;
      Timer t;
      if(OS.contains("windows")){
       tt=new StatoSitoWindows();
       t=new Timer();
       t.schedule(tt, 1000, 2000);
      }
      else{
        tt=new StatoSitoLinux();
        t=new Timer();
        t.schedule(tt, 1000, 2000);
      }
  }
    
}

