<?php
include('baza-danych.php');

$id_uzytkownika = $_POST['id_uzytkownika'];

$sql = "SELECT * FROM uzytkownicy WHERE id_uzytkownika=".$id_uzytkownika;


$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
	while($row=mysql_fetch_assoc($result)){
		$json['uzytkownik'][]=$row;
	}
}
mysql_close($con);
echo json_encode($json); 
?> 