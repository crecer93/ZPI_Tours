<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];
$moderator = $_POST['moderator'];//Mo¿e byæ 0 lub 1

$sql = "UPDATE uzytkownicy SET moderator=".$moderator." WHERE id_uzytkownika=".$id_uzytkownika;

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 