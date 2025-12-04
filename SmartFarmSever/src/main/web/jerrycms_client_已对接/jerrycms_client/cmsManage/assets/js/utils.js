function toggleLeft()
{
	if($("#navleft").attr("style")=="left:0px;")
	{
		$("#navleft").attr("style","left:-260px;");
		$("#page-wrapper").attr("style","margin-left:0px;");
	}else if($("#navleft").attr("style")=="left:-260px;")
	{
		$("#navleft").attr("style","left:0px;");
		$("#page-wrapper").attr("style","margin-left:260px;");
	}
}