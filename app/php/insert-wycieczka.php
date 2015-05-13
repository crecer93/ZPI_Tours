<?php
include('baza-danych.php');

$liczba_miejsc = $_POST['liczba_miejsc'];
$nazwa = $_POST['nazwa'];
$opis = $_POST['opis'];
$dlugosc_trasy = $_POST['dlugosc_trasy'];
$poziom_trudnosci = $_POST['poziom_trudnosci'];//WA¯NE! Poziom musi byæ 1 (³atwy), 2 (œredni) lub 3 (trudny)
$lokalizacja = $_POST['lokalizacja'];
$data_poczatku = $_POST['data_poczatku'];
$data_konca = $_POST['data_konca'];
$cena = $_POST['cena'];
$id_moderatora = $_POST['id_moderatora'];

$sql = "INSERT INTO wycieczki (liczba_miejsc, nazwa";
if($opis != "")
	$sql .= ", opis";
$sql .= ", dlugosc_trasy, poziom_trudnosci, lokalizacja, data_poczatku, data_konca, cena, id_moderatora";
$sql .= ") VALUES("
	."".$liczba_miejsc.", "
	."'".$nazwa."', ";
if($opis != "")
	$sql .= "'".$opis."', ";
$sql .= "".$dlugosc_trasy.", "
	."".$poziom_trudnosci.", "
	."'".$lokalizacja."', "
	."'".$data_poczatku."', "
	."'".$data_konca."', "
	."".$cena.", "
	."".$id_moderatora;
$sql .= ")";

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
//echo $sql;
echo json_encode($json);
?> 