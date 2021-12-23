<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/includes/layout.jsp" %>
    <title>Dashboard</title>
</head>
<body>
	<%@ include file="/admin/includes/header.jsp" %>  


    <div class="flex-col dashboard-wrapper">
        <div class="header-padding"></div>
        <div class="dashboard-body">
            
            <%@ include file="/admin/includes/nav-panel.jsp" %>
            <div class="component-loader">
                <div class="component">
                    <h2 class="component-header">Dashboard</h2>
                    <div>
						<%@ include file="/includes/flash-message.jsp" %>  
                    	
                    </div>
	                
                </div>
                <%@ include file="/admin/includes/footer.jsp" %>  
            </div>
        </div>
    </div>
</body>
</html>