/**
 * 
 */

 $(document).ready(function(){
			$.ajax({
			url:"/storeAccount",
			type:"GET",			
			success:function(user_account){					
				sessionStorage.setItem('loginUser',user_account);
											
			}			
		});		
	
	
		//不知道一個表單按鈕可否同時發送兩個請求(1.頁面跳轉 2.ajax)
	})