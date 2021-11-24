package updatearuba;

import static java.lang.Thread.sleep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import updatearuba.Setting.LeggiCredenzialiAruba;
import updatearuba.Setting.LeggiDriverSetting;

/**
 *
 * @author Albe
 */
public class UpdateAruba {

    static String baseUrl = "https://managehosting.aruba.it";
    static WebDriver driver= null;
    static String OS=System.getProperty("os.name").toLowerCase();
    
    public  void Inizializza()
    {
     System.out.println(System.getProperty("user.dir"));
     System.out.println(OS);
     ChromeOptions ch=new ChromeOptions();
     String[]  cho=LeggiDriverSetting.getGlobalDriverOptions();
     if(cho!=null){
      for(int i=0;i<cho.length;i++)
       ch.addArguments(cho[i]);
     }
     ch.setBinary(LeggiDriverSetting.getChromeLocalInstallation());
     System.setProperty("webdriver.chrome.driver", LeggiDriverSetting.getChromeDriverDirectory());
     driver = new ChromeDriver(ch);
     driver.manage().window().maximize();
    }
    
    public  void ConnettiAruba()
    {
     driver.get(baseUrl);
    }
    
    public void Login()
    {
      WebElement user=driver.findElement(By.id("LoginAreaUtenti"));
      WebElement pass=driver.findElement(By.id("PasswordAreaUtenti"));
      WebElement login=driver.findElement(By.id("submitFormCustomerArea"));
      System.out.println(driver.getCurrentUrl());
      user.sendKeys(LeggiCredenzialiAruba.getArubaUsername()); 
      pass.sendKeys(LeggiCredenzialiAruba.getArubaPassword());      
      timer(20);
      WebElement banner=driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
      timer(20);
      banner.click();
      timer(20);
      login.click();
    }
    
    public void timer(int ms)
    {
      try{
          sleep(ms);
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
     }    
    
    public void switchTabs()
    {
      for(String winHandle : driver.getWindowHandles()) //effettua lo switch  sulla seconda pagina che chrome apre 
       driver.switchTo().window(winHandle);  
    }      
    
    public void PannelloAdmin()
    {
     System.out.println(driver.getCurrentUrl());
     new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='form10']/button"))).click();
    }        
         
    public void UtilityDominio()
    {
      WebElement mt=driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div[2]/div[5]/div/ul/li[2]/a/span/span[1]"));
      mt.click();
    }
      
    public void PannelloDNS(String nip)
    {
     //APERTURA PANNELLO DNS
        //switchTabs();
        System.out.println(driver.getCurrentUrl());
        WebElement pdns=driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div[2]/div[5]/div/ul/li[2]/div/ul/li[4]/a"));
        pdns.click();
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
    
    public void ChiudiChrome()
    {
     timer(6000);    
     driver.close();
     driver.quit();
    }
}
