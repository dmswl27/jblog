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
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/blog/admin/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${vop.title }</h4>
					<p>
					 ${vop.contents }
					<p>
				</div>
				<ul class="blog-list">
		      		<c:forEach items="${listp }" var="post" >
					<li><a href="${pageContext.request.contextPath}/${id}/${post.category_no}/${post.no}">${post.title }</a> <span>${post.reg_date }</span>	</li>
					</c:forEach>
				</ul>
				
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${vob.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:set var="category" value="${fn:length(listc) }" />		      		
	      		<c:forEach items="${listc }" var="category">
	      			<li><a href="${pageContext.request.contextPath}/${authUser.id}/${category.no}">${category.name}</a>	</li>
	      		</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>