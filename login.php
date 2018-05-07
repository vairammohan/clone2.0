<?php

///http://localhost/fileserver/login.php?user=sdds@hjgd.com&pass=sjdjkbd

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "laptop_sale";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
ini_set( "display_errors", 0);
	$user=$_POST['user'];
	$password=$_POST['pass'];
	$sql = "SELECT * FROM registration where email='$user' and password='$password'";
	//echo $sql;

	$result = $conn->query($sql);

	class JSON{
		public $status=false;
		public $message ="Please check the username/password";
	}

	class Message {

		public $result = 0;
		

	   }

	class User {
        
		public $result ="";
		public $regid = 0;
		public $name  = "";
		public $email ="";
		public $mobileno="";
		public $city ="";

	   }

	   


	if ($result->num_rows > 0) {
		// output data of each row
			while($row = $result->fetch_assoc()) {

				$myObj = new User();
				$JsonObj=new JSON(); 
				
				$JsonObj->status= true;
				$JsonObj ->message ="Successfully login";

				$myObj->result= $JsonObj;
				$myObj->regid = $row['regid'];
				$myObj->name = $row['fname'].$row['lname'];
				$myObj->email = $row['email'] ;
				$myObj->mobileno =$row["mobileno"];
				$myObj->city = $row['city'];

				$myJSON = json_encode($myObj);

				//echo "id: " . $row["dp"]. " - Name: " . $row["city"]. " " . $row["lname"]. "<br>";
			}
		} else {

			$myObj = new Message();
			$JsonObj=new JSON();
		
		     $myObj->result=$JsonObj;

			 $myJSON = json_encode($myObj);

		}



	echo $myJSON;


	$conn->close();

//Read more: http://mrbool.com/how-to-create-a-login-page-with-php-and-mysql/28656#ixzz5B1YijpY0
    
?>