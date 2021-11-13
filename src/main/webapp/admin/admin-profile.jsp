<%@page import="org.json.simple.JSONObject"%>
<%
	JSONObject json = (JSONObject) request.getAttribute("data");

	String success = (String) json.get("success");
	String error   = (String) json.get("error");
	JSONObject admin = (JSONObject) json.get("admin");
	String firstName = (String) admin.get("firstName");
	String lastName  = (String) admin.get("lastName");
	String email     = (String) admin.get("email");
	String contact   = (String) admin.get("contact");
	String gender    = (String) admin.get("gender");
	String institution = (String) admin.get("institution");
	String dateOfBirth = (String) admin.get("dob");
	
	String male = "", female = "", others = "";
	if(gender.equals("1"))
		male = "checked";
	else if(gender.equals("2"))
		female = "checked";
	else if(gender.equals("3"))
		others = "checked";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/includes/layout.jsp" %>
    <title>Profile</title>
</head>
<body>
	<%@ include file="/admin/includes/header.jsp" %>  


    <div class="flex-col dashboard-wrapper">
        <div class="header-padding"></div>
        <div class="dashboard-body">
            <%@ include file="/admin/includes/nav-panel.jsp" %>
            <div class="component-loader">
                <div class="component">
                    <h2 class="component-header">Profile</h2>
                    <div>
                    	<div class="form-container">
		                    <form id="create-quiz-form">
		                        <div class="form-block flex-row flex-1">
		                            <div class="input-container">
		                                <label >First Name</label>
		                                <input type="text" name="firstName" value="<%= firstName %>"/>
		                                <div class="form-response"></div>
		                            </div>
		                            <div class="input-container">
		                                <label >Last Name</label>
		                                <input type="text" name="lastName" value="<%= lastName %>"/>
		                                <div class="form-response"></div>
		                            </div>
		                        </div>
		                        <div class="form-block flex-row flex-1">
		                            <div class="input-container">
		                                <label >E-mail</label>
		                                <input type="text" name="email" value="<%= email %>" disabled/>
		                                <div class="form-response"></div>
		                            </div>
		                            <div class="input-container">
		                                <label >Contact</label>
		                                <input type="number" name="contact" value="<%= contact %>"/>
		                                <div class="form-response"></div>
		                            </div>
		                        </div>
		                        <div class="form-block flex-row flex-1">
		                            <div class="card small-msg">
		                                <p class="input-label mb-5">Gender</p>
		                                <div class="select-option-container">
		                                    <label class="">
		                                        <input type="radio" name="gender" value="1" <%= male %>>
		                                        <span>
		                                            Male
		                                        </span>
		                                    </label>
		                                    <label class="">
		                                        <input type="radio" name="gender" value="2" <%= female %>>
		                                        <span>
		                                            Female
		                                        </span>
		                                    </label>
		                                    <label class="">
		                                        <input type="radio" name="gender" value="3" <%= others %>>
		                                        <span>
		                                            Others
		                                        </span>
		                                    </label>
		                                </div>
		                                <div class="form-response"></div>
		                            </div>
		                        </div>
		                        <div class="form-block flex-row flex-1">
		                            <div class="input-container">
		                                <label >Institution</label>
		                                <input type="text" name="institution" value="<%= institution %>"/>
		                                <div class="form-response"></div>
		                            </div>
		                            <div class="input-container">
		                                <label >Date of Birth</label>
		                                <input type="date" name="dateOfBirth" value="<%= dateOfBirth %>"/>
		                                <div class="form-response"></div>
		                            </div>
		                        </div>
		                        <div class="flex-row space-between btn-container">
		                            <span id="reset-form-btn" class="btn btn-cancel">Reset</span>
		                            <button class="btn btn-submit">Submit</button>
		                        </div>
		                        <div class="form-footer">
		                            
		                        </div>
		                    </form>
		                </div>
                    </div>
	                
                </div>
                <%@ include file="/admin/includes/footer.jsp" %>  
            </div>
        </div>
    </div>
</body>
</html>