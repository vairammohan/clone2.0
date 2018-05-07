<?php
require_once 'dbDetails.php';

ini_set( "display_errors", 0);
//connecting to the db
$con = mysqli_connect(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME) or die("Unable to connect");
 
//sql query
$sql = "SELECT * FROM `news`";
 
//getting result on execution the sql query
$result = mysqli_query($con,$sql);
 
//response array
$response = array();
 
$response['error'] = false;
 
$response['message'] = "PDfs fetched successfully.";
 
$response['news'] = array();
 
//traversing through all the rows
 
while($row =mysqli_fetch_array($result)){
    $temp = array();
    $temp['id'] = $row['newsid'];
    $temp['newshead'] = $row['newshead'];
    $temp['content'] = $row['content'];
   $temp['newsimage'] = $row['newsimage'];
    $temp['rate']=$row['rate'];
    array_push($response['news'],$temp);
}
 
echo json_encode($response);

?>