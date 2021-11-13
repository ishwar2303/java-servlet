var flash = false

const createFlashMessage = (i = 'fa-exclamation-circle', bgColor, msg) => {
	let flashBlock = document.createElement('div')
	let flashMessage = document.createElement('div')
	let icon = document.createElement('i')
	flashMessage.classList.add('ml-10')
	flashMessage.innerHTML = msg
	icon.classList.add('fas')
	icon.classList.add(i)
	icon.style.display = "flex"
	icon.style.alignSelf = "flex-start"
	flashBlock.classList.add('flash-message')
	flashBlock.classList.add(bgColor)
	flashBlock.appendChild(icon)
	flashBlock.appendChild(flashMessage)
	return flashBlock
}

// create processing... look in button
const processingRequest = (pro) => {
    let process = document.createElement('div')
    process.className = 'flex-row flex-center'
    let icon = document.createElement('i')
    icon.className = 'fas fa-sync fa-spin'
    let span = document.createElement('span')
    if(pro != "")
        span.className = 'mr-5'
    span.appendChild(icon)
    let req = document.createElement('div')
    req.innerHTML = pro
    process.appendChild(span)
    process.appendChild(req)
    
    return process
}
