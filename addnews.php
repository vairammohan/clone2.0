<?php

//http://localhost/FILEserver/addnews.php?newshead=test&content=test

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "securefileportal";

//class
class JSON{
		public $status=false;
		public $message ="Record not found";
	}
class Message {
		public $result = "";
	   }

ini_set( "display_errors", 0);
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    
	$myObj = new Message();
			$JsonObj=new JSON();
	         $JsonObj->message="Connection error";
		
		     $myObj->result=$JsonObj;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
	die($myObj->$conn->connect_error);
} 
if($_SERVER['REQUEST_METHOD']=='POST'){

$head=$_GET['newshead'];
$content=$_GET['content'];
$image = $_POST['image'];


$sql = "INSERT INTO news (newshead, content)
VALUES ('$head','$content')";

if ($conn->query($sql) === TRUE) {
	
	$id=$conn->insert_id;
	
	$path = "uploads/news/$id.png";
 
 $actualpath = "http://localhost/fileserver/$path";

 $localpath="C:/xampp/htdocs/fileserver/$path";
 
 $sql = "INSERT INTO news(newsimage) VALUES ('$id.png')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
	
   $myObj = new Message();
			$JsonObj=new JSON();
	         $JsonObj->status=true;
	         $JsonObj->message=$conn->insert_id."News Added sucessfully";
		
		     $myObj->result=$JsonObj;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
 }
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();

?>

