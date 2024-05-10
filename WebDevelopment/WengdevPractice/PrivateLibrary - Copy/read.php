<?php
$table_name = $_GET['table_name'];
$keyword = $_GET['keyword'];

// if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include 'connection.php';

    $sql = "SELECT * FROM $table_name 
    WHERE isbn LIKE '%$keyword%'
        OR title LIKE '%$keyword%'
        OR author LIKE '%$keyword%'
        OR year LIKE '%$keyword%';";
    $result = mysqli_query($conn, $sql);

    $data = array();
    if ($result && mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_assoc($result)) {
            $data[] = $row;
        }
    }

    mysqli_close($conn);

    echo json_encode($data);
// }
?>