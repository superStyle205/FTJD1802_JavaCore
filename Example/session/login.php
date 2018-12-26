<?php
session_start();

if (isset($_POST["user"]) && isset($_POST["pass"])) {
    $u = $_POST["user"];
    $p = $_POST["pass"];
    if ($u == "Vui" && $p == "123") {
        $_SESSION["userLogin"] = $u;
        echo 'login thanh cong';
    } else
        echo 'sai user hoac pass';
}

?>