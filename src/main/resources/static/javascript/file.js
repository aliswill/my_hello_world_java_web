/**
 * 
 */
 
 $(document).ready(function(){
	$.ajax({
			url:"/getUserFiles",
			type:"GET",			
			success:function(files){				
				$(files).each(function(){
					var file = this;
					
					//$("#test").append("<p>"+file+"</p>");
					//提交後會是提交鍵值對 name=value 所以name不能用成檔案名，所以複選框的提交結果會是 name=value1&value2
					$("#files_form_ckeckbox").append('<input type="checkbox" name="del_files"' +'value="'+file+'"'+'id="'+file+'"/>');
					$("#files_form_ckeckbox").append('<label for="'+file+'">'+file+'</label></br>');
				})
				
					//$("#files_form").append('<input type="submit" id="butten" value="刪除">');
			}			
		});
		
	$("#butten").click(function(){
		//***如果按鈕也是由ajax新增的，似乎無法操作?
		//$(this).parent("form")["del_files"].value==""
		
		var check=$("input[name='del_files']:checked").length;//判斷有多少個方框被勾選
			if(check==0){
				alert("您尚未勾選任何項目");
				return false;//不要提交表單
			}else{
				var del_yn = confirm("確定刪除檔案?");
				if(del_yn){
					return true;
				}else{
					return false;
				}
			}

	});
	

/*	$("#download_txt").click(function(){
		$.ajax({
			url:"/downloadTxt",
			type:"POST",
			data:{filename:"file1"},
			success:function(result){
				alert("ajax成功");
			}
			
		});
	})*/
})