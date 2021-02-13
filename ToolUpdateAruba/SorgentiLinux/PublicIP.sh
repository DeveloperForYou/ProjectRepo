##########################VARIABILI##########################################################################################################
ip=$(curl -s https://checkip.amazonaws.com)                 #IP pubblico attuale
oip=""                                                      #IP pubblico precedente 
data=$(date) 						      #data e ora attuale di sistema
#####################INIZIO SCRIPT###########################################################################################################
echo "############################################################################################"
echo "###########Script automatico di verifica online sito web data e ora: "$data "#############"
echo "############################################################################################"
tput setaf 3;
if [ ! -f "fip.txt"  ] ;                    #se lo script non e' mai stato eseguito, sicuramente non esiste il file che conterra' l'indirizzo ip attuale
then
    echo "Creo il file contenente l'indirizzo IP: "$ip
    echo $ip > "fip.txt" 
fi 
oip=$(cat "fip.txt")                  #leggo dal file il vecchio indirizzo IP(la prima volta conterra' l'indirizzo IP attuale)
if [ $ip = $oip  ] ;                  #indirizzi ip uguali non faccio niente 
then
    echo "il sito web e' online"
    java -jar /directory/email.jar "nome@dominio.com" "sito_online sull'indirizzo IP: "$ip   "sito_online"
    exit 0
else                                  #indirizzi ip diversi, provvedo a segnalare che il sito e' down quindi elimino il vecchio file dell'indirizzo ip e lo riscrivo col nuovo 
    echo "il sito web  non e' online"
    echo "Vecchio Indirizzo IP:" $oip
    echo "Nuovo Indirizzo IP  :" $ip
    rm -rf "fip.txt" 
    echo $ip > "fip.txt" 
    java -jar /directory/email.jar "nome@dominio.com" "Si prega di sostituire il vecchio Indirizzo IP:"$oip" con il nuovo:  "$ip  "sito _non_online"
    java -jar /directory/UpdateAruba.jar  $ip
fi
#sostituire directory con il percorso effettitvo di dove si trovano i file, lasciare invariato il resto, il file fip.txt verra' creato nella directory attuale in caso 
#si decida di modificare il percorso di creazione e' possbile farlo  
#####################FINE SCRIPT###########################################################################################################
