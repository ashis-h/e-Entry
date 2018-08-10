<?php  
	require "conn.php";
	$id=$_POST["tag"];
	$mysqli_query="SELECT * FROM  `emp` where ID='$id';";
	$result=mysqli_query($conn,$mysqli_query);
	$error="{'error':'Id not found'}";
	if(mysqli_num_rows($result)>0){
		while($row=mysqli_fetch_assoc($result)){
			$output=$row;
		}
		print(json_encode($output));
	}
	else
		print($error);
	mysqli_close($conn);
?>  