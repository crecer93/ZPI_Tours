<?php
$host="127.0.0.1"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="zpi_tours"; //replace with database name


$id = $_POST['id_wycieczki'];
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

$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
mysql_query("SET NAMES 'utf8' COLLATE 'utf8_polish_ci';");
mysql_query("SET character_set_client = 'utf8'");
mysql_query("SET character_set_results = 'utf8'");
mysql_query("SET character_set_connection = 'utf8'");

$pierwsze = TRUE;

$sql = "UPDATE Wycieczki SET ";
if($liczba_miejsc != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "liczba_miejsc="."".$liczba_miejsc."";
	$pierwsze = FALSE;
}
if($nazwa != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "nazwa="."'".$nazwa."'";
	$pierwsze = FALSE;
}
if($opis != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "opis="."'".$opis."'";
	$pierwsze = FALSE;
}
if($dlugosc_trasy != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "dlugosc_trasy="."".$dlugosc_trasy."";
	$pierwsze = FALSE;
}
if($poziom_trudnosci != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "poziom_trudnosci="."".$poziom_trudnosci."";
	$pierwsze = FALSE;
}
if($lokalizacja != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "lokalizacja="."'".$lokalizacja."'";
	$pierwsze = FALSE;
}
if($data_poczatku != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "data_poczatku="."'".$data_poczatku."'";
	$pierwsze = FALSE;
}
if($data_konca != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "data_konca="."'".$data_konca."'";
	$pierwsze = FALSE;
}
if($cena != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "cena="."'".$cena."'";
	$pierwsze = FALSE;
}
if($id_moderatora != "") {
	if(!$pierwsze)
		$sql .=", ";
	$sql .= "id_moderatora="."'".$id_moderatora."'";
	$pierwsze = FALSE;
}

$sql .= " WHERE id_wycieczki=".$id;

$result = mysql_query($sql);
$json = array();
$json['sukces']=$result;

mysql_close($con);
echo json_encode($json);
?> 