<% if (session.getAttribute("name")==null){ response.sendRedirect("login.jsp");
} %>

<!DOCTYPE html>
<html lang="en">
  <head>
  <link rel="stylesheet" href="overall.css">
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>paperTrade -Practice your trades</title>
    <!-- -->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
    <!-- Google fonts-->
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/index-styles.css" rel="stylesheet" />
    <script
      type="text/javascript"
      src="https://www.gstatic.com/charts/loader.js"
    ></script>
    <style>
    * {
  font-family: "Ubuntu", sans-serif;
}
body {
  background-color: black;
  color: white;
  font-family: "Ubuntu", sans-serif;
  margin: 0;
  padding: 0;
}
nav {
  display: flex;
  background-color: rgb(50, 50, 50);
  padding-bottom: 10px;
  padding-top: 10px;
}
ul {
  display: flex;
  margin: 0;
  position: relative;
  right: 20px;
  margin-left: auto;
  list-style: none;
}
li {
  margin: 10px;
  background-color: rgb(44, 44, 44);
  border-radius: 100px;
  padding: 10px;
  padding: 0 20px;
  padding-top: 15px;
  transition: 0.5s;
}
li:hover {
  background-color: blue;
  transition: 0.5s;
}
ul a {
  color: white;
  text-decoration: none;
}
h1 {
  margin: 20px;
  padding-left: 5vw;
  font-size: 2em;
  color: rgb(0, 255, 255);
}
.tradesection {
  display: flex;
}
#stockInfo {
  background-color: rgb(59, 59, 59);
  margin: 0 20px;
  border-radius: 50px;
  padding: 50px;
  align-items: center;
  align-content: center;
  text-align: center;
  padding-top: 100px;
  width:20vw;
}
.graph {
  width: 100vw;
  margin: 0;
  overflow: hidden;
  border-radius: 50px;
  position: relative;
  border-radius: 100px;
}
#chart_div {
  width: 100vw;
  margin: 30px;
  overflow: hidden;
  border-radius: 50px;
  position: relative;
}
.searchBar {
  padding: 20px;
  text-align: center;
  position: relative;
}
input {
  border-radius: 20px;
  background-color: rgb(71, 71, 71);
  border: none;
  padding: 10px;
  color: white;
  font-size: 1.1em;
}
.stockSearch {
  border-radius: 20px;
  padding: 10px;
  font-size: 1.2em;
  color: white;
  background-color: rgb(0, 23, 201);
  border: none;
  cursor: pointer;
}
#searchResults {
  display: grid;
  max-width: fit-content;
  margin: auto;
  background-color: rgb(90, 90, 90);
  padding: 20px;
  border-radius: 0 0 30px 30px;
  box-shadow: 0px 5px 20px 10px rgba(0, 0, 0, 0.468);
  position: absolute;
  transform: 0.1s;
  left: 50%;
  transform: translate(-50%, 0);
  z-index: 4;
}
#searchResults button {
  margin: 2px;
  padding: 05px 50px;
  background-color: rgb(42, 42, 42);
  color: white;
  border: none;
  cursor: pointer;
  transition: 0.2s;
  border-radius: 10px;
}
#searchResults button:hover {
  background-color: blue;
  padding: 10px 20px;
  transition: 0.2s;
}
.sell {
  background-color: rgb(198, 0, 0);
}
.buy {
  background-color: rgb(9, 184, 0);
}
.volume{
  background-color:orange;
}
.buy,
.sell ,.volume{
  color: white;
  text-shadow: 0px 0px 3px black;
  border-radius: 10px;
  padding: 5px;
  margin: 5px;
}
.quantity {
  text-align: center;
  max-width: 5vw;
}
div.main {
  position: relative;
  background-color: rgb(255, 255, 255);
  width: fit-content;
  text-align: center;
  left: 50%;
  top: 50vh;
  transform: translate(-50%, -50%);
  display: flex;
  border-radius: 50px;
}
div.main div {
  margin: 20px;
}
.img {
  height: 20vh;
  margin: auto;
  margin-top: 10vh;
}
div.signup-content {
  display: flex;
}
h2 {
  color: black;
}

.news-item img {
  width: 15vw;
  height: 10vw;
  border: 5px solid cyan;
  border-radius: 10px;
  position: relative;
  
}

td {
  padding: 15px;
  transition: 0.2s;
}
th {
  background-color: rgb(255, 255, 255);
  padding: 10px;
  color: rgb(0, 0, 0);
  border-radius: 0px;
}
tr {
  border-radius: 20px;
  transition: 0.2s;
}
table {
  background-color: rgb(59, 59, 59);
  border-radius: 20px;
  margin: auto;
  margin-top: 100px;
  font-family: "Ubuntu", sans-serif;
}
tbody {
  border-radius: 20px;
}

tr:hover {
  background-color: rgb(0, 247, 255);
  color: black;
  border-radius: 20px;
  padding: 20px 15px;
  transition: 0.2s;
}
tr:hover td {
  font-family: "Ubuntu", sans-serif;
  transition: 0.2s;
}
#table tr:hover td {
  background-color: #b7b7b7;
  font-size: 1.5em;
  transition: 0.2s;
}
#table td:hover button{
  border:black solid 5px;
  border-radius:10px;
  padding:5px;
  color:black;
  font-weight:900;
  transition: 0.2s;
}
#table {
  margin-bottom: 50px;
  margin-top:30px;
  transition: 0.2s;
}
#table th{
  text-transform:uppercase;
  font-size:1.2em;
  padding:10px;
  transition: 0.2s;
  
}
table button {
  background-color: transparent;
  border: none;
  color: white;
  font-family: "Ubuntu", sans-serif;
}
.news-item {
  margin: 50px auto;
  border-radius: 20px;
  border: 5px solid white;
  display: flex;
  padding: 20px;
  transition: 0.2s;
  width: 70vw;
}
.news-item:hover {
  background-color: #333333;
  transition: 0.2s;
}
.twoInone{
  display:flex;
  margin:0 20vw;
  
}
#container div{
  background-color:#454545;
  width:30vw;
  margin:50px auto;
  padding:20px;
  border:white 5px solid;
  border-radius:20px;
  font-family:"Ubuntu", sans-serif;
  display:inline-block
}
p{
  font-size:1.5em;
  padding:20px;
  margin:auto
}
#container form input{
  background-color:red;
  padding:15px 30px;
  font-size:1.2em;
  text-transform:uppercase;
  position:relative;
  float:right;
  cursor: pointer;
}
#container{
  display:grid;
}
    </style>
  </head>
  <body id="page-top">
    <!-- Navigation-->
    <nav>
      <h1>paperTrade</h1>
      <ul>
        <li><a href="index.jsp">home</a></li>
        <li><a href="portfolio.jsp">portfolio</a></li>
        <li><a href="History">history</a></li>
        <li><a href="Logout">logout</a></li>
        <li><%=session.getAttribute("name") %></li>
        <li><%=session.getAttribute("money") %></li>
      </ul>
    </nav>

    <div style="padding-top: 50px; ">
    <form action="History" method="get" >
        <input type="submit" name="" id="" value="History" >
    </form>
    </div>
    <h1>Portfolio</h1>
<!--<p><%=session.getAttribute("holdings") %></p>-->
<div id="container"></div>

<script>
  console.log("working");
  document.addEventListener("DOMContentLoaded", function () {
  // Get the string representation from the session variable
  const hashMapString = '<%= session.getAttribute("holdings") %>';

  // Convert the string back to a JavaScript object (hashmap)
  const keyValuePairs = hashMapString.split(',');
  const myHashMap = {};

  keyValuePairs.forEach(pair => {
    const [key, value] = pair.split('=');
    myHashMap[key] = value;
  });

  // Create div elements based on the hashmap
  const container = document.getElementById("container"); // Assuming you have a container div in your HTML
  for (const key in myHashMap) {
    if (myHashMap.hasOwnProperty(key)) {
      const value = myHashMap[key];
      let ttype;
      console.log(Number(value));
if (Number(value) < 0) {
    ttype = "B";
} else {
    ttype = "S";
}

      console.log(ttype);
      // Create a div element
      const div = document.createElement("div");
      const para = document.createElement("p");
      const form = document.createElement("form");
      form.setAttribute("method","post");
      form.setAttribute("action","Storetrade");
      form.setAttribute("id",(key+"form"));
      form.innerHTML="<input type='hidden' name='stockSymbol'value="+key+">"+"<input type='submit' value='exit' onclick='alertExit()'>"+"<input type='hidden' name='qty'value="+value+">"+
      "<input type='hidden' name='stockName'value="+key+">"
      + 
      "<input type='hidden' name='tradeType'value="+ ttype+">";

      para.setAttribute("id",key);
      
      (getpriceforstock(value,key));
      // Set content or attributes as needed
      div.innerHTML = "<strong>"+key+"</strong>:" +value;
      
      // Append the div to the container
      container.appendChild(div);
      div.appendChild(para);
      div.appendChild(form);
    }
  }
});

function alertExit(){
  alert("Trade exited Succesfully!")
}

  async function getpriceforstock(quantity,symbol){
        const url = 'https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval=5m&symbol='+symbol+'&range=1d&region=US';
const options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': '33ce378580mshff4ac8dc4a3be11p1466a9jsn6fc299880e3f',
		'X-RapidAPI-Host': 'apidojo-yahoo-finance-v1.p.rapidapi.com'
	}
};

try {
	const response = await fetch(url, options);
	var result1 = await response.text();
	//console.log(result1);
  result1=JSON.parse(result1);
  let closeprice=result1.chart.result[0].indicators.quote[0].close;
  let finalclose= closeprice[closeprice.length-1];
  finalclose=finalclose.toFixed(2);
  console.log(finalclose);
  console.log(finalclose*quantity);
  let formprice=document.getElementById(symbol+"form");
  let inputofPrice=document.createElement("input");
  inputofPrice.setAttribute("type","hidden");
  inputofPrice.setAttribute("name","stockPriceCurrent");
  inputofPrice.setAttribute("value",finalclose);
  formprice.appendChild(inputofPrice);

  //hiddenprice.setAttribute("value",finalclose);
  const pdiv=document.getElementById(symbol);
  pdiv.innerText=finalclose+"("+(finalclose*quantity)+")";
} catch (error) {
	console.error(error);
}
      }


</script>
  </body>
</html>