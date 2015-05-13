<?php
include('baza-danych.php');

$sql = "SELECT email, haslo FROM Uzytkownicy"; 
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
	while($row=mysql_fetch_assoc($result)){
		$json['klient'][]=$row;
	}
}
mysql_close($con);
echo json_encode($json); 
?> 