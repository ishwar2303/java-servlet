
<%
	String adminName = (String) request.getAttribute("adminFirstName");
%>
<div class="main-header">
	
    <div class="flex-row space-between">
        <div>
            <h2 class="logo">
                <a href="index" class="logo">QuizWit</a>
            </h2>
        </div>
        <div class="links">
            <label class="right">
                <%= adminName %>
                <div class="admin-dashboard-header-links">
                    <ul>
                        <li>
                            <label id="change-password">
                                <i class="fas fa-lock"></i>
                                Change Password
                            </label>
                        </li>
                        <li>
                            <a href="admin-profile">
                                <i class="fas fa-user-circle"></i>
                                Update Profile
                            </a>
                        </li>
                        <li>
                            <label id="setting">
                                <i class="fas fa-cogs"></i>
                                Settings
                            </label>
                        </li>
                        <li>
                            <a href="logout?page=admin-login">
                                <i class="fas fa-sign-out-alt"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </label>
        </div>
    </div>
</div>

<div id="overlay"></div>
<div id="change-password-dialog" class="theme-border">
   <div class="form-container">
		<form id="admin-change-password-form">
		    <h2 class="form-heading">Change Password</h2>
			<div class="form-block flex-row flex-1">
			    <div class="input-container">
			        <label >Old Password</label>
			        <input type="password" name="oldPassword"/>
			        <div class="form-response"></div>
			    </div>
			</div>
			<div class="form-block flex-row flex-1">
			    <div class="input-container">
			        <label >New Password</label>
			        <input type="password" name="newPassword"/>
			        <div class="form-response"></div>
			    </div>
			</div>
			<div class="form-block flex-row flex-1">
			    <div class="input-container">
			        <label >Confirm Password</label>
			        <input type="password" name="confirmPassword"/>
			        <div class="form-response"></div>
			    </div>
			</div>
			<div class="flex-row space-between btn-container">
			    <span id="reset-form-btn" class="btn btn-cancel">Reset</span>
			    <button id="submit-change-password" class="btn btn-submit">Submit</button>
			</div>
		</form>
	</div>
</div>
