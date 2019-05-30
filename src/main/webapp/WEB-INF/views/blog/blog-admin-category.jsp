<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blog.title }</h1>
			<c:import url="/WEB-INF/views/includes/blog-admin-navigation.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-admin-menu.jsp"/>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<tr id="categoryList">
						<td id="no"></td>
						<td id="title"></td>
						<td id="count"></td>
						<td id="description"></td>
						<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr> 
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="cat-add-form">
      				<input type="hidden" name="blogId" value="${authUser.id }">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="title"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="button" id="cat-add-button" value="카테고리 추가" ></td>
			      		</tr>      		      		
			      	</table>
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"/>
	</div>
	
	<script>
		
		
		$(function(){
			$('#cat-add-button').click(function(){
				
			});
			
			
		})
		
		function getList() {
			$.ajax({
				url: "${pageContext.request.contextPath}/${authUser.id}/admin/category", 
				type: "POST",
				dataType: "json",
				data: $('#cat-add-form').serialize(),
				success : function(res) {
					res.data.forEach((ele)=>{
						console.log(ele)
					});
					
					
				},
				error : function(xhr, error) {
					console.error("error : " + error);
				}
			});
		}
		
		
		function insertCategory() {
			
		}
		
		function deleteCategory() {
			
		}
	
	</script>
</body>
</html>