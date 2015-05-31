<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$id_wycieczki = $_POST['id_wycieczki'];

$sql = "SELECT COUNT(id_uzytkownika) AS c FROM uczestnicy WHERE id_uzytkownika=".$id_uzytkownika." AND id_wycieczki=".$id_wycieczki." GROUP BY id_uzytkownika"; 
$result = mysql_query($sql);
$counter = mysql_fetch_assoc($result)['c'];
if($counter == 0) {
	$sql = "SELECT COUNT(id_uzytkownika) AS c FROM rezerwacje WHERE id_uzytkownika=".$id_uzytkownika." AND id_wycieczki=".$id_wycieczki." GROUP BY id_uzytkownika";
	$result = mysql_query($sql);
	$counter = mysql_fetch_assoc($result)['c'];
}
$json = array();

if($counter > 0){
	$json['wyswietl']='0';
} else {
	$json['wyswietl']='1';
}

mysql_close($con);
echo json_encode($json);
?> 