<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/includes/layout.jsp" %>  
    <title>Student register</title>
</head>
<body>
	
	<%@ include file="/includes/main-header.jsp" %>  
	
    <div class="wrapper flex-center">
        <div class="form-container">
            <form id="student-registration-form" class="form">
                <h2 class="form-heading">Student Registration</h2>
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

                <div class="flex-row flex-1 mb-10">
                    <div class="form-block flex-col flex-1">
                        <div class="input-container">
                            <label >
                            <i class="fas fa-envelope mr-5"></i>
                            E-mail
                            </label>
                            <input type="text" name="email"/>
                        	<div class="form-response"></div>	
                            <div id="email-verified"></div>
                        </div>
                        <div class="btn-container">
                            <span id="show-email-otp-block-btn" class="btn btn-primary">Verify E-mail</span>
                        </div>
                    </div>
                    <div class="form-block flex-row flex-1">
                        <div class="input-container">
                            <label >
                            <i class="fas fa-phone mr-5"></i>
                            Contact
                            </label>
                            <input type="number" name="contact"/>
                        	<div class="form-response"></div>
                        </div>
                    </div>
                </div>

                <div class="form-block flex-row flex-1">
                    <div class="card small-msg">
                        <p class="input-label mb-5">Gender</p>
                        <div class="select-option-container">
                            <label class="">
                                <input type="radio" name="gender" value="1">
                                <span>
                                    Male
                                </span>
                            </label>
                            <label class="">
                                <input type="radio" name="gender" value="2">
                                <span>
                                    Female
                                </span>
                            </label>
                            <label class="">
                                <input type="radio" name="gender" value="3">
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
                        <input type="text" name="institution"/>
                        <div class="form-response"></div>
                    </div>
                    <div class="input-container">
                        <label >Date of Birth</label>
                        <input  type="date" name="dateOfBirth"/>
                        <div class="form-response"></div>
                    </div>
                </div>

                <div class="form-block flex-row flex-1">
                    <div class="input-container">
                        <label >Password</label>
                        <input type="password" name="password"/>
                        <div class="form-response"></div>
                    </div>
                    <div class="input-container">
                        <label >Confirm Password</label>
                        <input type="password" name="confirmPassword"/>
                        <div class="form-response"></div>
                    </div>
                </div>
	            <div class="flex-row space-between btn-container">
	                <span id="reset-form-btn" class="btn btn-cancel">Reset</span>
	                <button class="btn btn-submit">Submit</button>
	            </div>
	            <div class="form-footer">
	            	Already have an account ? <a href="student-login">Login Now</a>
	            </div>
	            
            </form>
        </div>
    </div>
    <div id="overlay"></div>
    <div id="email-verification-dialog" class="otp-verification-dialog">
        <h3 id="otp-verification-heading" class="mb-20 center theme-color">E-mail Verification</h3>
        <p class="ml-10 mr-10 mt-10 mb-10 theme-color">Please enter OTP</p>
        <div class="otp-container mb-5">
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
            <input class="otp-box email-otp-input" type="number" max="9" name="emailOtp[]"/>
        </div>
        <div id="email-otp-response"></div>
        <div class="flex-row space-between btn-container">
            <button id="requestEmailOtpBtn" class="btn btn-primary mt-5">Request OTP</button>
            <button id="verifyEmailOtpBtn" class="btn btn-success mt-5">Verify OTP</button>
        </div>
    </div>


	<%@ include file="/includes/main-footer.jsp" %>  
	
    <script src="public/js/student/student-register.js"></script>
</body>
</html>