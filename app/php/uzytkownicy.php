<?php
include('baza-danych.php');

$sql = "SELECT * FROM uzytkownicy";

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