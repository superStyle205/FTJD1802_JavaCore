<?php
include 'Animal.php';
include 'Duck.php';
include 'Dog.php';
include 'Cat.php';
include 'MyClass.php';

$cat = new Cat();
$dog = new Dog();
$duck = new Duck();

$myClass = new MyClass();
$myClass->speak($cat);

$myClass = new MyClass();
$myClass->speak($dog);

$myClass = new MyClass();
$myClass->speak($duck);
?>