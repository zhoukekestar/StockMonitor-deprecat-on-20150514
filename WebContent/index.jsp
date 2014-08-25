<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="model" class="com.stock.jsp.model.RatioModel" />
<jsp:setProperty property="request" name="model" value="<%=request%>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.slide {
	height: 20px;
	background-color: #eee;
	width: 100%;
}

.slide>span {
	height: 20px;
	display: block;
	padding-left: 50%;
}

.slide>span:after {
	content: "";
	content: "";
	display: block;
	height: 20px;
	background-color: #72D369;
	width: 100%;
	border-radius: 0 8px 8px 0;
}

.slide>div>span {
	width: 25%;
	height: 2px;
	display: block;
	border: none;
	margin: 0;
	float: left;
}

.fan:after {
	background-color: #F38F8F !important;
	border-radius: 8px 0 0 8px !important;
}

p {
	display: block;
	position: absolute;
	margin: 0;
}
</style>
</head>
<body>
	<table style="width: 100%;">
		<c:forEach var="item" items="${ratio_data }" varStatus="i">
			<tr>
				<td style="width: 200px;">${item.time }</td>
				<td>
					<p>${item.ratio}</p>
					<div class="slide">
						<c:if test="${item.ratio >= 0 }">
							<span style="width:${item.ratio/360*100 }%"></span>
						</c:if>
						<c:if test="${item.ratio <= 0 }">
							<span class="fan" style="width:${-item.ratio/360*100 }%; margin-left:${item.ratio/360*100 }%;"></span>
						</c:if>
						<div>
							<span style="background: #B3B3B3"></span> <span style="background: #757575;"></span> <span style="background: #B3B3B3"></span> <span style="background: #757575;"></span>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>