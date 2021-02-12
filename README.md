# ProjectRepo
 Istruzioni per l'uso dei seguenti File

Questi files rappresentano un soluzione ad un problema molto fastidioso ovvero quando il nostro Indirizzo IP Pubblico cambia a seguito di un blackout oppure ad una disconessione causata dal nostro ISP e i servizi che offriamo risulteranno offline
Legenda File:
-PublicIP.sh sara' il file che dovrete lanciare sempre 
-i due file Java che insieme ai file zip contenenti le librerie vi serviranno per la compilazione e per creare i rispetitivi file jar 
Note Prima di iniziare:
-scaricate il WebDriver per GoogleChrome da questo link: https://chromedriver.chromium.org/downloads
-installare Java 13 tramite il comando sudo apt install openjdk-13-jdk-headless
-modificare oppurtunamente i due file Java secondo questa:
 nel file email.java dovrete sostiture "tua_mail" e "tua_pass" con i vostri dati della vostra email e funziona solo se avete Gmail
 nel file UpdateAruba.java sostuire "directoryDriver"  con il percorso dove avete estratto il driver per Google Chrome  e inserire i vostri dati di aruba nel metodo Login() precisamente :
                     user.sendKeys("tuo_user@aruba.it");  //**********sostituire con il vostro username
                     pass.sendKeys("");                   //*********sostituire con la vostra password 
Salvate i file java ed estraete i file .zip che conterranno le liberie per effettuare la compilazione
                              Compilazione email.java 
dirigersi dove e' stato estratto il file zip libMailserver1.zip  e digitare il comando:
javac -cp activation-1.1.1.jar:jakarta.activation-api-2.0.0.jar:jakarta.mail-2.0.0.jar  Directorydovesitrovailfileemail.java/email.java
il comando genera un file email.class nel percorso del file. java 
per creare il file digitare il comando:  jar cvf email.jar  Directorydovesitrovailfileemail.java/email.class
nella cartella dove prima si trovavano solo  le librerie ci sara' un file email.jar 
                            Compilazione UpdateAruba
dirigersi dove e' stato estratto il file zip libUpdateAruba.zip e digitare il comando: 
javac -cp byte-buddy-1.8.15.jar:client-combined-3.141.59.jar:commons-exec-1.3.jar:guava-30.1-jre.jar:okhttp-3.11.0.jar:okio-1.14.0.jar DirectorydovesitrovailfileUpdateAruba.java/UpdateAruba.java
il comando genera un file UpdateAruba.class nel percorso del file. java 
creare il file jar tramite il comando:  jar cvf UpdateAruba.jar  DirectorydovesitrovailfileUpdateAruba.java/UpdateAruba.class
nella cartella dove prima si trovavano solo  le librerie ci sara' un file UpdateAruba.jar 

-modificare il file PublicIP.sh alle righe 19,27,28 inserendo il percorso di dove sono stati  creati i rispettivi file .jar  e salvate 
lanciare il file PublicIP.sh tramite il comando bash DirectorydovesitrovailfilePublicIP/PublicIP.sh
