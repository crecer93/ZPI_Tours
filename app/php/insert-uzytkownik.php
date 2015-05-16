<?php
include('baza-danych.php');

$email = $_POST['email'];
$haslo = $_POST['haslo'];
$imie = $_POST['imie'];
$nazwisko = $_POST['nazwisko'];
$plec = $_POST['plec'];//WA¯NE! P³eæ musi byæ 1 (mê¿czyzna) albo 2 (kobieta)
$id_miasta = $_POST['id_miasta'];
$moderator = $_POST['moderator'];

$sql = "INSERT INTO uzytkownicy (email, haslo, imie, nazwisko";
if($plec != "")
	$sql .= ", plec";
if($id_miasta != "")
	$sql .= ", id_miasta";
if($moderator != "")
	$sql .= ", moderator";
$sql .= ") VALUES("
	."'".$email."', "
	."'".$haslo."', "
	."'".$imie."', "
	."'".$nazwisko."'";
if($plec != "")
	$sql .= ", ".$plec;
if($id_miasta != "")
	$sql .= ", ".$id_miasta;
if($moderator != "")
	$sql .= ", ".$moderator;
$sql .= ")";

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 