<?php

abstract class SinhVien
{

    private $ma;

    private $ten;

    private $tuoi;

    private $gioiTinh;

    public function __construct()
    {}

    public function setMa($ma)
    {
        $this->ma = $ma;
    }

    public function setTen($ten)
    {
        $this->ten = $ten;
    }

    public function setTuoi($tuoi)
    {
        $this->tuoi = $tuoi;
    }

    public function setGioiTinh($gioiTinh)
    {
        $this->gioiTinh = $gioiTinh;
    }

    public function getMa()
    {
        return $this->ma;
    }

    public function getTen()
    {
        return $this->ten;
    }

    public function getTuoi()
    {
        return $this->tuoi;
    }

    public function getGioiTinh()
    {
        return $this->gioiTinh;
    }

    public function hienThi()
    {
        echo "ma sinh sien : " . $this->ma . "\n";
        echo "ten sinh sien : " . $this->ten . "\n";
        echo "tuoi sinh sien : " . $this->tuoi . "\n";
        echo "gioi tinh sinh sien : " . $this->gioiTinh . "\n";
        echo "Diem trung binh : " . $this->getDiemTB() . "\n";
    }

    abstract public function getDiemTB();
}
?>