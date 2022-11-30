$(document).ready(function(){
	//alert("成功連接emp.js");
	$("#getAllEmp_btn").click(function(){
		$.ajax({
		  url:"/getAllEmployeesRest",
		  //restController+ajax的操作，不會重新導向葉面，所以不可直接在原頁面寫thymeleaf，而是要用js動態操作DOM
		  type:"GET",		
		  success:function(result){		  		
				//$("#getAllEmpResult").text(result);
				$("#getAllEmpResult").empty();
				$("#getAllEmpResult").append("<tr><th>員工ID</th><th>員工姓名</th><th>職稱</th><th>薪資</th><th>到職日</th></tr>");
				$(result).each(function(){
					
					var emp_id=this.id;
					var emp_name=this.name;
					var emp_position=this.position;
					var emp_pay = this.pay;
					var emp_onboard_date = this.on_board_date;
					var formattedDate = new Date(emp_onboard_date);
					var d = formattedDate.getDate();
					var m = formattedDate.getMonth();
					m+=1;
					var y = formattedDate.getFullYear();		
					//var emp_onboard_date = new Date(this.on_board_date).Format('yyyy-MM-dd');
					$("#getAllEmpResult").append("<tr><td>"+emp_id+"</td><td>"+emp_name+"</td><td>"+emp_position+"</td><td>"+emp_pay+"</td><td>"+y+"."+m+"."+d+"</td></tr>");
					//按順序append tr tr會有問題...(https://stackoverflow.com/questions/171027/adding-a-table-row-in-jquery)
					
				})
			},		  
		});
	})
	
	$(".alter_btn").click(function(){		
		var emp_id = this.id.split('_')[0];
		
		$.ajax({
			url:"/toAlterEmployee",
			type:"GET",
			data:{ //為啥又不用json了?因為get?
				emp_id:emp_id,             
          	},
          	success:function(result){
	
			},
		});
		
	})
	

})