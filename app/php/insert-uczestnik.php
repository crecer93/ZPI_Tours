<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$id_wycieczki = $_POST['id_wycieczki'];

$sql = "INSERT INTO uczestnicy (id_uzytkownika, id_wycieczki)
	VALUES (".$id_uzytkownika.", ".$id_wycieczki.")";

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 