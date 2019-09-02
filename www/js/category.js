function checkValidation()
{
	if(document.getElementById('category_name').value=="")
	{
		alert("Please Enter Category.!");
		 
		document.getElementById('category_name').focus();
		document.getElementById('category_name').select();
		return false;
	}
	if(document.getElementById('image').value=="")
	{
		alert("Please select category image!");
		 
		document.getElementById('image').focus();
		document.getElementById('image').select();
		return false;
	}
	
	return true;
} 