<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/includes/layout.jsp" %>  
    <title>QuizWit</title>
</head>
<body>
	<%@ include file="/includes/main-header.jsp" %> 
    <div class="index-cover">
    	<div id="index-tag-line">
	    	<div class="text-center">Any successful career starts with a good education</div>
	    	<br/>
	    	<br/>
	    	<br/>
	    	<div>
		    	Be your own evaluator
		    	<br/>
		    	Pursue your dreams
	    	</div>
    	</div>
        <div class="public-quiz-block">
            <div>
                <form action="" class="form">
                    <h2 class="form-heading">Public Quiz</h2>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >First Name</label>
                            <input type="text" name="firstName"/>
                            <div class="form-response"></div>
                        </div>
                        <div class="input-container">
                            <label >Last Name</label>
                            <input type="text" name="lastName"/>
                            <div class="form-response"></div>
                        </div>
                    </div>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >Quiz URL</label>
                            <input type="text" name="quizUrl"/>
                            <div class="form-response"></div>
                        </div>
                    </div>
                    <div class="flex-row space-between btn-container">
                        <span id="reset-form-btn" class="btn btn-cancel">Reset</span>
                        <button class="btn btn-submit">Take Quiz</button>
                    </div>
                    <div class="form-footer">
                        Public Quizzes do not require key.
                    </div>
                </form>
            </div>

        </div>
    </div>
    
    <div style="height: 200px;"></div>
    <div class="flex-row pos-relative part-3">
        <div class="flex-1">
            <div id="contact-us" class="form-container">
                <form action="" class="form">
                    <h2 class="form-heading">Contact Us</h2>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >First Name</label>
                            <input type="text" name="firstName"/>
                            <div class="form-response"></div>
                        </div>
                        <div class="input-container">
                            <label >Last Name</label>
                            <input type="text" name="lastName"/>
                            <div class="form-response"></div>
                        </div>
                    </div>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >E-mail</label>
                            <input type="email" name="email"/>
                            <div class="form-response"></div>
                        </div>
                        <div class="input-container">
                            <label >Contact</label>
                            <input type="number" name="contact"/>
                            <div class="form-response"></div>
                        </div>
                    </div>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >Query</label>
                            <textarea style="height: 100px" type="text" name="query"></textarea>
                            <div class="form-response"></div>
                        </div>
                    </div>
                    <div class="flex-row space-between btn-container">
                        <span id="reset-form-btn" class="btn btn-cancel">Reset</span>
                        <button class="btn btn-submit">Send</button>
                    </div>
                    <div class="form-footer">
                        You will receive response within 24 hours.
                    </div>
                </form>
            </div>
        </div>
        <div class="flex-1 flex-center">
			<div class="why-quizwit">
                <h3>Why QuizWit?</h3>
                <div>
                    <p>
                        Online Quizzes are a popular form of entertainment with learning.
                        <br/>
                        We provide you one of the most flexible and impeccable platform that enhance your experience in online learning.
                        <br/>
                        All salient features are integrated and can be used effortlessly.
                        <br/>
                        <h4 class="mt-20">Quiz Report</h4>
                        QuizWit helps you to find your weak points with the help of Analytics .
                        <br/>
                        <h4 class="mt-20">Questions Type</h4>
                        <div class="flex-col">
                            <label>Multiple Choice Questions</label>
                            <label>One Word Answer</label>
                            <label>True/False</label>
                        </div>
                        <br/>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 200px;"></div>
    <div id="about-us" class="developer flex-col">
    	<div class="flex-center mb-20">
    		<div class="flex-col flex-center">
	    		<h2 class="mb-10">Our Team</h2>
	    		<p class="text-center">We're diverse. That's why we work well as a team.<br/>
	We provides complete professional services including web design, development, deployment and support.</p>
			</div>
    	</div>
    	<div class="flex-row">
	    	<div class="flex-1 flex-col flex-center">
	    		<img src="public/images/ishwar-baisla.png" alt="">
	    		<label class="mt-10">Ishwar Baisla</label>
	    		<span class="fb">Software Engineer</span>
	    		<label class="team-social-links">
                    <a href="" target="_blank"> <!-- Give link here -->
                    <i class="fa fa-facebook-f" aria-hidden="true"></i>
                    </a>
                    <a href="" target="_blank"> <!-- Give link here -->
                        <i class="fa fa-twitter" aria-hidden="true"></i>
                    </a>
                    <a href="" target="_blank"> <!-- Give link here -->
                        <i class="fa fa-instagram" aria-hidden="true"></i>
                    </a>
                    <a href="https://www.linkedin.com/in/ishwar-baisla-8168151aa/" target="_blank"> <!-- Give link here -->
                        <i class="fa fa-linkedin-square" aria-hidden="true"></i>
                    </a>
                </label>
	    	</div>
    	</div>
    </div>

	<%@ include file="/includes/main-footer.jsp" %>  
	
	
	<script>
		<% 
			String ref = (String) request.getAttribute("ref");
			if(ref != null) {
				%>
					$('#<%= ref %>').click();
				<%
			}
		%>
	</script>
</body>
</html>