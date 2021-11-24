package updatearuba;

import java.util.Timer;
import java.util.TimerTask;

import updatearuba.Setting.LeggiDriverSetting;

/**
 *
 * @author DeveloperForYou
 */
public class Main {
    static String OS=System.getProperty("os.name").toLowerCase();
    
    public static void main(String [] args){
      TimerTask tt;
      Timer t;
      int[] time=LeggiDriverSetting.getTimeOfExecution();
      if(OS.contains("windows")){
       tt=new StatoSitoWindows();
       t=new Timer();
       t.schedule(tt, time[0],time[1]);
      }
      else{
        tt=new StatoSitoLinux();
        t=new Timer();
        t.schedule(tt, time[0],time[1]);
      }
  }  
}