<%
	String flashSuccess = (String)request.getAttribute("flashSuccess");
	if(flashSuccess != null) {
		%>
		<div class="flash-success">
			<span><%= flashSuccess %></span>
		</div>
		<%
	}
%>

<%
	String flashError = (String)request.getAttribute("flashError");
	if(flashError != null) {
		%>
		<div class="flash-success">
			<span><%= flashError %></span>
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
		</div>
		<%
	}
%>