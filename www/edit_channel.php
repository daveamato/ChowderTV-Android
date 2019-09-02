<?php include("includes/header.php");

	require("includes/function.php");
	$kwallpaper=new  k_wallpaper;
	
	
	//Get all Category 
	$qry="SELECT * FROM tbl_category";
	$result=mysql_query($qry);
	
	if(isset($_GET['channel_id']))
	{
		$img_qry="SELECT * FROM  tbl_channels WHERE id='".$_GET['channel_id']."'";
		$img_res=mysql_query($img_qry);
		$img_row=mysql_fetch_assoc($img_res);
		 
		 
	}
	 	
	if(isset($_POST['submit']) and isset($_GET['channel_id']))
	{
	 
		$kwallpaper->editchannel();
		
		 
		echo "<script>document.location='manage_channels.php';</script>"; 
	    exit;
		
	}	
	 
?>
<script src="js/gallery.js" type="text/javascript"></script>
  
                <!-- h2 stays for breadcrumbs -->                                
                <div id="main">                	
					 <h2><a href="home.php">Dashboard</a> &raquo; <a href="#" class="active"></a></h2>
                     <h3>Edit Channel</h3>                   	
					
					<form action="" method="post"  enctype="multipart/form-data" onsubmit="">
						<fieldset>
						
							<p>
								<label>Select Category:</label>
								
								 
									<select name="category_id" id="category_id">
			
										<option value="0">--Select Category--</option>
										<?php
												while($row=mysql_fetch_array($result))
												{
										 			if(isset($_POST['category']))
													{
															if($_POST['category']==$row['cid']){
															?>
															<option value="<?php echo $row['cid'];?>"  selected="selected"><?php echo $row['category_name'];?></option>								<?php }else{?>
										<option value="<?php echo $row['cid'];?>"><?php echo $row['category_name'];?></option>								
										<?php 				}?>
																
												<?php }
													else
													{
														 if($img_row['cat_id']==$row['cid']){
											 
											 ?>
										<option value="<?php echo $row['cid'];?>"  selected="selected"><?php echo $row['category_name'];?> </option>								<?php }else{?>
										<option value="<?php echo $row['cid'];?>"><?php echo $row['category_name'];?></option>								
										<?php 				}
														}
												}
										?>
									</select>
									  </p>
							  
							<p><label>Channel Title:</label>
								<input type="text" name="channel_title" id="channel_title" value="<?php echo $img_row['channel_title'];?>" class="text-long" placeholder="Test channel" />
							</p>
                             <p><label>Channel Url:</label>
								<input type="text" name="channel_url" id="channel_url" value="<?php echo $img_row['channel_url'];?>" class="text-long" 
                                placeholder="http://example.com?p=12" />
							</p> 
                            

                             <p id="thumbnail">
                              <?php if($img_row['channel_thumbnail']) {?>
                              <img src="images/thumbs/<?php echo $img_row['channel_thumbnail'];?>" />
                              <?php } ?>
                             <label>Select Channel Thumbnail</label>
                             <input type="file" name="thumbnail" id="thumb" class="text-long"/>
                             </p>
                          	 <p><label>Channel Description:</label>
                            	<textarea name="channel_description" id="channel_description" class="text-long"><?php echo $img_row['channel_desc'];?></textarea>
                            </p>
							 
                            <input type="submit" name="submit" value="Edit channel" />
                        </fieldset>
                    </form>
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
<?php include("includes/footer.php");?>       
