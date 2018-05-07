<?php
 
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "laptop_sale";

$uid=$_GET['user'];
    $file_path = "uploads/";
     $file_name=basename( $_FILES['uploaded_file']['name']);
    $file_path = $file_path . basename( $_FILES['uploaded_file']['name']);
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path) ){

      $sql ="INSERT INTO fileserver (userid,file) VALUES (uid,'$file_name')";
 ini_set( "display_errors", 0);

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
        
    }

 ?>
          
