<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/includes/layout.jsp" %>  
    <title>Admin Login</title>
</head>
<body>

	<%@ include file="/includes/main-header.jsp" %>  

    <div class="wrapper flex-center">
        <div class="small-form-container">
            <form id="admin-login-form" class="form">
                <h2 class="form-heading">Admin Login</h2>
               	<%@ include file="/includes/flash-message.jsp" %>
                <div class="flex-row flex-1 mb-10">
                    <div class="form-block flex-col flex-1">
                        <div class="input-container">
                            <label >
                            <i class="fas fa-envelope mr-5"></i>
                            E-mail
                            </label>
                            <input type="email" name="email"/>
                        	<div class="form-response"></div>	
                        </div>
                    </div>
                </div>
                <div class="flex-row flex-1 mb-10">
                    <div class="form-block flex-col flex-1">
                        <div class="input-container">
                            <label >
                            <i class="fas fa-key mr-5"></i>
                            Password
                            </label>
                            <input type="password" name="password"/>
                        	<div class="form-response"></div>	
                        </div>
                    </div>
                </div>

	            <div class="flex-center">
	                <button class="btn btn-submit">Login</button>
	            </div>
	            <div class="form-footer">
	            	Create an account ? <a href="admin-register">Register Now</a>
	            	<br/>
	            	<br/>
	            	<a href="admin-forgot-password" class="fs-small">Forgot Password</a>
	            </div>
	            
            </form>
        </div>
    </div>
    <div id="overlay"></div>

	<%@ include file="/includes/main-footer.jsp" %>  

    <script src="public/js/admin/admin-login.js"></script>
</body>
</html>