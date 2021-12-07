package updatearuba.Setting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

/**
 *
 * @author DeveloperForYou
 */
public class LeggiDriverSetting {

    private final static String OS = System.getProperty("os.name").toLowerCase();

    public static String getFileDriverSetting() {
        if (OS.equals("windows")) {
            return System.getProperty("user.dir") + "\\Setting\\DriverConfiguration.properties";
        } else {
            return System.getProperty("user.dir") + "/Setting/DriverConfiguration.properties";
        }
    }

    public static String getChromeDriverDirectory() {
        Properties prop = new Properties();
        String dd = "";
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key = OS.substring(0, 1).toUpperCase() + OS.substring(1)
                    + "ChromeDriverDirectory";
            dd = prop.getProperty(key);
            if(dd==null | dd.isEmpty()){
                System.err.println("Errore di Configurazione "
                       +"nella chiave:ChromeDriverDirectory che risulta mancante nel file "
                       +getFileDriverSetting()
                       + "-> Driver Chrome mancante ");
                System.exit(1);
                return null;
            }  
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return dd;
    }

    public static String getChromeLocalInstallation() {
        String cli = "";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key = OS.substring(0, 1).toUpperCase() + OS.substring(1)
                    + "ChromeInstallDirectory";
            cli = prop.getProperty(key);
            if(cli==null | cli.isEmpty()){
                System.err.println("Errore di Configurazione "
                       +"nella chiave:ChromeInstallDirectory che risulta mancante nel file "
                       +getFileDriverSetting()
                       + "->  Google Chrome non installato sul sistema ");
                System.exit(1);
                return null;
            }
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return cli;
    }
    
    public static String getGmailMail() {
        Properties prop = new Properties();
        String mg = "";
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key ="MailGmail";
            mg = prop.getProperty(key);
            if(mg==null | mg.isEmpty()){
                System.err.println("Errore di Configurazione "
                       +"nella chiave:MailGmail che risulta mancante nel file "
                       +getFileDriverSetting()
                       + "-> Mail mancante ");
                System.exit(1);
                return null;
            }  
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return mg;
    }
    
    public static String getGmailMail() {
        Properties prop = new Properties();
        String mp = "";
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key ="MailPassword";
            mp = prop.getProperty(key);
            if(mp==null | mp.isEmpty()){
                System.err.println("Errore di Configurazione "
                       +"nella chiave:MailPassword che risulta mancante nel file "
                       +getFileDriverSetting()
                       + "-> Mail Password mancante ");
                System.exit(1);
                return null;
            }  
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return mp;
    }

    public static String[] getGlobalDriverOptions() {
        String[] option = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key = "GlobalDriverOptions";
            String values = prop.getProperty(key);
            option = values.split(",");
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return option;
    }

    public static int[] getTimeOfExecution() {
        int[] time = new int[2];
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(getFileDriverSetting()));
            String key = "TimeFirstExecution";
            String value = prop.getProperty(key).strip();
            time[0]=Integer.parseInt(value);
            key="TimeAllExecution";
            value = prop.getProperty(key).strip();
            time[1]=Integer.parseInt(value);
            if(time==null){
               System.err.println("Errore di Configurazione "
                       +"nella chiavi:TimeFirstExecution e/o TimeAllExecution"
                       + "che risultano mancante nel file "
                       +getFileDriverSetting()
                       + "->  I tempi di schedulazione sono mancanti ");
                System.exit(1);
                return null;
            }
        } catch (FileNotFoundException ex) {
             ex.printStackTrace();
             System.exit(1);
             return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
        return time;
    }

    public static void main(String[] args) {
        System.out.println(LeggiDriverSetting.getChromeDriverDirectory());
        System.out.println(LeggiDriverSetting.getChromeLocalInstallation());
        String[] opt = LeggiDriverSetting.getGlobalDriverOptions();
        for (int i = 0; i < opt.length; i++) {
            System.out.println(opt[i]);
        }
        int[] time = LeggiDriverSetting.getTimeOfExecution();
        for (int j = 0; j < time.length; j++) {
            System.out.println(time[j]);
        }
    }
}
