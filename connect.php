<?php
$host="localhost";
$username='root';
$password="";
$database="store";

$conn=mysqli_connect($host,$username,$password,$database);
mysqli_query($conn,"SET NAMES 'utf8'");

?>