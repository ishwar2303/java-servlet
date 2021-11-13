<%
	Boolean link = (Boolean) request.getAttribute("makeLink");
%>

<div class="main-header-padding"></div>
<div class="main-header">
    <div class="flex-row space-between">
        <div>
            <h2 class="logo">
                <a href="index" class="logo">QuizWit</a>
            </h2>
        </div>
        <div class="links">
            <label id="show-main-search">
                <i class="fas fa-search"></i>
            </label>
            <label class="left">Register
                <div class="admin-register-links">
                    <ul>
                        <li>
                            <a href="admin-register">Admin Registration</a>
                        </li>
                        <li>
                            <a href="student-register">Student Registration</a>
                        </li>
                    </ul>
                </div>
            </label>
            <label class="left">Login
                <div class="admin-login-links">
                    <ul>
                        <li>
                            <a href="admin-login">Admin Login</a>
                        </li>
                        <li>
                            <a href="student-login">Student Login</a>
                        </li>
                    </ul>
                </div>
            </label>
            <a href="">Blog</a>
            <%
            	if(link == null) {
            		%>
            <label id="move-to-about-us">About Us</label>
            <label id="move-to-contact-us">Contact Us</label>
            		<%
            		
            	}
            	else {
            		%>
            <a href="index?ref=move-to-about-us">About Us</a>
            <a href="index?ref=move-to-contact-us">Contact Us</a>
            		<%
            	}
            %>
        </div>
    </div>
</div>

<div id="overlay"></div>
<div id="main-search-dialog">
    <div class="flex-row">
        <div>
            <i class="fas fa-search"></i>
        </div>
        <div class="flex-1">
            <input id="main-search-input" type="text" placeholder="Type here...">
        </div>
        <div>
            <button id="main-search-btn">Search</button>
        </div>
    </div>
</div>

<script src="public/js/includes/index.js"></script> 