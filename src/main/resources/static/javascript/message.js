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
					
					var nick_name = this.nick_name;
					var message = this.message;
					//message.replace('\r','<br/>').replace('\n','<br/>');
					var message_time = this.message_time;
					//可用tyoeof查看類型 應該都是字串
					//var time = new Date(message_time);
					//message_time = time.format('yyyy-MM-dd hh:mm:ss');
					
					var message_id = this.message_id;
					var user_account = this.user_account;
					var like_num = this.like_num;
					var like_yn = this.like_yn;
					var sub_message_num = this.sub_message_num;
					
					//alert("loginUser"+loginUser+",user_account"+user_account);
					//提交後會是提交鍵值對 name=value 所以name不能用成檔案名，所以複選框的提交結果會是 name=value1&value2
					//不會允許不成對標籤，所以無法案預想的順序，若要先鍵一個標籤再以插入父類的方法，因為標籤都是ajax生成，似乎不可行?用live可以嗎?
					//ajax動態生成的thymeleaf與法似乎不會被解析 th:if="${session.loginUser}== '+message_id+'"
					
					if(loginUser==user_account){
						if(loginUser==like_yn){
							
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' " style="color:#C78100;">刪除留言</a><br/><button id="like_act_'+message_id+'" class="like_act non_border_btn">收回讚</button><button id="reply_'+message_id+'" class="reply non_border_btn">回覆('+sub_message_num+')</button><span id="like_'+message_id+'">讚:'+like_num+'</span></div><div id="sub_'+message_id+'"></div>');
						}else{
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><a  href="/message/delete/'+message_id+' " style="color:#C78100;">刪除留言</a><br/><button id="like_act_'+message_id+'" class="like_act non_border_btn">按讚</button><button id="reply_'+message_id+'" class="reply non_border_btn">回覆('+sub_message_num+')</button><span id="like_'+message_id+'">讚:'+like_num+'</span></div><div id="sub_'+message_id+'"></div>');
						}						
					}else{
						if(loginUser==like_yn){
							
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><button id="like_act_'+message_id+'" class="like_act non_border_btn">收回讚</button><button id="reply_'+message_id+'" class="reply non_border_btn">回覆('+sub_message_num+')</button><span id="like_'+message_id+'">讚:'+like_num+'</span></div><div id="sub_'+message_id+'"></div>');	
						}else
						{
							$("#message_board").append('<div id="'+message_id+'"><div>暱稱 '+nick_name+'</div><div>發表於　'+message_time+'</div><div>說到：'+message+'</div><button id="like_act_'+message_id+'" class="like_act non_border_btn">按讚</button>	<button id="reply_'+message_id+'" class="reply non_border_btn">回覆('+sub_message_num+')</button><span id="like_'+message_id+'">讚:'+like_num+'</span></div><div id="sub_'+message_id+'"></div>');	
							}													
					}
										
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
				
				$("#like_"+message_id).text('讚:'+like_num);
				
				if(already_like_yn=='true'){					
					$('#like_act_'+message_id).text("收回讚");
				}else{
					$('#like_act_'+message_id).text("按讚");
				}

			},
			  //dataType: dataType //可省略不寫
			});
		})
		

		
		$("body").on('click','.reply',function(e){
			
			var message_id = $(this).parent().attr("id");
			var url = '/message/getreply/'+message_id;
			if($("#sub_"+message_id).children().length!=0){
				
				$("#sub_"+message_id).empty();
			}else{	
					
				$.ajax({
				  type: 'GET',
				  url: url,
				  //data: data,
				  success: function(allMessage){
					
					$("#sub_"+message_id).empty();
					
						$(allMessage).each(function(){
							var submessage = this;
							var nick_name = this.nick_name;
							var message = this.message;
							var message_time = this.message_time;
							var message_id = this.message_id;
							var sub_message_id = this.sub_message_id;
							var user_account = this.user_account;

							//這邊再調用this會抓不到
							$("#sub_"+message_id).append('<div id="'+message_id+'_'+sub_message_id+'">Floor '+sub_message_id+'<div>暱稱 '+nick_name+'</div><div>發表於 '+message_time+'</div><div>回覆到：'+message+'</div></div>');							
							$('#'+message_id+'_'+sub_message_id).wrap('<div class="StraightMylayoutSon orange"></div>');
												 
					});
						$("#sub_"+message_id).append('<form action="/addSubMessage/'+message_id+'" method="post"><div class="MylayoutSon">暱稱 <input type="text" name="nickname" required/></div><div class="MylayoutSon"><textarea  name="message" required></textarea></div><input class="MylayoutSon btn btn-secondary" type="submit" value="我要回覆"/>  </form>');
				}
								
			});
		} //else的右括弧
	
	})
})