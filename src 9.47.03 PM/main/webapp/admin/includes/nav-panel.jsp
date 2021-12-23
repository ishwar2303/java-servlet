<% 
	String activeNavLink = (String) request.getAttribute("activeNavLink");
	String activeLink = "active-nav-link";
	String dashboardLink = activeNavLink.equals("admin-dashboard") ? activeLink : "";
	String profileLink = activeNavLink.equals("admin-profile") ? activeLink : "";
	String createQuizLink = activeNavLink.equals("create-quiz") ? activeLink : "";
%>
<div class="navigation">
    <div class="links">
        <a href="admin-dashboard" class="<%= dashboardLink %>">
            <div>
                <div class="icon">
        			<i class="fas fa-tachometer-alt"></i>
                </div>
                <div class="link-desc">Dashboard</div>
            </div>
        </a>
        <a href="create-quiz" class="<%= createQuizLink %>">
            <div>
                <div class="icon">
                    <i class="fas fa-plus"></i>
                </div>
                <div class="link-desc">Create Quiz</div>
            </div>
        </a>
        <a href="admin-profile" class="<%= profileLink %>">
            <div>
                <div class="icon">
                    <i class="fas fa-user-circle"></i>
                </div>
                <div class="link-desc">Profile</div>
            </div>
        </a>
        <a href="">
            <div>
                <div class="icon">
                    <i class="fas fa-bug"></i>
                </div>
                <div class="link-desc">Reported Queries</div>
            </div>
        </a>
        <a href="">
            <div>
                <div class="icon">
                    <i class="far fa-address-card"></i>
                </div>
                <div class="link-desc">About US</div>
            </div>
        </a>
        <a href="">
            <div>
                <div class="icon">
                    <i class="fas fa-phone"></i>
                </div>
                <div class="link-desc">Contact Us</div>
            </div>
        </a>
        <label id="toggle-panel">
            <div>
                <div class="icon">
                    <i class="fas fa-eye"></i>
                </div>
                <div class="link-desc">Toggle Panel</div>
            </div>
        </label>
    </div>
</div>