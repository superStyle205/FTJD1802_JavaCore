<?php
include 'SinhVien.php';
include 'SinhVienIT.php';
include 'SinhVienBiz.php';

echo "\n============================================\n";
$sv3 = new SinhVienIT();
$sv3->setMa("sv03");
$sv3->setTen("Sang");
$sv3->setTuoi(25);
$sv3->setGioiTinh("Duc");
$sv3->setDiemHTML(5.5);
$sv3->setDiemCSS(3);
$sv3->setDiemPHP(5);
$sv3->hienThi();
echo "\n============================================\n";
$sv4 = new SinhVienBiz();
$sv4->setMa("sv04");
$sv4->setTen("Tun~");
$sv4->setTuoi(24);
$sv4->setGioiTinh("Mai");
$sv4->setDiemMaketing(8.5);
$sv4->setDiemSales(7);
$sv4->hienThi();
?>