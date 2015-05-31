<?php
include('baza-danych.php');

$email = $_POST['email'];
$hash = $_POST['hash'];

$sql = "SELECT id_uzytkownika, email, haslo, moderator FROM uzytkownicy WHERE email='".$email."' AND haslo='".$hash."'"; 
$result = mysql_query($sql);
$json = array();

if($result != false && mysql_num_rows($result)){
	$json['stan']='user';
	while($row=mysql_fetch_assoc($result)){
		$json['klient'][]=$row;
	}
} else {
	$sql = "SELECT super_id, super_email, super_haslo FROM super_admin WHERE super_email='".$email."' AND super_haslo='".$hash."'"; 
	$result = mysql_query($sql);
	
	if($result != false){
		$json['stan']='admin';
		while($row=mysql_fetch_assoc($result)){
			$json['klient'][]=$row;
		}
	} else {
		$json['stan']='nope';
	}
}

mysql_close($con);
echo json_encode($json);
?> 