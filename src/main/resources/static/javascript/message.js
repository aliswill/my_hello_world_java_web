/**
 * 
 */
 $(document).ready(function(){
	
		$.ajax({
			url:"/getMessages",
			type:"GET",			
			success:function(allMessage){		//記得案時間排序		
				$(allMessage).each(function(){
					var message = this;
					var nick_name = this.nick_name;
					var message = this.message;
					var message_time = this.message_time;
					var message_id = this.message_id;

					//提交後會是提交鍵值對 name=value 所以name不能用成檔案名，所以複選框的提交結果會是 name=value1&value2
					//不會允許不成對標籤，所以無法案預想的順序，若要先鍵一個標籤再以插入父類的方法，因為標籤都是ajax生成，似乎不可行?用live可以嗎?
					$("#message_board").append('<div class="StraightMylayoutSon" id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div></div>');
//					$("#message_board").append('<div><p>'+nick_name+'</p></div>');
//					$("#message_board").append('<div><p>'+message_time+'</p></div>');
//					$("#message_board").append('<div><p>'+message+'</p></div>');
//					$("#message_board").append('</div>');
				})
				
					//$("#files_form").append('<input type="submit" id="butten" value="刪除">');
			}			
		});
		
})

