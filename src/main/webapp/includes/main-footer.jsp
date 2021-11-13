<div class="main-footer-message">
    <marquee behavior="" direction="">An investment in knowledge pays the best interest.</marquee>  
</div>
<div class="main-footer">
    <div>
        <div class="heading">Follow Us</div>
        <div class="links">
            <a href=""><i class="fab fa-youtube mr-5"></i> Youtube</a>
            <a href=""><i class="fab fa-linkedin mr-5"></i> Linkedin</a>
            <a href=""><i class="fab fa-facebook mr-5"></i> Facebook</a>
            <a href=""><i class="fab fa-instagram mr-5"></i> Instagram</a>
            <a href=""><i class="fab fa-github mr-5"></i> Github</a>
        </div>
    </div>
    <div>
        <div class="heading">Courses</div>
        <div class="links">
            <a href="">
                <i class="fas fa-code mr-5"></i> 
                <span>Web Developement</span>
            </a>
            <a href="">
                <i class="fas fa-cloud-upload-alt mr-5"></i> 
                <span>Cloud AWS | Google</span>
            </a>
        </div>
    </div>
    <div>
        <div class="heading">Subjects</div>
        <div class="links">
            <a href="">Programming</a>
            <a href="">Data Structure</a>
            <a href="">Database</a>
            <a href="">Analytics</a>
            <a href="">Machine Learning</a>
        </div>
    </div>
    <div>
        <div class="heading">Products</div>
        <div class="links">
            <a href="index">QuizWit | Web App</a>
            <a href="https://ishwar2303.github.io/chroma/index.html" target="_blank">Chroma | JavaScript Library</a>
        </div>
    </div>

    <div>
        <div class="heading">News Letter</div>
        <div class="news-letter mb-20">
            <div class="flex-row">
                <input type="email" placeholder="E-mail">
                <button class="btn btn-danger">Subscribe</button>
            </div>
        </div>
        <div class="heading">Support</div>
        <div class="links">
            <p>
                <i class="fas fa-envelope mr-5"></i> 
                <span>ishwar2303@gmail.com</span>
            </p>
            <p>
                <i class="fas fa-phone mr-5"></i> 
                <span>+91 9821671707</span>
            </p>
        </div>
    </div>
</div>

<script>
    $('form').on('submit', (e) => {
        e.preventDefault()
        e.stopPropagation()
    })
    
    const forms = document.getElementsByTagName('form')
    for(let i=0; i<forms.length; i++) {
    	let resetBtn = document.getElementsByClassName('btn-cancel')[i]
    	resetBtn.addEventListener('click', () => {
    		forms[i].reset()
    	})
    }

    $("#move-to-contact-us").on('click',function() {
        $('html, body').animate({
            'scrollTop' : $("#contact-us").position().top - 90
        });
    });

    $("#move-to-about-us").on('click',function() {
        $('html, body').animate({
            'scrollTop' : $("#about-us").position().top - 90
        });
    });
</script>