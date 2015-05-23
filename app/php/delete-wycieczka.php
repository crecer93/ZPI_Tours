<?php
include('baza-danych.php');

$id_wycieczki = $_POST['id_wycieczki'];

$sql = "DELETE FROM wycieczki WHERE id_wycieczki=".$id_wycieczki;

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

//Dopóki nie przejdziemy na InnoDB
$sql = "DELETE FROM uczestnicy WHERE id_wycieczki=".$id_wycieczki;
mysql_query($sql);
$sql = "DELETE FROM rezerwacje WHERE id_wycieczki=".$id_wycieczki;
mysql_query($sql);

mysql_close($con);
echo json_encode($json);
?> 