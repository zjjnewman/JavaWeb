<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--			大于 1 才显示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--	当前页码的前后页码 结束--%>

    <%--	小于末页才显示	--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            // 跳到指定页码
            $("#searchPageBtn").click(function () {
                let pageNo = $("#pn_input").val();

                // location.href = "${requestScope.page.url}&pageNo="+pageNo;

                if(pageNo >= 1 && pageNo <= ${requestScope.page.pageTotal}){
                    // 如何跳转？，js提供了地址栏对象 location，可以取出地址栏中的地址
                    <%--location.href = "${requestScope.host}"--%>
                    location.href = "${requestScope.page.url}&pageNo="+pageNo;
                } else {
                    alert("跳转页码【" + pageNo + "】非法")
                    <%--return confirm("跳转页码【" + pageNo + "】非法，共【${requestScope.page.pageTotal}】页！！！");--%>
                }

            })
        })
    </script>

</div>
<%--分页条的结束--%>


