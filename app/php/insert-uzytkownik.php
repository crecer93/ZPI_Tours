<?php
$host="127.0.0.1"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="zpi_tours"; //replace with database name

$email = $_POST['email'];
$haslo = $_POST['haslo'];
$imie = $_POST['imie'];
$nazwisko = $_POST['nazwisko'];
$plec = $_POST['plec'];//WA�NE! P�e� musi by� 1 (m�czyzna) albo 2 (kobieta)
$id_miasta = $_POST['id_miasta'];
$moderator = $_POST['moderator'];

$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
mysql_query("SET NAMES 'utf8' COLLATE 'utf8_polish_ci';");
mysql_query("SET character_set_client = 'utf8'");
mysql_query("SET character_set_results = 'utf8'");
mysql_query("SET character_set_connection = 'utf8'");

$sql = "INSERT INTO Uzytkownicy (email, haslo, imie, nazwisko";
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