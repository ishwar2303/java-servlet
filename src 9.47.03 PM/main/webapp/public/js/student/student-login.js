$('#student-login-form').on('submit', (e) => {
    e.preventDefault()
    e.stopPropagation()
	let form = new FormData(e.target)
	let reqData = {
        email: form.get('email'),
        password: form.get('password')
    }
    console.log(reqData)
    
    $.ajax({
        url: 'student-login',
        type: 'POST',
        success: () => {

        },
        complete: (res) => {
            let msgIcon = '<i class="fa fa-exclamation-circle mr-5"></i>'
            res = JSON.parse(res.responseText)
			console.log(res)
            let responseMsg = document.getElementsByClassName('form-response')
            if(!res.success) {
                let errorLog = res.errorLog
                for(let i=0; i<responseMsg.length; i++) {
					if(errorLog[i] != '')
                    	responseMsg[i].innerHTML =  msgIcon + errorLog[i]
					else responseMsg[i].innerHTML =  ''
                }
            }
			else {
				let a = document.createElement('a')
				a.href = 'student-dashboard'
				a.click()
			}
        }, 
        data: reqData
    })
})

let footerHeight = document.getElementsByClassName('main-footer')[0].offsetHeight
let bodyHeight = document.body.offsetHeight
let availableHeight = bodyHeight - footerHeight + 50
document.getElementsByClassName('wrapper')[0].style.height = availableHeight + 'px'