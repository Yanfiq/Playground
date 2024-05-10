<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "library";

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '$dbname';";
$result = $conn->query($sql);

if ($result->num_rows == 0) {
        // Database does not exist, create it
        $create_db_sql = "CREATE DATABASE $dbname";
        if ($conn->query($create_db_sql) === TRUE) {
                echo "Database created successfully<br>";
                $conn->select_db($dbname);
                $table_creation_query = "
                CREATE TABLE book_finished (
                        isbn VARCHAR(13) PRIMARY KEY,
                        title TEXT,
                        author TEXT,
                        year YEAR
                );
                CREATE TABLE book_unfinished (
                        isbn VARCHAR(13) PRIMARY KEY,
                        title TEXT,
                        author TEXT,
                        year YEAR
                );";
                if ($conn->multi_query($table_creation_query)) {
                    echo "Tables created successfully<br>";
                } else {
                    echo "Error creating tables: " . $conn->error;
                    $conn->close();
                    exit; // Exit script if table creation fails
                }

        } else {
                echo "Error creating database: " . $conn->error;
                $conn->close();
                exit; // Exit script if database creation fails
        }
}
$conn->select_db($dbname);

?>