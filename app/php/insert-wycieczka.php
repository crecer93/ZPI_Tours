<?php
$host="127.0.0.1"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="zpi_tours"; //replace with database name

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

error_reporting(E_ALL ^ E_DEPRECATED);
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
mysql_query("SET NAMES 'utf8' COLLATE 'utf8_polish_ci';");
mysql_query("SET character_set_client = 'utf8'");
mysql_query("SET character_set_results = 'utf8'");
mysql_query("SET character_set_connection = 'utf8'");

$sql = "INSERT INTO Wycieczki (liczba_miejsc, nazwa";
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