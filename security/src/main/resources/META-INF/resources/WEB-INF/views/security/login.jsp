<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<div class="col-sm-12 col-md-4 col-md-offset-4 text-center">
		<form class="form-signin" action="${ctx }/security/do-login" method="post">
		    <h2 class="form-signin-heading">请登录</h2>
		    <label for="inputLoginName" class="sr-only">登录名</label>
		    <input id="inputLoginName" 
		    	class="form-control" 
		    	placeholder="登录名" 
		    	required="required" 
		    	autofocus="autofocus"
		    	name="loginName"/>
		    <label for="inputPassword" class="sr-only">密码</label>
		    <input type="password" 
		    	id="inputPassword" 
		    	class="form-control" 
		    	placeholder="登录密码" 
		    	required="required"
		    	name="password">
		    	
		    	<!-- 如果用到scrf，防跨站攻击的，所有post请求的表单都必须加上 -->
		    	<input type="hidden"
				name="${_csrf.parameterName}"
				value="${_csrf.token}"/>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

		</form>
	</div>
</body>
</html>