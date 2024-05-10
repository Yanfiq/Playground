<?php
$table = $_POST['table'];
$isbn = $_POST['isbn'];
$title = $_POST['title'];
$author = $_POST['author'];
$year = $_POST['year'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include "connection.php";
    $stmt = $table == "book_finished" ? $conn->prepare("UPDATE book_finished SET title=?, author=?, year=? WHERE isbn=?") : $conn->prepare("UPDATE book_unfinished SET title=?, author=?, year=? WHERE isbn=?");
    $stmt->bind_param("ssis", $title, $author, $year, $isbn);
    $stmt->execute();
    $stmt->close();
    $conn->close();
}
?>