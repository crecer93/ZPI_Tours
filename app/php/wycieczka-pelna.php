<?php
include('baza-danych.php');

$id_wycieczki = $_POST['id_wycieczki'];

$sql = "SELECT * FROM wycieczki WHERE id_wycieczki=".$id_wycieczki; 
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
	while($row=mysql_fetch_assoc($result)){
		$json['wycieczka'][]=$row;
	}
}
mysql_close($con);
echo json_encode($json); 
?> 