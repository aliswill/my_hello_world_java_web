/**
 * 
 */
 $(document).ready(function(){
	
		
		$.ajax({
			url:"/getMessages",
			type:"GET",			
			success:function(allMessage){		//記得案時間排序	
				var loginUser = sessionStorage.getItem("loginUser");	
				//alert(loginUser);
				$(allMessage).each(function(){
					var message = this;
					var nick_name = this.nick_name;
					var message = this.message;
					var message_time = this.message_time;
					//可用tyoeof查看類型 應該都是字串
					//var time = new Date(message_time);
					//message_time = time.format('yyyy-MM-dd hh:mm:ss');
					
					var message_id = this.message_id;
					var user_account = this.user_account;
					
					//提交後會是提交鍵值對 name=value 所以name不能用成檔案名，所以複選框的提交結果會是 name=value1&value2
					//不會允許不成對標籤，所以無法案預想的順序，若要先鍵一個標籤再以插入父類的方法，因為標籤都是ajax生成，似乎不可行?用live可以嗎?
					//ajax動態生成的thymeleaf與法似乎不會被解析 th:if="${session.loginUser}== '+message_id+'"
					
					if(loginUser==user_account){
						$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' ">刪除留言</a></div>');
					}else{
						$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div></div>');
					}
					//$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' ">刪除留言</a></div>');
					$('#'+message_id).wrap('<div class="StraightMylayoutSon"></div>');
					//$("#message_board").append('<p class="a">哈哈</p>');
					//$(".a").css("background-color","red"); 居然可以操作?
					
				})
				
					//$("#files_form").append('<input type="submit" id="butten" value="刪除">');
			}			
		});
		
})

