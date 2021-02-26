$data=Get-Date -Format "dd/MM/yyyy HH:mm"
$oip=""                                              #IP pubblico precedente 
$ip=(curl.exe -s https://checkip.amazonaws.com)      #IP pubblico attuale                                              
Write-Host "############################################################################################"
Write-Host "###########Script automatico di verifica online sito web data e ora: "$data "#############"
Write-Host "############################################################################################"
if((Test-Path "fip.txt") -ne "True"){
    Write-Host "Creo il file contenente l'indirizzo IP: "$ip
    Set-Content -Path "fip.txt" -Value "$ip" -Force 
}
$oip=type fip.txt
if($oip ="op")
{
  Write-Host "Sito Web Online sull'indirizzo IP "$ip
  java -jar /directory/email.jar "sito_online sull'indirizzo IP: "$ip   "sito_online"
}
else{
     Write-Host "il sito web  non e' online"
     Write-Host "Vecchio Indirizzo IP:" $oip
     Write-Host "Nuovo Indirizzo IP  :" $ip
     Set-Content -Path "fip.txt" -Value "$ip" -Force 
     java -jar /directory/email.jar "nome@dominio.com" "Si prega di sostituire il vecchio Indirizzo IP:"$oip" con il nuovo:  "$ip  "sito _non_online"
     java -jar /directory/UpdateAruba.jar $ip
}
pause
#sostituire directory con il percorso effettitvo di dove si trovano i file, lasciare invariato il resto, il file fip.txt verra' creato nella directory attuale in caso 
#si decida di modificare il percorso di creazione e' possbile farlo  

