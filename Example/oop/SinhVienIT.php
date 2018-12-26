<?php

class SinhVienIT extends SinhVien
{

    private $diemHTML;

    private $diemCSS;

    private $diemPHP;

    public function __construct()
    {}

    public function setDiemHTML($diemHTML)
    {
        $this->diemHTML = $diemHTML;
    }

    public function setDiemCSS($diemCSS)
    {
        $this->diemCSS = $diemCSS;
    }

    public function setDiemPHP($diemPHP)
    {
        $this->diemPHP = $diemPHP;
    }

    public function getDiemHTML()
    {
        return $this->diemHTML;
    }

    public function getDiemCSS()
    {
        return $this->diemCSS;
    }

    public function getDiemPHP()
    {
        return $this->diemPHP;
    }

    public function getDiemTB()
    {
        return ($this->diemPHP + $this->diemCSS + $this->diemHTML) / 3;
    }

    public function hienThi()
    {
        parent::hienThi();
        echo "Diem HTML : " . $this->diemHTML . "\n";
        echo "Diem CSS : " . $this->diemCSS . "\n";
        echo "Diem PHP : " . $this->diemPHP . "\n";
    }
}