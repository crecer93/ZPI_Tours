<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$id_wycieczki = $_POST['id_wycieczki'];

$sql = "DELETE FROM uczestnicy WHERE id_wycieczki=".$id_wycieczki." AND id_uzytkownika=".$id_uzytkownika;

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 