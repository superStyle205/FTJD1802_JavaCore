<?php

class ConNguoi
{

    private $ten;

    private $tuoi;

    private $gioiTinh;

    public function __construct($ten, $tuoi, $gioiTinh = "Nu")
    {
        $this->ten = $ten;
        $this->tuoi = $tuoi;
        $this->gioiTinh = $gioiTinh;
    }

    public function hienThi()
    {
        echo "ten : " . $this->ten . "\n";
        echo "tuoi : " . $this->tuoi . "\n";
        echo "gioi tinh : " . $this->gioiTinh . "\n";
    }
}
?>