<?php include("includes/header.php");?>
                
                <!-- h2 stays for breadcrumbs -->
                
                <div id="main">
                	 <h2> Dashboard</h2>
                
					<h3 align="left">Total Categories</h3>
                   <?php 
						
						$qry_cat="SELECT COUNT(*) as num FROM tbl_category";
						$total_category= mysql_fetch_array(mysql_query($qry_cat));
						$total_category = $total_category['num'];
					
					?>	
                    	<p style="margin-left:50px;"><a href="manage_category.php" style="color:#009900;text-decoration:none; font-size:16px;"><?php echo $total_category;?></a></p>
                    
                    	 
					<h3 align="right" style="margin-top:-40px">Total Channel</h3>
                     <?php 
						
						$qry_gallery="SELECT COUNT(*) as num FROM tbl_channels";
						$total_images = mysql_fetch_array(mysql_query($qry_gallery));
						$total_images = $total_images['num'];
					
					?>	 
                     <p align="right" style="margin-right:45px; margin-bottom:50px;"><a href="manage_channels.php" style="color:#009900;text-decoration:none; font-size:16px;"><?php echo $total_images;?></a></p>
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
<?php include("includes/footer.php");?>       
