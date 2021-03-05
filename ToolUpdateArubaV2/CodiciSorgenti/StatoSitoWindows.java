package updatearuba;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import java.util.TimerTask;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author DeveloperForYou
 */
public class StatoSitoWindows extends TimerTask {
    String command ="curl.exe -s https://checkip.amazonaws.com";
    File ipaddr=new File("ipaddr.txt");
    UpdateAruba up=new UpdateAruba();
     
    @Override
    public void run() {
        System.out.println("Controllo Automatico Stato Sito Web ");
        CalcolaStato();
    }
    
    public void CalcolaStato(){
     try {
          Process proc=Runtime.getRuntime().exec(command);
          BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
          String inputLine;
          String risultato="";
           while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                risultato += inputLine;
           }
          boolean esiste=ipaddr.exists();
          if(!esiste){
            ipaddr.createNewFile();
            Write(risultato);
          }
          else{
               String dafile=Read();
               System.out.println(dafile);
               if(risultato.equals(dafile)){
                System.out.println("Stato del sito web online sull'indirizzo IP: "+risultato);
                } 
               else{
                   email em=new email("admin@albertogavazzi.it","Stato del sito web online sull'indirizzo IP: "+risultato);
                   em.sendWithOutAttached("admin@albertogavazzi.it",
                         "Rimetto in Piedi il Sito Web sul nuovo Indirizzo IP"
                          +"\nNStato del sito web online sull'indirizzo IP: "+risultato
                         ,"SitoRimessoOnline");
                   ipaddr.delete();
                   //INIZIALIZZO IL DRIVER 
                   up.Inizializza();
                   //CONNESSIONE AD ARUBA
                   up.ConnettiAruba();
                  //LOGIN ARUBA
                   up.Login();
                  //APERTURA PANNELLO ADMIN
                   up.PannelloAdmin();
                   //APERTURA UTILITY DOMINIO
                   up.UtilityDominio(); 
                   //APERTURA PANNELLO DNS
                   up. PannelloDNS(risultato);
                   //CHIUSURA DEL DRIVER E DI GOOGLE CHROME
                   up.ChiudiChrome();
                   ipaddr.createNewFile();
                   Write(risultato);
                 }
          }   
         } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void Write(String wr)
    {
     try {
            FileUtils.writeStringToFile(ipaddr, wr, Charset.defaultCharset(), false);
         } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String Read(){
      String letto = null;
      try {
           letto = FileUtils.readFileToString(ipaddr,Charset.defaultCharset());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     return letto;
    }
}
