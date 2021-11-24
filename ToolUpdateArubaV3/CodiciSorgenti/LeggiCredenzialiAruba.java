package updatearuba.Setting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

/**
 *
 * @author DeveloperForYou
 */
public class LeggiCredenzialiAruba {

    public static String getFileCrendenzial() {
        if (System.getProperty("os.name").toLowerCase().equals("windows")) {
            return System.getProperty("user.dir") + "\\Setting\\ArubaCredenzial.properties";
        } else {
            return System.getProperty("user.dir") + "/Setting/ArubaCredenzial.properties";
        }
    }

    public static String getArubaUsername() {
        Properties prop = new Properties();
        String username = "";
        try {
            prop.load(new FileInputStream(getFileCrendenzial()));
            username = prop.getProperty("ArubaUsername");
            if(username==null | username.isEmpty()){
               System.err.println("Errore di Configurazione "
                       +"nella chiave:ArubaUsername che risulta mancante "
                       + "nel file"+getFileCrendenzial());
               System.exit(1);
               return null;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return username;
    }

    public static String getArubaPassword() {
        Properties prop = new Properties();
        String password = "";
        try {
            prop.load(new FileInputStream(getFileCrendenzial()));
            password = prop.getProperty("ArubaPassword");
            if(password==null | password.isEmpty()){
               System.err.println("Errore di Configurazione "
                       +"nella chiave:ArubaPassword che risulta mancante nel file " 
                       +getFileCrendenzial());
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
        return password;
    }

    public static void main(String[] args) {
        System.out.println(LeggiCredenzialiAruba.getArubaUsername());
        System.out.println(LeggiCredenzialiAruba.getArubaPassword());
    }

}
