function edit(){
	 if(confirm("수정???")){
		form1.action="/admin/category/update";
		form1.method="post";
		form1.submit();
 	}
 }


function del(){
	 if(confirm("삭제???")){
		location.href="/admin/category/delete?category_id=<%=category.getCategory_id()%>";
 	}
 } 