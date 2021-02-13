                                     Istruzioni per l'uso dei seguenti File

Questi files rappresentano un soluzione ad un problema molto fastidioso ovvero quando il nostro Indirizzo IP Pubblico cambia a seguito di un blackout oppure 
ad una disconessione causata dal nostro ISP e i servizi che offriamo risulteranno offline
*Legenda File:
 -PublicIP.zip       archivio zippato contentente il file eseguibile del tool, una volta estratto sara' un file con estensione .sh eseguibile tramite il comando: bash PublicIP.sh 
 -UpdateAruba.zip    archivio zippato contentente il modulo di aggiornamento automatico dell'Indirizzo IP 
 -email.zip          archivio zippato contentente il modulo di invio email del cambiamento dell'Indirizzo IP a causa di disconessione o blackout
Il tool e' composto da questi tre pezzi
 
Note Prima di iniziare:
-scaricare i 3 Archivi da GitHub:
      PublicIP.zip           https://github.com/DeveloperForYou/ProjectRepo/raw/main/ToolUpdateAruba/Linux/PublicIP.zip
      UpdateAruba.zip        https://github.com/DeveloperForYou/ProjectRepo/raw/4f101abe689b81223e7ae8553a2f41f00a8ec979/ToolUpdateAruba/Linux/UpdateAruba.zip
      email.zip              https://github.com/DeveloperForYou/ProjectRepo/raw/4f101abe689b81223e7ae8553a2f41f00a8ec979/ToolUpdateAruba/Linux/email.zip
	  
-estrarre i tre file Zip nella Scrivania 

-scaricare il WebDriver per GoogleChrome tramite il comando: wget https://chromedriver.storage.googleapis.com/89.0.4389.23/chromedriver_linux64.zip
-estrarre anch'esso nella scrivania 

-installare Java 13 tramite il comando: sudo apt install openjdk-13-jdk-headless

-installare Google Chrome tramite la successione di comandi:
                                                           wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
														   sudo dpkg -i google-chrome-stable_current_amd64.deb
-installare Netbeans per aprire i package che conterranno i file .java da modificare e poi successivamente compilarli 
 per installare Netbeans digitare il comando: 
                                              sudo snap install netbeans --classic
avviarlo tramite il comando:                  netbeans											  
-importare le librerie necessarie alla compilazione  tramite la sezione Libraries di Netbeans, i rispettivi file di libreria sono nella cartella lib nelle rispettive 
cartelle dei pacchetti che avete estratto prima
-modificare oppurtunamente i due file .java con Netbeans secondo questo schema: 

 nel file email.java dovrete sostiture "tua_mail" e "tua_pass" con i vostri dati della vostra email e funziona solo se avete Gmail
 
 nel file UpdateAruba.java sostuire "directoryDriver"  con il percorso dove avete estratto il driver per Google Chrome  e 
  inserire i vostri dati di aruba nel metodo Login() precisamente :
                     user.sendKeys("tuo_user@aruba.it");  //**********sostituire con il vostro username
                     pass.sendKeys("");                   //*********sostituire con la vostra password 

procedere alla compilazione dei file java, i rispettivi eseguibili con estenzione .jar li trovate nella cartella dist nelle rispettive cartelle dei pacchetti che avete estratto prima
-modificare il file PublicIP.sh alle righe 19,27,28 inserendo il percorso di dove sono stati  creati i rispettivi file .jar  e salvate 
lanciare il file PublicIP.sh tramite il comando bash DirectorydovesitrovailfilePublicIP/PublicIP.sh
