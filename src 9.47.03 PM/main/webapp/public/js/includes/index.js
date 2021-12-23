var hideElement = '';

const overlay = document.getElementById('overlay')
const mainSearch = document.getElementById('main-search-dialog')
const showMainSearch = () => {
    overlay.style.display = 'block'
    mainSearch.style.display = 'block'
    hideElement = mainSearch
}

const hideOverlay = () => {
    hideElement.style.display = 'none'
    overlay.style.display = 'none'
}

overlay.addEventListener('click', hideOverlay)
document.getElementById('show-main-search').addEventListener('click', showMainSearch)