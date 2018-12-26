<?php
/*
 * $handle = fopen ( "php://stdin", "r" );
 *
 * while ( true ) {
 * echo "nhap 1 so bat ky : ";
 *
 * // get so tu ban phim
 * $soBatKy = fgets ( $handle );
 *
 * if ($soBatKy > 0) {
 * break;
 * } else {
 * echo "khong phai so duong, moi nhap lai";
 * }
 * }
 */
$danhSach = array(
    "hoa 1" => 1212,
    "hoa 2" => "dsfaf",
    "hoa 3" => "adasfa"
);

unset($danhSach['hoa 2']);

echo "<pre>";
print_r($danhSach);
echo "</pre>";
?>
