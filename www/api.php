<?php include("includes/connection.php");
 
	
	include("purchase.php");	

	if(purchase_status() == false)
 	{
		echo "<p>Sorry, we are unable to verify your purchase.</p>";
		exit;
	}
	else
	{
	if(isset($_GET['cat_id']))
	{
		
		$cat_id=$_GET['cat_id'];		
	
			$query="SELECT * FROM tbl_channels
		LEFT JOIN tbl_category ON tbl_channels.cat_id= tbl_category.cid 
		where tbl_channels.cat_id='".$cat_id."' ORDER BY tbl_channels.id DESC";
		
	}
	else if(isset($_GET['review']))
	{
		$review = $_GET['review'];
				$cat_result=mysql_query('INSERT INTO `tbl_review` (`channel_id` ,`review`) VALUES (  \''.addslashes($_GET['channel_id']).'\',\''.$review.'\')');
				
				    $set['LIVETV'][] = array('msg' => 'Review added successfully...','success'=>'1');
    	$json = json_encode($set);
				
				echo $json;
		 die();

	}
	else if(isset($_GET['latest']))
	{
		$limit=$_GET['latest'];	 	
		$query="SELECT * FROM tbl_channels
		LEFT JOIN tbl_category ON tbl_channels.cat_id= tbl_category.cid 
		ORDER BY tbl_channels.id DESC LIMIT $limit";
	}
	else if(isset($_GET['tv_id']))
	{
		$tv_id=$_GET['tv_id'];
		 	
		$query="SELECT * FROM tbl_channels LEFT JOIN tbl_category ON tbl_channels.cat_id= tbl_category.cid WHERE tbl_channels.id='".$tv_id."' ";
	}
	
	
	else if(isset($_GET['search']))
	{
		$query="SELECT * FROM tbl_channels
		LEFT JOIN tbl_category ON tbl_channels.cat_id= tbl_category.cid 
		WHERE channel_title like '%".$_GET['search']."%' ";
	$sel = mysql_query($query);
	if(mysql_num_rows($sel)>0){
		
	}
	else
	{
		     $set['LIVETV'][]=array('msg' => 'No Channel Fount Try Different Keyword','Success'=>'0');
	  	     echo $val= str_replace('\\/', '/', json_encode($set,JSON_UNESCAPED_UNICODE));
			 die();
	}
	
		}
	else if(isset($_GET['channel_id']))
	{
		 //echo $_GET['channel_id'];
		$SQL1="select * from tbl_channels where id='".$_GET['channel_id']."'";
	
			$result1 = mysql_query($SQL1)or die(mysql_error());	
						
						$arr = array();
						while ($row1 = mysql_fetch_assoc($result1)) 
						{
				  
								$catArr=array();
								$catArr['id'] = $row1['id'];
								$catArr['cat_id'] = $row1['cat_id'];
								$catArr['channel_title'] = $row1['channel_title'];
								$catArr['channel_url'] = $row1['channel_url'];
								$catArr['channel_thumbnail'] = $row1['channel_thumbnail'];
								$catArr['channel_desc'] = $row1['channel_desc'];
								
								$SQL2 = "SELECT * FROM tbl_channels WHERE cat_id = '".$row1['cat_id']."'";
				       			$result2 = mysql_query($SQL2);
								
								$subvidArr=array();
								while ($row2 = mysql_fetch_assoc($result2)) 
								{	
				 					if($row1['id'] != $row2['id'])
									{									
									$temp = array('rel_id' => $row2['id'], 'rel_channel_title' => $row2['channel_title'] , 'rel__channel_id' =>$row2['video_id'],
									 'rel_channel_thumbnail' => $row2['channel_thumbnail']);
									$subvidArr[]=$temp;
									}
								}
								$catArr['related']=$subvidArr;
								$arr[$row1['cat_id']]=$catArr;
				  
						}
				 
				
				$shop_out = array();
				foreach ($arr as $key => $record) {
						 
						$shop_out['LIVETV'][] = $record;
						 
				}
				
				header( 'Content-Type: application/json; charset=utf-8' );
				$json = json_encode($shop_out);
				
				echo $json;
		 die();

	}
	
	else
	{
		$query="SELECT cid,category_name,category_image FROM tbl_category ORDER BY tbl_category.cid DESC";
	}
	
	$resouter = mysql_query($query);
     
    $set = array();
     
    $total_records = mysql_num_rows($resouter);
    if($total_records >= 1){
     
      while ($link = mysql_fetch_array($resouter, MYSQL_ASSOC)){
	   
        $set['LIVETV'][] = $link;
      }
    }
     
     echo $val= str_replace('\\/', '/', json_encode($set));
	} 	 
	 
?>