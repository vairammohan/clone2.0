<?php
require_once 'dbDetails.php';

ini_set( "display_errors", 0);
//connecting to the db
$con = mysqli_connect(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME) or die("Unable to connect");
 
//sql query
$sql = "SELECT * FROM `games`";
 
//getting result on execution the sql query
$result = mysqli_query($con,$sql);
 
//response array
$response = array();
 
$response['error'] = false;
 
$response['message'] = "PDfs fetched successfully.";
 
$response['games'] = array();
 
//traversing through all the rows
 
while($row =mysqli_fetch_array($result)){
    $temp = array();
    $temp['id'] = $row['gameid'];
    $temp['url'] = $row['link'];
    $temp['image'] = $row['gimage'];
    array_push($response['games'],$temp);
}
 
echo json_encode($response);