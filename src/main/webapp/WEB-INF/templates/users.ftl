<html>
<head>
	<title>Users</title>
</head>
	<body>
	<#if users?has_content>
	<ul>
	<#list users as user>
	<li>${user.name} ${user.url}</li>
	</#list>
	</ul>
	<#else>
	<p>No news has posted yet</p>
	</#if>
	</body>
</html>
