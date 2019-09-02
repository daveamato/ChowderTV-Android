<?php include("includes/header.php");

	require("includes/function.php");
	$kwallpaper=new  k_wallpaper;
	
	
	//Get all Category 
	$qry="SELECT * FROM tbl_category";
	$result=mysql_query($qry);
	
	 	
	if(isset($_POST['submit']) and isset($_GET['add']))
	{	
		 
		$kwallpaper->addchannel();
		
		echo "<script>document.location='manage_channels.php';</script>"; 
	    exit;
		
	}	
	 
?>
<script src="js/add_channel.js" type="text/javascript"></script>
  

                  
                <!-- h2 stays for breadcrumbs -->                                
                <div id="main">                	
					 <h2><a href="home.php">Dashboard</a> &raquo; <a href="#" class="active"></a></h2>
                     <h3>Add Channel</h3>                   	
					
					<form action="" method="post"  enctype="multipart/form-data" onsubmit="return checkValidation(this);">
						<fieldset>
						
							<p>
								<label>Select Category:</label>
								
								 
									<select name="category_id" id="category_id" style="width:280px; height:25px;">
			
										<option value="">--Select Category--</option>
										<?php
												while($row=mysql_fetch_array($result))
												{
										?>
										<?php if($_POST['category']==$row['cid']){?>
										<option value="<?php echo $row['cid'];?>"  selected="selected"><?php echo $row['category_name'];?> </option>								<?php }else{?>
										<option value="<?php echo $row['cid'];?>"><?php echo $row['category_name'];?></option>								
										<?php }?>
										<?php
											}
										?>
									</select>
									  </p>
							  
							<p><label>Channel Title:</label>
								<input type="text" name="channel_title" id="channel_title" value="" class="text-long"
                                 placeholder="Test channel" />
							</p>
                                                         
                             <p><label>Channel Url:</label>
								<input type="text" name="channel_url" id="channel_url" value="" class="text-long" 
                                placeholder="http://example.com?p=12" />
							</p> 
                            
                             <p id="thumbnail">
                             <label>Select Channel Thumbnail</label>
                             <input type="file" name="thumbnail" id="thumb" class="text-long"/>
                             </p>
                            
                            <p><label>Channel Description:</label>
                            	<textarea name="channel_description" id="channel_description"></textarea>
                            </p>
							 
                            <input type="submit" name="submit" value="Add Channel" />
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
