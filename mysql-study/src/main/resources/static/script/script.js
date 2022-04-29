var today = new Date();



$(document).ready(function(){
	
	   $('#Progress_Loading').hide(); //첫 시작시 로딩바를 숨겨준다.
	})
	.ajaxStart(function(){
		$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	})
	.ajaxStop(function(){
		$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
	});
$(document).ready(function() {
	currentMonthChange();

	
	
	for (var i = 2015; i <= lastYear; i++) {
		if (i == lastYear) {
			$('#yearSelect').append('<option selected>' + i + '</option>');
		} else {
			$('#yearSelect').append('<option>' + i + '</option>');
		}
	}
		$('#yearSelect').change(function() {		
		year = $('#yearSelect option:selected').val();
		console.log(year);
		
		// 선택 연도가 올해보다 작으면 12로 하고, 똑같으면 존재하는 달까지만 나오게
		if (year < lastYear) {
			month = 12;
			currentMonthChange();
		} else if (year === lastYear) {
			var month2 = new Date();
			month2 = month2.toISOString().substring(6, 7);
			month = month2;
			currentMonthChange();
		}
	});
	

$("#callOrder").click(function(){
	
	var year = $("#yearSelect").val();
	var month = $("#monthSelect").val();
	console.log(year + " " + month+" ");
	
		$.ajax({
			url : "/two/home2",
			"method" : "get",
			"data" : {
				"year": year, //year값 = YYYY-MM-01
				"month": month, //month값 = YYYY-MM-31
			} ,
			success : function(data) {
				var resultListHTML = ""; 
				var resultList = data.monthList;
				//             = JSON.parse(data(컨트롤러에서 넘겨주는애));
				//모델에 담지 않고 맵을 리스폰스바디로 넘겨줘서 json객체로 변환된다 그걸 뽑아주면 됨 
				var priceSum = data.priceSum;
				console.log(priceSum);
				var discountSum = data.discountSum;
				var lastpaySum = data.lastpaySum;
				
				
				resultListHTML += "<table class='type01'>"
				resultListHTML += "	<tr>"
				resultListHTML += "		<th>제휴사</th>"
				resultListHTML += "		<th>상품금액</th>"
				resultListHTML += "		<th>할인금액</th>"
				resultListHTML += "		<th>결제금액</th>"
				resultListHTML += "	</tr>"
				
				if(resultList.length>=1){
				
				for(var i = 0; i < resultList.length; i++) {
					resultListHTML += "<tr id='tr_" + i + "'>";
					
					resultListHTML += "<td id='td_companynm" + i + "' >";
					resultListHTML += "<a href='javascript:popup("+i+")'>"+resultList[i].companynm+"</a>"
					resultListHTML += "</td>";
					
					resultListHTML += "<td id='td_price_" + i + "'>";
					var price = resultList[i].price;
					price = price.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
					resultListHTML += price;
					resultListHTML += "원</td>";
					
					resultListHTML += "<td id='td_discount_" + i + "'>";
					var discount = resultList[i].discount;
					discount = discount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
					resultListHTML += discount;
					resultListHTML += "원</td>";
					
					resultListHTML += "<td id='td_lastpay_" + i + "'>";
					var lastpay = resultList[i].lastpay;
					lastpay = lastpay.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
					resultListHTML += lastpay;
					resultListHTML += "원</td>";
					
					resultListHTML += "</tr>";

				}
				resultListHTML += "<tr id='sum' style='background: #eee;'>"
				resultListHTML += "<td>합계</td>"
				
				resultListHTML += "<td>"
				var pricesum = priceSum;
				pricesum = pricesum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
				resultListHTML += pricesum;
				resultListHTML += "원</td>"
				
				resultListHTML += "<td>"
				var discountsum = discountSum;
				discountsum = discountsum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
				resultListHTML += discountsum;
				resultListHTML += "원</td>"
				
				resultListHTML += "<td>"
				var lastpaysum = lastpaySum;
				lastpaysum = lastpaysum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
				resultListHTML += lastpaysum;
				resultListHTML += "원</td>"
				
				resultListHTML += "</tr>"
				resultListHTML += "</table>"
					
				}else{
					resultListHTML += "<tr>"
					resultListHTML += "	<td colspan='4'>통계 데이터가 없습니다.</td>"
					resultListHTML += "</tr>"
				}
				 
				$("#dataListDiv").html(resultListHTML);
				
				$("#dataListDiv").css("display", "block");
				
			}, error : function(data){
				alert("실패");
			}
		});
	});
});
function currentMonthChange(){
		$("#monthSelect").empty();
		
		for(var i = 1; i<= month; i++){
			if (i == month) {
				$('#monthSelect').append('<option selected>'+i+'</option>');
			} else {
				$('#monthSelect').append('<option>'+i+'</option>');
			}
		}
	}
function popup(i){

	//console.log(companynm)
	var year = $('#yearSelect').val();
	var month = $('#monthSelect').val();
	var companynm = $('#td_companynm'+i).text();
	//encodeURIComponent($('#txtupdserviceurl')
    var url = "/three/home3?year="+year+"&month="+month+"&companynm="+companynm;
    console.log(year+month+companynm);
    var name = "popup test";
    var option = "width = 800, height = 800, top = 100, left = 200, location = no"
    
    window.open(url, name, option);
}

 