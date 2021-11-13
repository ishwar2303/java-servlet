<%
	String flashSuccess = (String)request.getAttribute("flashSuccess");
	if(flashSuccess != null) {
		%>
		<div class="flash-success">
			<span><%= flashSuccess %></span>
			<i class="fas fa-times"></i>
		</div>
		<script>
		var flashSuccessBlock = document.getElementsByClassName('flash-success')[0]
		flashSuccessBlock.getElementsByTagName('i')[0].addEventListener('click', () => {
			flashSuccessBlock.remove()
		})
		</script>
		<%
	}
%>

<%
	String flashError = (String)request.getAttribute("flashError");
	if(flashError != null) {
		%>
		<div class="flash-success">
			<span><%= flashError %></span>
			<i class="fas fa-times"></i>
		</div>
		<%
	}
%>

<%
	String flashNote = (String)request.getAttribute("flashNote");
	if(flashNote != null) {
		%>
		<div class="flash-success">
			<span><%= flashNote %></span>
			<i class="fas fa-times"></i>
		</div>
		<%
	}
%>