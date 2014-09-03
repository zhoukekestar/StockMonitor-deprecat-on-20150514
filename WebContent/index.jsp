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
	border-radius: 8px 0 0 8px !important;
}

p {
	display: block;
	position: absolute;
	margin: 0;
}

.exp span {
	width: 25%;
	display: inline-block;
	border: none;
	margin: 0;
	padding: 0;
	text-align: center;
	float: left;
}
.l-1:after
{
background-color: rgba(243, 143, 143, 1);
}
.l-2:after
{
background-color: rgba(243, 143, 143, 0.5);
}
.l-3:after
{
background-color: rgba(114, 211, 105, 0.5);
}
.l-4:after
{
background-color: rgba(114, 211, 105, 1);
}
</style>
</head>
<body>
	<table style="width: 100%;">
		<tr>
			<td style="width: 200px"></td>
			<td class="exp">
			<span style="background-color: rgba(243, 143, 143, 1);">下跌主力流出</span> 
			<span style="background-color: rgba(243, 143, 143, 0.5);">上涨主力流出</span> 
			<span style="background-color: rgba(114, 211, 105, 0.5);">上涨主力流入</span> 
			<span style="background-color: rgba(114, 211, 105, 1);">下跌主力流入</span></td>
		</tr>
		<c:forEach var="item" items="${ratio_data }" varStatus="i">
			<tr>
				<td style="width: 200px;">${item.time }<span style="font-style: oblique; color: #f00;">(${item.trade })</span></td>
				<td>
					<p>${item.ratio}</p>
					<div class="slide">
						<c:if test="${item.ratio >= 0 }">
							<c:if test="${item.ratio <= 90 }">
								<span class="l-3" style="width:${item.ratio/360*100 }%;"></span>
							</c:if>
							<c:if test="${item.ratio > 90 }">
								<span class="l-4" style="width:${item.ratio/360*100 }%;"></span>
							</c:if>
						</c:if>
						<c:if test="${item.ratio < 0 }">
							<c:if test="${item.ratio >= -90 }">
								<span class="fan l-2" style="width:${-item.ratio/360*100 }%; margin-left:${item.ratio/360*100 }%;"></span>
							</c:if>
							<c:if test="${item.ratio < -90 }">
								<span class="fan l-1" style="width:${-item.ratio/360*100 }%; margin-left:${item.ratio/360*100 }%;"></span>
							</c:if>
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