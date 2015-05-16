<?php
include('baza-danych.php');

$id_wycieczki = $_POST['id_wycieczki'];

//Pobranie danych wycieczki
$sql = "SELECT * FROM wycieczki WHERE id_wycieczki=".$id_wycieczki; 
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
	$json['wycieczka'][]=mysql_fetch_assoc($result);
}

$id_moderatora = $json['wycieczka'][0]['id_moderatora'];

//Pobranie danych administratora
$sql = "SELECT email FROM uzytkownicy JOIN miasta ON(uzytkownicy.id_miasta=miasta.id_miasta) WHERE id_uzytkownika=".$id_moderatora;
$result = mysql_query($sql);

if(mysql_num_rows($result)){
	$json['moderator'][]=mysql_fetch_assoc($result);
}

//Pobranie wszystkich uczestników
$sql = "SELECT email, nazwa_miasta FROM uzytkownicy JOIN uczestnicy USING(id_uzytkownika) LEFT OUTER JOIN miasta USING(id_miasta) WHERE id_wycieczki=".$id_wycieczki;
$result = mysql_query($sql);

if(mysql_num_rows($result)){
	while($row=mysql_fetch_assoc($result)){
		$json['uczestnicy'][]=$row;
	}
}

mysql_close($con);
echo json_encode($json); 
?> 