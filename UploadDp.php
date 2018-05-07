<?php
 
//class
class JSON{
		public $status=false;
		public $message ="Cant update";
	}
class Message {
		public $result = "";
	   }


 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
$id=$_POST['user'];
 
 //require_once('dbConnect.php');
$con = mysqli_connect("localhost:3306","root","","laptop_sale");
 
 
 
 $path = "uploads/dp/$id.png";
 
 $actualpath = "http://localhost/$path";

 $localpath="C:/xampp/htdocs/laptop/uploads/dp/$id.png";
 
 $sql = "UPDATE registration SET dp='$actualpath' where regid=$id";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));


                           $myObj = new Message();
	         $JsonObj=new JSON();
	         $JsonObj->status=true;
	         $JsonObj->message="DP Uploaded Sucessfully";
		
		     $myObj->result=$JsonObj;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
} else {
    $JsonObj=new JSON();
	         
		
		   $myObj->result=$JsonObj;

			 $myJSON = json_encode($myObj);
	echo $myJSON;
}
 
 mysqli_close($con);
 
}

?>