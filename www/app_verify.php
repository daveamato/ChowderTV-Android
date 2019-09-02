<?php include("includes/header.php");

	require("includes/function.php");
	$kwallpaper=new  k_wallpaper;
	include('purchase.php'); 
 	
	
	if(isset($_POST['submit']))
	{
	     
	     if(verify_purchase($_POST['buyer'],$_POST['purchase_code']) == false)
	 	{
			 
			$_SESSION['msg']="Sorry, we are unable to verify your purchase.";
			header( "Location:app_verify.php");
			exit;
		}
		else
		{
			 $data = array(
			'buyer'  =>  $_POST['buyer'],
			'purchase_code' => $_POST['purchase_code'],
			'status' => '1'
			);
		  
		    $app_edit=Update('app_verify', $data, "WHERE id = '1'");
			 
			if ($app_edit > 0){
				
				$_SESSION['msg']="Save successfully...";
				header( "Location:app_verify.php");
				exit;
			} 	
		}
	     
		
		 
	}
	//Get Data
	$app_qry="SELECT * FROM app_verify WHERE id='1'";
	$app_result=mysql_query($app_qry);
	$app_row=mysql_fetch_assoc($app_result);
	 
?>
	
	
<script src="js/category.js" type="text/javascript"></script>  
                 
                <div id="main">
                <h2><a href="home.php">Dashboard</a> &raquo; <a href="#" class="active"></a></h2>
                <?php if(isset($_SESSION['msg'])!= '')
                {
				echo '<p style="color:white">'.$_SESSION['msg'].'</p>';
				$_SESSION['msg'] = '';	
				}
				?>
                	<form action="" name="addeditcategory" method="post" class="jNice" onsubmit="return checkValidation(this);" enctype="multipart/form-data">
					 
					                    	 
					<h3>App Verify</h3>
                    	<fieldset>
						 
<label>Buyer:</label>
								
       
								
							 <input type="text" name="buyer" id="buyer" value="<?php echo $app_row['buyer'];?>" >
                            <label style="padding-top:20px;">Purchase Code</label>
                         <input type="text" name="purchase_code" id="purchase_code" value="<?php echo $app_row['purchase_code'];?>" >  
                         <p></p>
           <input type="submit" name="submit" value="Save" />
                        
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
