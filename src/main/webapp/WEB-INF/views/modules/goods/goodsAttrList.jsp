<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品属性管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/goods/goodsAttr/">商品属性列表</a></li>
		<shiro:hasPermission name="goods:goodsAttr:edit"><li><a href="${ctx}/goods/goodsAttr/form">商品属性添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="goodsAttr" action="${ctx}/goods/goodsAttr/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>属性名称：</label>
				<form:input path="attrName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>属性名称</th>
				<th>排序</th>
				<th>备注</th>
				<shiro:hasPermission name="goods:goodsAttr:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goodsAttr">
			<tr>
				<td><a href="${ctx}/goods/goodsAttr/form?id=${goodsAttr.id}">
					${goodsAttr.attrName}
				</a></td>
				<td>
					${goodsAttr.sort}
				</td>
				<td>
					${goodsAttr.remarks}
				</td>
				<shiro:hasPermission name="goods:goodsAttr:edit"><td>
    				<a href="${ctx}/goods/goodsAttr/form?id=${goodsAttr.id}">修改</a>
					<a href="${ctx}/goods/goodsAttr/delete?id=${goodsAttr.id}" onclick="return confirmx('确认要删除该商品属性吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>