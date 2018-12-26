<?php

class NhanVien extends ConNguoi
{

    private $maNhanVien;

    private $luong;

    public function __construct($ten, $tuoi, $gioiTinh, $maNhanVien, $luong)
    {
        parent::__construct($ten, $tuoi, $gioiTinh);
        $this->maNhanVien = $maNhanVien;
        $this->luong = $luong;
    }

    public function hienThi()
    {
        echo "ma nhan vien : " . $this->maNhanVien . "\n";
        parent::hienThi();
        echo "luong : " . $this->luong . "\n";
    }
}
?>