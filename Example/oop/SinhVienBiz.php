<?php

class SinhVienBiz extends SinhVien
{

    private $diemMaketing;

    private $diemSales;

    public function __construct()
    {}

    public function setDiemMaketing($diemMaketing)
    {
        $this->diemMaketing = $diemMaketing;
    }

    public function setDiemSales($diemSales)
    {
        $this->diemSales = $diemSales;
    }

    public function getDiemMaketing()
    {
        return $this->diemMaketing;
    }

    public function getDiemSales()
    {
        return $this->diemSales;
    }

    public function getDiemTB()
    {
        return ($this->diemMaketing * 2 + $this->diemSales) / 3;
    }

    public function hienThi()
    {
        parent::hienThi();
        echo "Diem Maketing : " . $this->diemMaketing . "\n";
        echo "Diem Sales : " . $this->diemSales . "\n";
    }
}
?>