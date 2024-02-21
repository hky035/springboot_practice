<!DOCTYPE html>
<html>
<head>
    <title>To Do - Login</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family: 'Arial', sans-serif;
        }
        .login-form {
            width: 300px;
            padding: 30px;
            border: 1px solid #f1f1f1;
            background: #fff;
        }
        .login-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
        }
        .login-form button {
            width: 100%;
            padding: 10px;
            border: none;
            background: #0275d8;
            color: #fff;
        }
    </style>
</head>
<body>
        <h2>To Do list Login</h2>
		<pre>${errorMessage} </pre>
    <form method="post">	
        Name: <input type="text" name="name">
        Password : <input type="password" name="password">
        <button type="submit">Login</button>
    </form>
</body>
</html>
