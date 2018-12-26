<?php
session_start();
if (isset($_SESSION["userLogin"])) {
    echo $_SESSION["userLogin"] . " da login roi<br/>";
    print_r($_SESSION);
} else {
    echo 'ban phai dang nhap';
}
?>