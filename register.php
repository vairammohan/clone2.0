<?php

//http://localhost/laptop/register.php?fname=vadivel&lname=sunder&password=12345&email=vadi@gmail.com&mobileno=9876545678&address=rabgapar,chennai&city=theni

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "laptop_sale";

//class
class JSON{
		public $status=false;
		public $message ="Record not found";

	}
class Message {
		public $result = "";
                                   public $regid="";
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
                                       $myObj->regid=$conn->insert_id;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
	die($myObj->$conn->connect_error);
} 

$fname=$_POST['fname'];
$lname=$_POST['lname'];
$password=$_POST['password'];
$email=$_POST['email'];
$mobileno=$_POST['mobileno'];
$address=$_POST['address'];
$dp=$_POST['dp'];
$city=$_POST['city'];

$sql = "INSERT INTO registration (fname, lname, password,email,mobileno,address,city)
VALUES ('$fname','$lname','$password','$email','$mobileno','$address','$city')";

if ($conn->query($sql) === TRUE) {
   $myObj = new Message();
			$JsonObj=new JSON();
	         $JsonObj->status=true;
	         $JsonObj->message="Reigistration completed Sucessfully";
		
		     $myObj->result=$JsonObj;
                                       $myObj->regid=$conn->insert_id;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
} else {
    $JsonObj=new JSON();
	         
		
		     $myObj->result=$JsonObj;
                                       $myObj->regid=$conn->insert_id;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
}

$conn->close();
?>

