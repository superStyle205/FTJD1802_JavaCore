<?php
session_start();
if (isset($_GET['id'])) {
    unset($_SESSION["listStudent"][$_GET['id']]);
    header("location:listStudent.php");
}
?>