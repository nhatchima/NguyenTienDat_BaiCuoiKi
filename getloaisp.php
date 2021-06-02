<?php
include "connect.php";
$query="SELECT * FROM loaisanpham";
$data=mysqli_query($conn,$query);
$mangloaisp=array();
while($row=mysqli_fetch_assoc($data)){
	array_push($mangloaisp, new loaisp(
		$row['id'],
	    $row['tensanpham'],
	    $row['hinhanhsanpham']));
}
echo json_encode($mangloaisp);
class loaisp{
	function loaisp($id,$tensanpham,$hinhanhsanpham){
    $this->id=$id;
    $this->tensanpham=$tensanpham;
    $this->hinhanhsanpham=$hinhanhsanpham;
	}
}
?>