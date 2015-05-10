<?php
$host="mysql1.ph-hos.osemka.pl"; //replace with database hostname 
$username="1430907338_f"; //replace with database username 
$password="zpi2015"; //replace with database password 
$db_name="1287631_zpitours"; //replace with database name

//error_reporting(E_ALL ^ E_DEPRECATED);
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
mysql_query("SET NAMES 'utf8' COLLATE 'utf8_polish_ci';");
mysql_query("SET character_set_client = 'utf8'");
mysql_query("SET character_set_results = 'utf8'");
mysql_query("SET character_set_connection = 'utf8'");

$sql = "SELECT email, haslo FROM uzytkownicy"; 
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