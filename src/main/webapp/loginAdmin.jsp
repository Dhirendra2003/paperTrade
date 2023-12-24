
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="overall.css">
<meta charset="UTF-8">
<title>Admin login</title>
<style>
    /* Style for the fieldset */
    fieldset {
      border: 2px solid white;
      border-radius: 20px;
      padding: 30px;
      width:50vw;
      margin:100px auto ;
      display: flex;
  flex-direction: column; /* To stack elements vertically */
  align-items: center; /* Centers items horizontally */
  justify-content: center; /* Centers items vertically */
  height: 50vh; 
    }
    fieldset *{
        margin: 20px 50px ;
        
    }
  </style>
</head>

<body>
<h1>Admin login</h1>
<form action="LoginAdmin" method="get">
    <fieldset>
        <legend>Enter Details</legend>
UserName:<input name="name" type="text"><br>
Password:<input name="password" type="password"><br>
<input type="submit"  class="stockSearch" value="Login">
</fieldset>
</form>
</body>
</html>