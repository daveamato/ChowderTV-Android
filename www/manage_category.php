<?php include("includes/header.php");

	require("includes/function.php");
	$kwallpaper=new  k_wallpaper;
	
	
	//Get all Category 
	$qry="SELECT * FROM tbl_category";
	$result=mysql_query($qry);
	
	if(isset($_GET['cat_id']))
	{
		$kwallpaper->deleteCategory();
		
		 
		echo "<script>document.location='manage_category.php';</script>"; 
	    exit;
		
	}	
	 
?>
                
                <!-- h2 stays for breadcrumbs -->
              
                <div id="main">
                  <h2> Manage Category &raquo;</h2>
                
                
                	<form action="" class="jNice">
					 <h3 class="abtn"><a href="add_category.php?add=yes">Add Category</a></h3>
					 
                    	<table cellpadding="0" cellspacing="0">
						<tr align="center">
						<th>Category</th>						 
						<th>Category image</th>
						<th>Edit</th>
						<th>Delete</th>
						
						</tr>
						<?php
						
							$i=0;
							while($row=mysql_fetch_array($result))
							{
							
						?>	
						                    
							<tr <?php if($i%2==0){?>class="odd"<?php }?> align="center">
                                <td><?php echo $row['category_name'];?></td>
                                <td><img src="images/thumbs/<?php echo $row['category_image'];?>" /></td>
                                <td class="action"><a href="add_category.php?cat_id=<?php echo $row['cid'];?>" class="edit">Edit</a></td>
                                <td class="action"><a href="?cat_id=<?php echo $row['cid'];?>" class="delete" onclick="return confirm('Are you sure you want to delete this category and related Channel?');">Delete</a></td>
                            </tr>  
                               
						<?php
						
							$i++;
							}
						?>	 
							                    
                        </table>
					 
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
<?php include("includes/footer.php");?>       
