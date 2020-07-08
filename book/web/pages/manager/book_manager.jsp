<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%-- 静态保函base标签页、css样式、jQuery文件，公共头，jsp静态包含--%>
	<%@include file="/pages/common/head.jsp"%>

<%--	以下是删除确实提示框--%>
	<script type="text/javascript">

		$(function () {
			$("a.deleteClass").click(function () {
				/**
				 * ------对于删除对象的查找说明，要查找到删除的书名
				 * 在事件的function函数中，有个this对象，这个this，是当前正在响应时间的dom对象，也就是deleteClass所在的 a标签
				 * 可以通过这个a标签找到其父元素<td>标签，然后再找<td>的父元素<tr>标签，然后在找到第一条<td>就是书名
				 *
				 * ------删除流程
				 * confirm是确认提示框函数
				 * 参数是他的提示内容
				 * 有两个按钮，确认，取消
				 * 返回true表示点击确认，false点击了取消
				 * true 表示继续当前的行为，false表示取消，啥也不干，所以直接返回函数
				 */
				return confirm("你确定要删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");

			})
		})

	</script>


</head>
<body>
	
	<div id="header">
		<img class="logo_img" alt="" src="../../static/img/logo.gif" >
		<span class="wel_word">图书管理系统</span>
		<%--	订单管理系统公共部分 manager模块菜单，静态包含	--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>

		<div id="page_nav">
<%--			大于 1 才显示--%>
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
			</c:if>

			<%--	当前页码的前后页码 开始--%>
			<%--	情况1--%>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
				</c:when>
<%--				情况2 总页码大于5--%>
				<c:when test="${requestScope.page.pageTotal > 5 }">
					<c:choose>
						<%--					小情况1--%>
						<c:when test="${requestScope.page.pageNo <= 3 }">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<%--					小情况2--%>
						<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3 }">
							<c:set var="begin" value="${requestScope.page.pageTotal - 4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
							<c:set var="end" value="${requestScope.page.pageNo + 2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>

			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i == requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<c:if test="${i != requestScope.page.pageNo}">
					<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<%--	当前页码的前后页码 结束--%>

	<%--	小于末页才显示	--%>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script type="text/javascript">
				$(function () {
					// 跳到指定页码
					$("#searchPageBtn").click(function () {
						let pageNo = $("#pn_input").val();

						// location.href = "manager/bookServlet?action=page&pageNo="+pageNo;

						if(pageNo >= 1 && pageNo <= ${requestScope.page.pageTotal}){
							// 如何跳转？，js提供了地址栏对象 location，可以取出地址栏中的地址
							<%--location.href = "${requestScope.host}"--%>
							location.href = "manager/bookServlet?action=page&pageNo="+pageNo;
						} else {
							alert("跳转页码【" + pageNo + "】非法")
							<%--return confirm("跳转页码【" + pageNo + "】非法，共【${requestScope.page.pageTotal}】页！！！");--%>
						}

					})
				})
			</script>

		</div>

	</div>

	<%-- 页脚静态包含 --%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>