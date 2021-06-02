<?php
	include "connect.php";
	$mangspmoinhat = array();
	$query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 10";
	$data = mysqli_query($conn,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangspmoinhat,new Sanphammoi(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['idsanpham']));
	}
	echo json_encode($mangspmoinhat);
	class Sanphammoi{
		function Sanphammoi($id,$tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idsanpham){
			$this ->id =$id;
			$this ->tensanpham =$tensanpham;
			$this ->giasanpham =$giasanpham;
			$this ->hinhanhsanpham =$hinhanhsanpham;
			$this ->motasanpham =$motasanpham;
			$this ->idsanpham =$idsanpham;

		}
	}
?>