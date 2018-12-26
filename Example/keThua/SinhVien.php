<?php

class SinhVien extends ConNguoi
{

    private $maSinhVien;

    private $diemTb;

    public function __construct($ten, $tuoi, $gioiTinh, $maSinhVien, $diemTb)
    {
        parent::__construct($ten, $tuoi, $gioiTinh);
        $this->maSinhVien = $maSinhVien;
        $this->diemTb = $diemTb;
    }

    public function hienThi()
    {
        echo "ma : " . $this->maSinhVien . "\n";
        parent::hienThi();
        echo "diem trung binh : " . $this->diemTb . "\n";
    }
}
?>