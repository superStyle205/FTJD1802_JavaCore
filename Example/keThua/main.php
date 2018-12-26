<?php
include 'ConNguoi.php';
include 'NhanVien.php';
include 'SinhVien.php';

$conNguoi = new ConNguoi("Viet", 30, "Nam");
$conNguoi->hienThi();
echo "===============\n";

$conNguoi2 = new ConNguoi("An", 27);
$conNguoi2->hienThi();
echo "===============\n";

//$sv1 = new SinhVien("Lam", 25, "Nam", "sv01", 9.5);
//$sv1->hienThi();
echo "===============\n";

$nhanVien1 = new NhanVien("Khoa", 28, "Multi", "nv01", 30000);
$nhanVien1->hienThi();
?>