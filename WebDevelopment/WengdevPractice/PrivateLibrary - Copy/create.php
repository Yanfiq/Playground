<?php
$isbn = $_POST['isbn'];
$title = $_POST['title'];
$author = $_POST['author'];
$year = $_POST['year'];
$table_name = $_POST['table_name'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include "connection.php";
    if($table_name == "book_finished"){
        $stmt = $conn->prepare("INSERT INTO book_finished (isbn, title, author, year) VALUES (?, ?, ?, ?)");
    }else{
        $stmt = $conn->prepare("INSERT INTO book_unfinished (isbn, title, author, year) VALUES (?, ?, ?, ?)");
    }
    $stmt->bind_param("sssi", $isbn, $title, $author, $year);
    $stmt->execute();
    $stmt->close();
    $conn->close();
}
?>