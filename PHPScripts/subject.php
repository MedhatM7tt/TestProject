<?php
require 'init.php';
$Acc = $_POST["Acc"];
$sql = "select ID from `student` where (ID = '$Acc' or Email = '$Acc')" 
$result =mysqli_query($conn,$sql);
$response = array();
if(mysqli_num_rows($result)==1){
	$ID=mysqli_fetch_assoc($result)["ID"];
	$sql2 = "select course_code from `registeration` where(student_id = '$ID')";
	$subject_query = mysqli_query($conn,$sql2);
	if(mysqli_num_rows($result)>=1){
		while ($row = mysqli_fetch_assoc($result)) {
			$subjectCode=$row["course_code"];
			array_push($response,array('subjectCode' => $subjectCode));
		}
		echo json_encode($response);
	}
	else{
		array_push($response,array('code'=>"fail",'message'=>"You haven't registered yet Please check your department"));
		echo json_encode($response);
	}
} 
else{
	array_push($response,array('code'=>"fail",'message'=>"Error Please check your department"));
	echo json_encode($response);
}




?>