<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/style.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery-1.8.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/navigation.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/modernizr.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/selectivizr.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/jquery.scrollTo-1.4.1-min.js"></script>       
        <title>
            Đăng nhập
        </title>
    </head>
       
    <body onload='document.f.j_username.focus();'>
	<section class="container login"> 
			<c:if test="${success.equals('0')}">
            	<script>
            	  alert('Sai tên đăng nhập hoặc mật khẩu');
            	</script>
            </c:if>
			<h1 style="text-align:center; margin-bottom:5px;"><a href="login.html" class="brand">BABYSHOP ADMIN</a></h1>
			<div class="data-block">
				<form name ="f" action="<c:url value='j_spring_security_check'/>" method="POST">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="login">Tên đăng nhập</label>
							<div class="controls">
								<input id="inputUsername" type="text" placeholder="Tên đăng nhập" name="j_username" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">Mật khẩu</label>
							<div class="controls">
								<input id="inputPassword" type="password" placeholder="Mật khẩu" name="j_password" />
							</div>
						</div>
						<div class="form-actions">
							<button class="btn btn-alt btn-large btn-inverse" type="submit"><span style="vertical-align:middle;" class="icon-signin"></span> Đăng nhập</button>
						</div>
					</fieldset>
				</form>
			</div>		
	</section>

    </body>
</html>