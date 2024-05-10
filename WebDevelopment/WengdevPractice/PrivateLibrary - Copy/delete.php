<?php
$isbn = $_POST['isbn'];
$table_name = $_POST['table_name'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include "connection.php";
    $stmt = $table_name == "book_finished" ? $conn->prepare("DELETE FROM book_finished WHERE isbn=?") : $conn->prepare("DELETE FROM book_unfinished WHERE isbn=?");
    $stmt->bind_param("s", $isbn);
    $stmt->execute();
    $stmt->close();
    $conn->close();
}
?>