package updatearuba;


import static java.lang.Thread.sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Albe
 */
public class UpdateAruba {

    static String baseUrl = "https://managehosting.aruba.it";
    static WebDriver driver= null;
    static String OS=System.getProperty("os.name").toLowerCase();
    
    
    public static void main(String[] args) {
        //INIZIALIZZO IL DRIVER 
        Inizializza();
        //CONNESSIONE AD ARUBA
        ConnettiAruba();
        //LOGIN ARUBA
        Login();
        //APERTURA PANNELLO ADMIN
        PannelloAdmin();
        //APERTURA UTILITY DOMINIO
        UtilityDominio(); 
        //APERTURA PANNELLO DNS
        PannelloDNS(args[0]);
        //CHIUSURA DEL DRIVER E DI GOOGLE CHROME
        ChiudiChrome();
     }  
    
    
    public static void Inizializza()
    {
     System.out.println(System.getProperty("user.dir"));
     System.out.println(OS);
     if(OS.contains("windows"))
       System.setProperty("webdriver.chrome.driver", "directoryDriver\\chromedriver.exe");//**sostituire directoryDriver con la directory in cui si trova il driver web che avete scaricato 
     else                                                                                 //**sostituire directoryDriver solo nella parte del vostro Sistema Operativo   
       System.setProperty("webdriver.chrome.driver", "directoryDriver//chromedriver");
     driver = new ChromeDriver();
    }
    
    public static void ConnettiAruba()
    {
     driver.get(baseUrl);
    }
    
    public static void Login()
    {
      WebElement user=driver.findElement(By.id("LoginAreaUtenti"));
      WebElement pass=driver.findElement(By.id("PasswordAreaUtenti"));
      WebElement login=driver.findElement(By.id("submitFormCustomerArea"));
      System.out.println(driver.getCurrentUrl());
      user.sendKeys("tuo_user@aruba.it");  //**********sostituire con il vostro username
      pass.sendKeys("");                   //*********sostituire con la vostra password
      login.click();
    }
    
    public  static void timer(int ms)
    {
      try{
          sleep(ms);
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
     }    
    
    public static void switchTabs()
    {
      for(String winHandle : driver.getWindowHandles()) //effettua lo switch  sulla seconda pagina che chrome apre 
       driver.switchTo().window(winHandle);  
    }      
    
    public static void PannelloAdmin()
    {
     System.out.println(driver.getCurrentUrl());
     new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='form10']/button"))).click();
    }        
         
    public static void UtilityDominio()
    {
        switchTabs();
        timer(1000);
        System.out.println(driver.getCurrentUrl());
        WebElement l=driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_RadDockServicesSummary_C_ServiziAcquistati_rlvUtilityDominio_ctrl3_Image6"));
        l.click();
    }
      
    public static void PannelloDNS(String nip)
    {
     //APERTURA PANNELLO DNS
        switchTabs();
        System.out.println(driver.getCurrentUrl());
     //EFFETTUO MODIFICHE
        //MODIFICA DNS 
        //PUNTO A) CLICCARE SUL PULSANTE GESTISCI
          WebElement gest=driver.findElement(By.cssSelector("#ctl00_Bottom_btnModifyA"));
          gest.click();
        //PUNTO B) MODIFICO HOST VUOTO TRAMITE LA MATITA, ELIMINO IL VECCHIO INDIRIZZO IP, SCRIVO IL NUOVO E CLICCO SUL BOTTONE BLU
          timer(1000);
          WebElement vu=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(2) > td:nth-child(4) > input[type=image]"));
          vu.click();
          timer(2000);
          WebElement ip=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(2) > td:nth-child(3) > input[type=text]"));
          ip.clear();
          ip.sendKeys(nip);//QUI LO PRENDERA' DIRETTAMENTE DALLA BASH LINUX TRAMITE ARGOMENTO DELLA JVM 
          WebElement bu=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(2) > td:nth-child(4) > input[type=image]:nth-child(1)"));
          bu.click();
        //PUNTO C) MODIFICO PARTE WWW TRAMITE LA MATITA, ELIMINO IL VECCHIO INDIRIZZO IP, SCRIVO IL NUOVO E CLICCO SUL BOTTONE BLU
          timer(1000);
          WebElement ww=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(6) > td:nth-child(4) > input[type=image]"));
          ww.click();
          timer(2000);
          WebElement ip2=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(6) > td:nth-child(3) > input[type=text]"));
          ip2.clear();
          ip2.sendKeys(nip);//QUI LO PRENDERA' DIRETTAMENTE DALLA BASH LINUX TRAMITE ARGOMENTO DELLA JVM 
          WebElement bu2=driver.findElement(By.cssSelector("#ctl00_Main_DynaLoadCtrl_3_grdRecord > tbody > tr:nth-child(6) > td:nth-child(4) > input[type=image]:nth-child(1)"));
          bu2.click();
        //PUNTO D) INDIVIDUARE IL PULSANTE PROSEGUI E POI IL PULSANTE DI SALVA CONFIGURAZIONE, e il pulsante OK
          WebElement pr=driver.findElement(By.cssSelector("#ctl00_Main_btnStep2"));
          pr.click();
          WebElement sc=driver.findElement(By.cssSelector("#ctl00_Bottom_btnSave"));
          sc.click();
          WebElement ok=driver.findElement(By.cssSelector("#ctl00_Bottom_btnConfirmOk"));
          ok.click();
    }
    
    public static void ChiudiChrome()
    {
     timer(2000);    
     driver.close();
     driver.quit();
    }
}
