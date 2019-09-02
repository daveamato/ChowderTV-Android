<?php include("includes/header.php");

	require("includes/function.php");
	$kwallpaper=new  k_wallpaper;
	
	 

	
	// Get page data

   $qry="SELECT tbl_review.*,tbl_channels.channel_title FROM tbl_review
LEFT JOIN tbl_channels ON tbl_review.channel_id= tbl_channels.id ORDER BY tbl_review.id DESC ";	  

	$result=mysql_query($qry);

 
	
	if(isset($_GET['channel_id']))
	{
		$kwallpaper->deletereview();
		
		 
		echo "<script>document.location='manage_review.php';</script>"; 
	    exit;
		
	}	
	 
?>
                
                <!-- h2 stays for breadcrumbs -->                
                <div id="main">
				<h2> Manage channels  &raquo;</h2>
                
                 <form  method="post" action="<?php echo $_SERVER["PHP_SELF"];?>">
                    <div class="search">
                        <input  type="text" name="search_value" placeholder="Find" class="search_text"> 
                        <button type="submit" value="submit" name="search" class="search_button">Search</button>
                    </div>       
                </form>         
                	<form action="" class="jNice">
					 
					<h3 class="abtn"><a href="add_channel.php?add=yes">Add Channel</a></h3>
                    	<table cellpadding="0" cellspacing="0">
						
						<tr align="center">
											 
						<th>Channel Name</th>
						<th>Review</th>	
						<th>Delete</th>
						
						</tr>
						
						
						<?php
						
							$i=0;
							while($row=mysql_fetch_array($result))
							{
							 
						?>							
							                   
							<tr <?php if($i%2==0){?>class="odd"<?php }?> align="center">
								
                                <td><?php echo $row['channel_title'];?></td>						 <td><?php echo $row['review'];?></td>					 
                               	<td class="action"><a href="?channel_id=<?php echo $row['id'];?>" class="delete" onclick="return confirm('Are you sure you want to delete this review?');">Delete</a></td>
                            </tr>                        
						
						<?php
						
							$i++;
							}
						?>	 
							                    
                        </table><br />
						<?php
								// Initial page num setup
	if ($page == 0){$page = 1;}
	$prev = $page - 1;	
	$next = $page + 1;							
	$lastpage = ceil($total_pages/$limit);		
	$LastPagem1 = $lastpage - 1;					
	
	
	$paginate = '';
	if($lastpage > 1)
	{	
	

	
	
		$paginate .= "<div class='paginate'>";
		// Previous
		if ($page > 1){
			$paginate.= "<a href='$targetpage?page=$prev'>previous</a>";
		}else{
			$paginate.= "<span class='disabled'>previous</span>";	}
			

		
		// Pages	
		if ($lastpage < 7 + ($stages * 2))	// Not enough pages to breaking it up
		{	
			for ($counter = 1; $counter <= $lastpage; $counter++)
			{
				if ($counter == $page){
					$paginate.= "<span class='current'>$counter</span>";
				}else{
					$paginate.= "<a href='$targetpage?page=$counter'>$counter</a>";}					
			}
		}
		elseif($lastpage > 5 + ($stages * 2))	// Enough pages to hide a few?
		{
			// Beginning only hide later pages
			if($page < 1 + ($stages * 2))		
			{
				for ($counter = 1; $counter < 4 + ($stages * 2); $counter++)
				{
					if ($counter == $page){
						$paginate.= "<span class='current'>$counter</span>";
					}else{
						$paginate.= "<a href='$targetpage?page=$counter'>$counter</a>";}					
				}
				$paginate.= "...";
				$paginate.= "<a href='$targetpage?page=$LastPagem1'>$LastPagem1</a>";
				$paginate.= "<a href='$targetpage?page=$lastpage'>$lastpage</a>";		
			}
			// Middle hide some front and some back
			elseif($lastpage - ($stages * 2) > $page && $page > ($stages * 2))
			{
				$paginate.= "<a href='$targetpage?page=1'>1</a>";
				$paginate.= "<a href='$targetpage?page=2'>2</a>";
				$paginate.= "...";
				for ($counter = $page - $stages; $counter <= $page + $stages; $counter++)
				{
					if ($counter == $page){
						$paginate.= "<span class='current'>$counter</span>";
					}else{
						$paginate.= "<a href='$targetpage?page=$counter'>$counter</a>";}					
				}
				$paginate.= "...";
				$paginate.= "<a href='$targetpage?page=$LastPagem1'>$LastPagem1</a>";
				$paginate.= "<a href='$targetpage?page=$lastpage'>$lastpage</a>";		
			}
			// End only hide early pages
			else
			{
				$paginate.= "<a href='$targetpage?page=1'>1</a>";
				$paginate.= "<a href='$targetpage?page=2'>2</a>";
				$paginate.= "...";
				for ($counter = $lastpage - (2 + ($stages * 2)); $counter <= $lastpage; $counter++)
				{
					if ($counter == $page){
						$paginate.= "<span class='current'>$counter</span>";
					}else{
						$paginate.= "<a href='$targetpage?page=$counter'>$counter</a>";}					
				}
			}
		}
					
				// Next
		if ($page < $counter - 1){ 
			$paginate.= "<a href='$targetpage?page=$next'>next</a>";
		}else{
			$paginate.= "<span class='disabled'>next</span>";
			}
			
		$paginate.= "</div>";		
	
	
}
  
 // pagination
 echo $paginate;
								?>		 
 <br />
					 
                </div>
                <!-- // #main -->
                
                <div class="clear"></div>
            </div>
            <!-- // #container -->
        </div>	
        <!-- // #containerHolder -->
        
<?php include("includes/footer.php");?>       
