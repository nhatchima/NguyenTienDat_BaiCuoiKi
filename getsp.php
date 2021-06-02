<?php
	include "connect.php";
	$page = $_GET['page'];
	$idsp = $_POST['idsanpham'];
	$volumn = 5;
	$range = ($page - 1) * $volumn;
	$mangsanpham = array();
	$query = "SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $range,$volumn";
	$data = mysqli_query($conn, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangsanpham,new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['idsanpham']));
			
	}
	echo json_encode($mangsanpham);
	class Sanpham{
		function Sanpham($id,$tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idsanpham){

			$this->id =$id;
			$this->tensanpham =$tensanpham;
			$this->giasanpham =$giasanpham;
			$this->hinhanhsanpham =$hinhanhsanpham;
			$this->motasanpham =$motasanpham;
			$this->idsanpham =$idsanpham;
		}
	}
?>