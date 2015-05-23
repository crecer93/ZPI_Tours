<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$id_wycieczki = $_POST['id_wycieczki'];

$sql = "INSERT INTO rezerwacje (id_uzytkownika, id_wycieczki, data_zlozenia)
	VALUES (".$id_uzytkownika.", ".$id_wycieczki.", CURRENT_TIMESTAMP)";

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 