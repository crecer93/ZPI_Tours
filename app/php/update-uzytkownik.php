<?php
$host="127.0.0.1"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="zpi_tours"; //replace with database name


$id = $_POST['id'];
$email = $_POST['email'];
$haslo = $_POST['haslo'];
$imie = $_POST['imie'];
$nazwisko = $_POST['nazwisko'];
$plec = $_POST['plec'];//WA¯NE! P³eæ musi byæ 1 (mê¿czyzna) albo 2 (kobieta)
$id_miasta = $_POST['id_miasta'];
$moderator = $_POST['moderator'];

/*
$id = 22;
$email = 'doom@doom.com';
$haslo = 'somethingus';
$imie = 'Jan';
$nazwisko = 'Testicular';
$plec = 2;//WA¯NE! P³eæ musi byæ 1 (mê¿czyzna) albo 2 (kobieta)
$id_miasta = 100;
$moderator = 0;
*/

error_reporting(E_ALL ^ E_DEPRECATED);
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
mysql_query("SET NAMES 'utf8' COLLATE 'utf8_polish_ci';");
mysql_query("SET character_set_client = 'utf8'");
mysql_query("SET character_set_results = 'utf8'");
mysql_query("SET character_set_connection = 'utf8'");

$pierwsze = TRUE;

$sql = "UPDATE uzytkownicy SET ";
if($email != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "email="."'".$email."'";
	$pierwsze = FALSE;
}
if($haslo != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "haslo="."'".$haslo."'";
	$pierwsze = FALSE;
}
if($imie != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "imie="."'".$imie."'";
	$pierwsze = FALSE;
}
if($nazwisko != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "nazwisko="."'".$nazwisko."'";
	$pierwsze = FALSE;
}
if($plec != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "plec=".$plec."";
	$pierwsze = FALSE;
}
if($id_miasta != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "id_miasta=".$id_miasta."";
	$pierwsze = FALSE;
}
if($moderator != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "moderator=".$moderator."";	
	$pierwsze = FALSE;
}
$sql .= " WHERE id_uzytkownika=".$id;

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 