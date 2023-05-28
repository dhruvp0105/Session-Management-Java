

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="LoginServlet" method="post">
            Username : <input type="text" name="uname">
            Password : <input type="password" name="pass">
            <input type="submit" value="login">
        </form>
    </body>
</html>
