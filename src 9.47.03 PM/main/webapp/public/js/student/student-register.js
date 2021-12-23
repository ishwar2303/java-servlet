

// request email otp
const showEmailVerificationBtn = document.getElementById('show-email-otp-block-btn')
const emailVerificationDialog = document.getElementById('email-verification-dialog')
showEmailVerificationBtn.addEventListener('click', () => {
    emailVerificationDialog.style.display = 'block'
    overlay.style.display = 'block'
    hideElement = emailVerificationDialog
    emailOtpResponse.innerText = ''
})

const requestEmailOtpBtn = document.getElementById('requestEmailOtpBtn')
const emailOtpResponse = document.getElementById('email-otp-response')
const emailVerified = document.getElementById('email-verified')
const requestOtp = () => {
    emailOtpResponse.innerText = ''
    let email = document.getElementsByName('email')[0].value
    if(email != '') {
        requestEmailOtpBtn.innerHTML = ''
        requestEmailOtpBtn.appendChild(processingRequest('Sending OTP'))
    }
    emailOtpResponse.innerText = ''
    let reqData = {
        email
    }
    $.ajax({
        url : 'email-verification',
        type : 'POST',
        success : (msg) => {
            
        },
        complete : (res) => {
            res = JSON.parse(res.responseText)
            if(res.success) {
                requestEmailOtpBtn.disabled = true
                requestEmailOtpBtn.innerText = 'OTP Sent'
                setTimeout(() => {
                    requestEmailOtpBtn.innerText = 'Resend OTP'
                    requestEmailOtpBtn.disabled = false
                }, 3000)
            }
            else if(res.error) {
                // flash message
                console.log(res.error)
                emailOtpResponse.innerText = res.error
                emailOtpResponse.className = 'danger small-msg'
                requestEmailOtpBtn.innerText = 'Request OTP'
            }
        },
        data : reqData
    })
}
requestEmailOtpBtn.addEventListener('click', requestOtp)

const verifyEmailOtpBtn = document.getElementById('verifyEmailOtpBtn')
const emailOtpInput = document.getElementsByClassName('email-otp-input')
const verifyEmailOtp = () => {
    let emailOtpInput = document.getElementsByClassName('email-otp-input')
    verifyEmailOtpBtn.innerHTML = ''
    verifyEmailOtpBtn.appendChild(processingRequest('Verifying'))
    emailOtpResponse.innerText = ''
    let otp = ''
    for(let i=0; i<emailOtpInput.length; i++) {
        if(emailOtpInput[i].value != '') {
            otp += emailOtpInput[i].value
        }
        else {
            emailOtpResponse.innerText = 'OTP required'
            emailOtpResponse.className = 'danger small-msg'
            setTimeout(() => {
                emailOtpResponse.innerText = ''
            }, 3000)
            verifyEmailOtpBtn.innerText = 'Verify OTP'
            return
        }
    }
    console.log('Entered OTP : ' + otp)
    let type = "emailOtp"
    let reqData = {
        otp,
        type
    }
    $.ajax({
        url : 'verify-otp',
        type : 'POST',
        success : (msg) => {
            console.log('Verification request sent')
        },
        complete : (res) => {
            console.log(res.responseText)
            res = JSON.parse(res.responseText)
            if(res.success) {
                emailOtpResponse.innerText = res.success
                emailVerified.innerHTML = '<i class="fas fa-check mr-5"></i>' + res.success
                emailVerified.className = 'success small-msg mt-5'
                emailVerified.style.display = 'block'
                emailOtpResponse.className = 'success small-msg'
                overlay.style.display = 'none'
                hideElement.style.display = 'none'
                showEmailVerificationBtn.style.display = 'none'
            }
            else if(res.error) {
                emailOtpResponse.innerText = res.error
                emailOtpResponse.className = 'danger small-msg'
                setTimeout(() => {
                    emailOtpResponse.innerText = ''
                }, 3000)
                emailVerified.innerText = ''
                emailVerified.style.display = 'none'
            }
            verifyEmailOtpBtn.innerText = 'Verify OTP'
        },
        data : reqData
    })

}
verifyEmailOtpBtn.addEventListener('click', verifyEmailOtp)


$('#student-registration-form').on('submit', (e) => {
    e.preventDefault()
    e.stopPropagation()
    let form = new FormData(e.target)
    let reqData = {
        firstName: form.get('firstName'),
        lastName: form.get('lastName'),
        email: form.get('email'),
        contact: form.get('contact'),
        gender: form.get('gender'),
        institution: form.get('institution'), 
        dateOfBirth: form.get('dateOfBirth'),
        password: form.get('password'),
        confirmPassword: form.get('confirmPassword')
    }
    console.log(reqData)
    
    $.ajax({
        url: 'student-register',
        type: 'POST',
        success: () => {

        },
        complete: (res) => {
            let msgIcon = '<i class="fa fa-exclamation-circle mr-5"></i>'
            res = JSON.parse(res.responseText)
			console.log(res)
            console.log(res)
            let responseMsg = document.getElementsByClassName('form-response')
            if(res.error) {
                let errorLog = res.errorLog
                console.log(errorLog)
                for(let i=0; i<responseMsg.length; i++) {
					if(errorLog[i] != '')
                    	responseMsg[i].innerHTML =  msgIcon + errorLog[i]
					else responseMsg[i].innerHTML =  ''
                }
            }
            else {
				let a = document.createElement('a')
				a.href = 'student-login'
				a.click()
            }
        }, 
        data: reqData
    })
})
document.getElementById('reset-form-btn').addEventListener('click', () => {
    document.getElementById('student-registration-form').reset()
})
