/**
 * 
 */
 $(document).ready(function(){


		
		$.ajax({
			url:"/getMessages",
			type:"GET",			
			success:function(allMessage){		//記得案時間排序	
				var loginUser = sessionStorage.getItem("loginUser");	
				
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
					var like_num = this.like_num;
					var like_yn = this.like_yn;
					//alert("loginUser"+loginUser+",user_account"+user_account);
					//提交後會是提交鍵值對 name=value 所以name不能用成檔案名，所以複選框的提交結果會是 name=value1&value2
					//不會允許不成對標籤，所以無法案預想的順序，若要先鍵一個標籤再以插入父類的方法，因為標籤都是ajax生成，似乎不可行?用live可以嗎?
					//ajax動態生成的thymeleaf與法似乎不會被解析 th:if="${session.loginUser}== '+message_id+'"
					
					if(loginUser==user_account){
						if(loginUser==like_yn){
							
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' ">刪除留言</a><br/><button id="like_act_'+message_id+'" class="like_act">收回讚</button><br/><span id="like_'+message_id+'">讚:'+like_num+'</span></div>');
						}else{
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' ">刪除留言</a><br/><button id="like_act_'+message_id+'" class="like_act">按讚</button><br/><span id="like_'+message_id+'">讚:'+like_num+'</span></div>');
						}
						
					}else{
						if(loginUser==like_yn){
							
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><button id="like_act_'+message_id+'" class="like_act">收回讚</button><br/><span id="like_'+message_id+'">讚:'+like_num+'</span></div>');	
						}else
						{
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><button id="like_act_'+message_id+'" class="like_act">按讚</button><br/><span id="like_'+message_id+'">讚:'+like_num+'</span></div>');	
							}
													
					}
										
					//$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' ">刪除留言</a></div>');
					$('#'+message_id).wrap('<div class="StraightMylayoutSon"></div>');
					//$("#message_board").append('<p class="a">哈哈</p>');
					//$(".a").css("background-color","red"); 居然可以操作?
					
				})
				
			}			
		});
		
		//給ajax升成的dom 綁定事件
		$("body").on('click','.like_act',function(e){
			var message_id = $(this).parent().attr("id");
			
			var url = '/message/getlike/'+message_id;
			
			$.ajax({
			  type: 'GET',
			  url: url,
			  //data: data,
			  success: function(result){
				var like_num = result.split(',')[0];
				var already_like_yn =   result.split(',')[1];
				
				$("#like_"+message_id).text(like_num);
				
				if(already_like_yn=='true'){					
					$('#like_act_'+message_id).text("收回讚");
				}else{
					$('#like_act_'+message_id).text("按讚");
				}

			},
			  //dataType: dataType //可省略不寫
			});
		})
		

		
		

		
		
		
})

