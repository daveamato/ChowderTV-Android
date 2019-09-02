<?php error_reporting(0);
require_once("thumbnail_images.class.php");
function Update($table_name, $form_data, $where_clause='')
{
    // check for optional where clause
    $whereSQL = '';
    if(!empty($where_clause))
    {
        // check to see if the 'where' keyword exists
        if(substr(strtoupper(trim($where_clause)), 0, 5) != 'WHERE')
        {
            // not found, add key word
            $whereSQL = " WHERE ".$where_clause;
        } else
        {
            $whereSQL = " ".trim($where_clause);
        }
    }
    // start the actual SQL statement
    $sql = "UPDATE ".$table_name." SET ";

    // loop and build the column /
    $sets = array();
    foreach($form_data as $column => $value)
    {
         $sets[] = "`".$column."` = '".$value."'";
    }
    $sql .= implode(', ', $sets);

    // append the where statement
    $sql .= $whereSQL;
 		 
    // run and return the query result
    return mysql_query($sql);
}
class k_wallpaper
{

//Category Query	
	function addCategory()
	{
		
		$albumimgnm=rand(0,99999)."_".$_FILES['image']['name'];
			 $pic1=$_FILES['image']['tmp_name'];
			  if(!is_dir('images'))
			   {
			
			   		mkdir('images', 0777);
			   }
			  $tpath1='images/'.$albumimgnm;
				
				 copy($pic1,$tpath1);
				 
				 
					    $thumbpath='images/thumbs/'.$albumimgnm;
						$obj_img = new thumbnail_images();
						$obj_img->PathImgOld = $tpath1;
						$obj_img->PathImgNew =$thumbpath;
						$obj_img->NewWidth = 100;
						$obj_img->NewHeight = 100;
						if (!$obj_img->create_thumbnail_images()) 
						  {
							echo $_SESSION['msg']="Thumbnail not created... please upload image again";
						    exit;
						  }
						  else
						  {
						  		 
								$cat_result=mysql_query('INSERT INTO `tbl_category` (`category_name` ,`category_image`) VALUES (  \''.addslashes($_POST['category_name']).'\',\''.$albumimgnm.'\')');
		
								
						  }

		
	}
	
	function editCategory()
	{
			 
			 
	if($_FILES['image']['name']=="")
		 {
		
		$cat_result=mysql_query('UPDATE `tbl_category` SET `category_name`=\''.addslashes($_POST['category_name']).'\' WHERE cid=\''.$_GET['cat_id'].'\'');

		}
		else
		{
		
			//Image Unlink
			
			$img_res=mysql_query('SELECT * FROM tbl_category WHERE cid=\''.$_GET['cat_id'].'\'');
			$img_row=mysql_fetch_assoc($img_res);
			
			if($img_row['category_image']!="")
			{
				unlink('images/'.$img_row['category_image']);
				unlink('images/thumbs/'.$img_row['category_image']);
			}	
		
			//Image Upload
			$albumimgnm=rand(0,99999)."_".$_FILES['image']['name'];
			 $pic1=$_FILES['image']['tmp_name'];
			   
		
			   if(!is_dir('images'))
			   {
			
			   		mkdir('images', 0777);
			   }
			  $tpath1='images/'.$albumimgnm;
				
				 copy($pic1,$tpath1);
				 
				 
					    $thumbpath='images/thumbs/'.$albumimgnm;
						$obj_img = new thumbnail_images();
						$obj_img->PathImgOld = $tpath1;
						$obj_img->PathImgNew =$thumbpath;
						$obj_img->NewWidth = 100;
						$obj_img->NewHeight = 100;
						if (!$obj_img->create_thumbnail_images()) 
						  {
							echo $_SESSION['msg']="Thumbnail not created... please upload image again";
						    exit;
						  }
						  else
						  {
						  		 
								 $cat_result=mysql_query('UPDATE `tbl_category` SET `category_name`=\''.addslashes($_POST['category_name']).'\',`category_image`=\''.$albumimgnm.'\' WHERE cid=\''.$_GET['cat_id'].'\'');
 						  }
		}

	}
	
	function deleteCategory()
	{
		
		
		$channel_res=mysql_query('SELECT * FROM `tbl_channels` WHERE cat_id=\''.$_GET['cat_id'].'\'');
		while($channel_row=mysql_fetch_assoc($channel_res)){
		
			if($channel_row['channel_url']!="")
			{
				$string=$channel_row['channel_url'];
				list($a,$b)=split('channel/',$string);
				unlink('channel/'.$b);
			}
			if($channel_row['channel_thumbnail']!="")
			{
				unlink('images/thumbs/'.$channel_row['channel_thumbnail']);
				unlink('images/'.$channel_row['channel_thumbnail']);
			}
		}
		$channel_result=mysql_query('DELETE FROM `tbl_channels` WHERE cat_id=\''.$_GET['cat_id'].'\'');
		
		$img_res=mysql_query('SELECT * FROM tbl_category WHERE cid=\''.$_GET['cat_id'].'\'');
		$img_row=mysql_fetch_assoc($img_res);
			
			if($img_row['category_image']!="")
			{
				unlink('images/thumbs/'.$img_row['category_image']);
				unlink('images/'.$img_row['category_image']);
				 
			}	
		
		$cat_result=mysql_query('DELETE FROM `tbl_category` WHERE cid=\''.$_GET['cat_id'].'\'');
	}

 
//Image Gallery
	function addchannel()
	{
	if($_POST['channel_url']!="")
	{
	
	
	if($_FILES['thumbnail']['name']!="")	
	{
	$thumbname=rand(0,99999)."_".$_FILES['thumbnail']['name'];
			 $thumb1=$_FILES['thumbnail']['tmp_name'];
			 if(!is_dir('images'))
			   {
			
			   		mkdir('images', 0777);
			   }
			  $tpath1='images/'.$thumbname;
				
				 copy($thumb1,$tpath1);
				 
				 
					    $thumbpath='images/thumbs/'.$thumbname;
						$obj_img = new thumbnail_images();
						$obj_img->PathImgOld = $tpath1;
						$obj_img->PathImgNew =$thumbpath;
						$obj_img->NewWidth = 100;
						$obj_img->NewHeight = 100;
						if (!$obj_img->create_thumbnail_images()) 
						  {
							echo $_SESSION['msg']="Thumbnail not created... please upload image again";
						    exit;
						  }
	}
	 $res=mysql_query('INSERT INTO `tbl_channels` (`cat_id`,`channel_title`,`channel_url`,`channel_thumbnail`,`channel_desc`) VALUES (\''.$_POST['category_id'].'\',\''.$_POST['channel_title'].'\',\''.$_POST['channel_url'].'\',\''.$thumbname.'\',
	 \''.addslashes($_POST['channel_description']).'\')');
	}
	}
		
	function editchannel()
	{
	/*	if($_POST['channel_url']="")
	{
		if($_FILES['thumbnail']['name']!="")
	{
		$thumbname=rand(0,99999)."_".$_FILES['thumbnail']['name'];
			 $thumb1=$_FILES['thumbnail']['tmp_name'];
			 if(!is_dir('images'))
			   {
			
			   		mkdir('images', 0777);
			   }
			  $tpath1='images/'.$thumbname;
				
				 copy($thumb1,$tpath1);
				 
				 
					    $thumbpath='images/thumbs/'.$thumbname;
						$obj_img = new thumbnail_images();
						$obj_img->PathImgOld = $tpath1;
						$obj_img->PathImgNew =$thumbpath;
						$obj_img->NewWidth = 72;
						$obj_img->NewHeight = 72;
						if (!$obj_img->create_thumbnail_images()) 
						  {
							echo $_SESSION['msg']="Thumbnail not created... please upload image again";
						    exit;
						  }
						  $channel_res=mysql_query('SELECT * FROM `tbl_gallery` WHERE id=\''.$_GET['channel_id'].'\'');
							  $channel_row=mysql_fetch_assoc($channel_res);
							  if($channel_row['channel_thumbnail']!="")
							{
							unlink('images/thumbs/'.$channel_row['channel_thumbnail']);
								unlink('images/'.$channel_row['channel_thumbnail']);
							}
	}
	else
	{
		$channel_res=mysql_query('SELECT * FROM `tbl_gallery` WHERE id=\''.$_GET['channel_id'].'\'');
		$channel_row=mysql_fetch_assoc($channel_res);
		$thumbname=$channel_row['channel_thumbnail'];
		
	}
	$res=mysql_query('UPDATE `tbl_gallery` SET `cat_id`=\''.$_POST['category_id'].'\',`channel_title`=\''.addslashes($_POST['channel_title']).'\',`channel_thumbnail`=\''.$thumbname.'\',`channel_desc`=\''.addslashes($_POST['channel_description']).'\' WHERE id=\''.$_GET['channel_id'].'\'');
	}
	*/
	if($_POST['channel_url']!="")
	{
	
	$channel=$_POST["channel_url"];
			
	if($_FILES['thumbnail']['name']!="")
	{
		$thumbname=rand(0,99999)."_".$_FILES['thumbnail']['name'];
			 $thumb1=$_FILES['thumbnail']['tmp_name'];
			 if(!is_dir('images'))
			   {
			
			   		mkdir('images', 0777);
			   }
			  $tpath1='images/'.$thumbname;
				
				 copy($thumb1,$tpath1);
				 
				 
					    $thumbpath='images/thumbs/'.$thumbname;
						$obj_img = new thumbnail_images();
						$obj_img->PathImgOld = $tpath1;
						$obj_img->PathImgNew =$thumbpath;
						$obj_img->NewWidth = 100;
						$obj_img->NewHeight = 100;
						if (!$obj_img->create_thumbnail_images()) 
						  {
							echo $_SESSION['msg']="Thumbnail not created... please upload image again";
						    exit;
						  }
						  $channel_res=mysql_query('SELECT * FROM `tbl_channels` WHERE id=\''.$_GET['channel_id'].'\'');
							  $channel_row=mysql_fetch_assoc($channel_res);
							  if($channel_row['channel_thumbnail']!="")
							{
							unlink('images/thumbs/'.$channel_row['channel_thumbnail']);
								unlink('images/'.$channel_row['channel_thumbnail']);
							}
	}
	else
	{
		$channel_res=mysql_query('SELECT * FROM `tbl_channels` WHERE id=\''.$_GET['channel_id'].'\'');
		$channel_row=mysql_fetch_assoc($channel_res);
		$thumbname=$channel_row['channel_thumbnail'];
	}
	$res=mysql_query('UPDATE `tbl_channels` SET `cat_id`=\''.$_POST['category_id'].'\',`channel_title`=\''.addslashes($_POST['channel_title']).'\',`channel_url`=\''.$_POST['channel_url'].'\',`channel_thumbnail`=\''.$thumbname.'\',`channel_desc`=\''.addslashes($_POST['channel_description']).'\' WHERE id=\''.$_GET['channel_id'].'\'');
	
	}
	}
	
	function deletechannel()
	{
			$channel_res=mysql_query('SELECT * FROM `tbl_channels` WHERE id=\''.$_GET['channel_id'].'\'');
			$channel_row=mysql_fetch_assoc($channel_res);
			
			if($channel_row['channel_thumbnail']!="")
			{
				unlink('images/thumbs/'.$channel_row['channel_thumbnail']);
				unlink('images/'.$channel_row['channel_thumbnail']);
			}
			$img_result=mysql_query('DELETE FROM `tbl_channels` WHERE id=\''.$_GET['channel_id'].'\'');
	}	
	function deletereview()
	{
		$img_result=mysql_query('DELETE FROM `tbl_review` WHERE id=\''.$_GET['channel_id'].'\'');
	}
	
	function editProfile()
 {
    
   $res=mysql_query('UPDATE `tbl_admin` SET `username`=\''.$_POST['username'].'\',`password`=\''.$_POST['password'].'\',`email`=\''.$_POST['email'].'\' WHERE id=\''.$_SESSION['id'].'\'');
 }	
}


?>