<?php
session_start();
if (isset($_SESSION["userLogin"])) {
    unset($_SESSION["userLogin"]);
    echo 'da logout';
    header("location:index.html");
} else {
    echo 'co login dau ma doi logout';
    header("location:https://google.com.vn");
}
?>