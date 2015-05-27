<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$id_wycieczki = $_POST['id_wycieczki'];
$czy_zaakceptowana = $_POST['czy_zaakceptowana'];

$sql = "START TRANSACTION;";

if($czy_zaakceptowana == 0) {
	$sql = "UPDATE rezerwacje SET czy_zaakceptowana=0
		WHERE id_uzytkownika=".$id_uzytkownika." AND id_wycieczki=".$id_wycieczki;
	mysql_query($sql);
} else {
	$sql = "UPDATE rezerwacje SET czy_zaakceptowana=1
		WHERE id_uzytkownika=".$id_uzytkownika." AND id_wycieczki=".$id_wycieczki;
	mysql_query($sql);

	$sql = "INSERT INTO uczestnicy (id_uzytkownika, id_wycieczki)
		VALUES (".$id_uzytkownika.", ".$id_wycieczki.")";
	mysql_query($sql);
}

	
$sql = "COMMIT;";
$result = mysql_query($sql);

$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 