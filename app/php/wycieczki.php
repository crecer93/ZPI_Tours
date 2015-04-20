<?php
$host="127.0.0.1"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="zpi_tours"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
$sql = "select id_wycieczki from wycieczki"; 
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
while($row=mysql_fetch_assoc($result)){
$json['wycieczki'][]=$row;
}
}
mysql_close($con);
echo json_encode($json); 
?> 