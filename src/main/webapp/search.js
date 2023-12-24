
      //search js separate file
      let searchbar = document.getElementById("searchInput");
      let buttonContainer = document.getElementById("searchResults");

      searchbar.addEventListener("input", () => getData(searchbar.value));
      let searchButtonsPrimary= document.getElementById("primarySearch");
      searchButtonsPrimary.addEventListener("click", () => getData(searchbar.value));
      async function getData(searchInput) {
        console.log("req");
        const url =
          "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/auto-complete?query=" +
          searchInput +
          "&region=US";
        const options = {
          method: "GET",
          headers: {
            "X-RapidAPI-Key":
              "eec0afe8e6msh0212a2ba155840fp1420a2jsn9770a1b2cc3f",
            "X-RapidAPI-Host": "apidojo-yahoo-finance-v1.p.rapidapi.com",
          },
        };

        try {
          const response = await fetch(url, options);
          const resultText = await response.text();
          const result = JSON.parse(resultText); // Parse the JSON response
          console.log(result);

          if (result.ResultSet && result.ResultSet.Result) {
            const newArray = result.ResultSet.Result.map((item) => {
              return {
                symbol: item.symbol,
                name: item.name,
                typeDisp: item.typeDisp,
              };
            });

            // Clear the previous buttons from the container
            buttonContainer.style.display="block";
            buttonContainer.innerHTML = "";

            newArray.forEach((element, index) => {
              const button = document.createElement("button");

              // Set the button's text to the symbol and name of the element
              button.innerHTML = element.name + "(" + element.typeDisp + ")";
              button.setAttribute("onclick","clearBox()");
              // Assign a unique ID to each button (e.g., "button-0", "button-1", ...)
              button.id = "button-" + index;
              button.className = "searchButtons";
              // Add a click event listener to each button
              button.addEventListener("click", () => {
                // Assuming fetchStockAndCreateChart takes the symbol as an argument
                //fetchStockAndCreateChart(element.symbol);
                console.log(element.symbol + " this was clicked");
                getChartData(element.symbol, element.name);
                buttonContainer.style.display="none";
                
                console.log("content cleared")
              }  );


             


              // Append the button to the container
              buttonContainer.appendChild(button);
            });
          } else {
            console.log("No results found.");
          }
        } catch (error) {
          console.error(error);
        }
      }

      async function getChartData(stockSymbol, stockName) {
        let url =
          "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/get-charts?symbol=" +
          stockSymbol +
          "&interval=5m&range=5d&region=US&comparisons=%5EGDAXI%2C%5EFCHI";
        let options = {
          method: "GET",
          headers: {
            "X-RapidAPI-Key":
              "eec0afe8e6msh0212a2ba155840fp1420a2jsn9770a1b2cc3f",
            "X-RapidAPI-Host": "apidojo-yahoo-finance-v1.p.rapidapi.com",
          },
        };

        try {
          const response = await fetch(url, options);
          const result = await response.text();
          console.log(result);
          console.log(
            "*************************************************************************"
          );

          const jsonData = JSON.parse(result); // Replace 'yourJsonData' with the actual JSON data

          // Extract data from the JSON
          const quoteData = jsonData.chart.result[0].indicators.quote[0];

          // Define the four arrays
          var timestamps = jsonData.chart.result[0].timestamp;
          for (let i = 0; i < timestamps.length; i++) {
            // Create a Date object
            var date = new Date(timestamps[i] * 1000);

            // Format the time in 24-hour format (HH:mm:ss)
            timestamps[i] = date.toTimeString().split(" ")[0];

            //console.log(timeString);
          }

          let high = [];
          let low = [];
          let open = [];
          let close = [];

          high = quoteData.high;
          low = quoteData.low;
          open = quoteData.open;
          close = quoteData.close;
          // Now you have the data in the 'high', 'low', 'open', and 'close' arrays
          console.log("time:", timestamps);
          console.log("High:", high);
          console.log("Low:", low);
          console.log("Open:", open);
          console.log("Close:", close);

          var data = [];
          for (let i = 0; i < high.length; i++) {
            data.push([timestamps[i], low[i], open[i], close[i], high[i]]);
          }
          timestamps = timestamps.slice(-76, -1);
          data = data.slice(-76, -1);
          console.log(data);
          console.log(timestamps);

          // Now you can create a DataTable
          makeChart(
            timestamps,
            high,
            low,
            open,
            close,
            data,
            stockName,
            stockSymbol
          );
        } catch (error) {
          console.error(error);
        }
      }
      var dataTableArray = [];

      function makeChart(
        
        timestamps,
        high,
        low,
        open,
        close,
        data,
        stockName,
        stockSymbol
      ) {
        google.charts.load('current', {'packages':['corechart']});
      //google.charts.setOnLoadCallback(drawChart);
        var chartData = google.visualization.arrayToDataTable(
          [
            data[0],
            data[1],
            data[2],
            data[3],
            data[4],
            data[5],
            data[6],
            data[7],
            data[8],
            data[9],
            data[10],
            data[11],
            data[12],
            data[13],
            data[14],
            data[15],
            data[16],
            data[17],
            data[18],
            data[19],
            data[20],
            data[21],
            data[22],
            data[23],
            data[24],
            data[25],
            data[26],
            data[27],
            data[28],
            data[29],
            data[30],
            data[31],
            data[32],
            data[33],
            data[34],
            data[35],
            data[36],
            data[37],
            data[38],
            data[39],
            data[40],
            data[41],
            data[42],
            data[43],
            data[44],
            data[45],
            data[46],
            data[47],
            data[48],
            data[49],
            data[50],
            data[51],
            data[52],
            data[53],
            data[54],
            data[55],
            data[56],
            data[57],
            data[58],
            data[59],
            data[60],
            data[61],
            data[62],
            data[63],
            data[64],
            data[65],
            data[66],
            data[67],
            data[68],
            data[69],
            data[70],
            data[71],
            data[72],
            data[73],
            data[74],
          ],
          true
        );

        var options = {
          legend: "none",
          title: stockName,
          backgroundColor: {
            fill: '#ccf3ff',
            fillOpacity: 1.0
          },
          chartArea: {
            color:"white",
        },
        hAxis:{
            color:"white"
        }
        
       
     // backgroundColor: {
       // fill: '#FF0000',
       // fillOpacity: 0.8
     // },
        };

        var chart = new google.visualization.CandlestickChart(
          document.getElementById("chart_div")
        );

        chart.draw(chartData, options);

        var stockPriceCurrent = close[close.length - 1];
        stockPriceCurrent = parseFloat(stockPriceCurrent);
        stockPriceCurrent = stockPriceCurrent.toFixed(2);
        var stockPriceDisplay = document.getElementById("stockInfo");

        let price = document.getElementById("ptagprice");
        price.innerText = stockPriceCurrent;
        //price.setAttribute("id", stockPriceCurrent);
        //price.setAttribute("name", 'stockPriceCurrent');
        
        price.setAttribute("value", stockPriceCurrent);
        //stockPriceDisplay.appendChild(price);

        

        let nameStock = document.getElementById("ptagname");
        nameStock.innerText = stockName;
        //nameStock.setAttribute("id", stockName);
        //nameStock.setAttribute("name", 'stockName');
        nameStock.setAttribute("value", stockName);
        
        //stockPriceDisplay.appendChild(nameStock);

        let symstock = document.getElementById("ptagsymbol");
        symstock.innerText = stockSymbol;
        //symstock.setAttribute("id", stockSymbol);
        //symstock.setAttribute("name", 'stockSymbol');
        symstock.setAttribute("value", stockSymbol);
        
        //stockPriceDisplay.appendChild(symstock);

        // Create  input for stockPriceCurrent
var hiddenPriceInput = document.getElementById("hiddenprice");
//hiddenPriceInput.setAttribute("type", "hidden");
//hiddenPriceInput.setAttribute("name", "stockPriceCurrent");
//hiddenPriceInput.setAttribute("id", "hiddenprice");

// Create hidden input for stockName
var hiddenNameInput = document.getElementById("hiddenname");
//hiddenNameInput.setAttribute("type", "hidden");
//hiddenNameInput.setAttribute("name", "stockName");
//hiddenNameInput.setAttribute("id", "hiddenname");

// Create hidden input for stockSymbol
var hiddenSymbolInput = document.getElementById("hiddensymbol");
//hiddenSymbolInput.setAttribute("type", "hidden");
//hiddenSymbolInput.setAttribute("name", "stockSymbol");
//hiddenSymbolInput.setAttribute("id", "hiddensymbol");

hiddenSymbolInput.setAttribute("value", stockSymbol);
hiddenPriceInput.setAttribute("value", stockPriceCurrent);
hiddenNameInput.setAttribute("value", stockName);


// Add these inputs to a form element (e.g., with the id "myForm")
//var form = document.getElementById("myForm");
//stockPriceDisplay.appendChild(hiddenPriceInput);
//stockPriceDisplay.appendChild(hiddenNameInput);
//stockPriceDisplay.appendChild(hiddenSymbolInput);

      }
      //initial load
      google.charts.load("current", { packages: ["corechart"] });
      //google.charts.setOnLoadCallback(drawChart);
      let niftysymbol = "^NSEI";
      let nifty = "NIFTY 50(Index)";
      getChartData(niftysymbol, nifty);


      //news Data
  async function newsData(){
  const apiUrl = 'https://www.alphavantage.co/query?function=NEWS_SENTIMENT&apikey=KIQ2HLOTWE80KZ2Y';
titles=[]
summaries=[]
bannerImages=[]
//const newsdiv=document.getElementById("newsSection")
    try {
        const response = await fetch(apiUrl);
       if (!response.ok) {
    throw new Error('Request failed with status: ' + response.status);}

        const data = await response.json();
        console.log(data.feed);
        const news=data.feed
        for (const item of news) {
    titles.push(item.title);
    summaries.push(item.summary);
    bannerImages.push(item.banner_image);
}
//console.log(titles,summaries,bannerImages)
const newsSection = document.getElementById("newsSection");

// Create divs for titles, summaries, and banner images
for (let i = 0; i < titles.length; i++) {
    const newsDiv = document.createElement("div");
    newsDiv.className = "news-item";

    const titleDiv = document.createElement("div");
    titleDiv.textContent = titles[i];
    titleDiv.className("title");
    newsDiv.appendChild(titleDiv);

    const summaryDiv = document.createElement("div");
    summaryDiv.textContent = summaries[i];
    newsDiv.appendChild(summaryDiv);

    const bannerImageDiv = document.createElement("div");
    const bannerImage = document.createElement("img");
    bannerImage.src = bannerImages[i];
    bannerImageDiv.appendChild(bannerImage);
    newsDiv.appendChild(bannerImageDiv);

    // Append the news div to the parent news section
    newsSection.appendChild(newsDiv);
}
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}
//top pics finder
      async function searchTops(){
  var url = 'https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-movers?lang=en-US&region=IN&start=0&count=10';
var options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': 'eec0afe8e6msh0212a2ba155840fp1420a2jsn9770a1b2cc3f',
		'X-RapidAPI-Host': 'apidojo-yahoo-finance-v1.p.rapidapi.com'
	}
};

//arrays
gainers=[]
losers=[]
mostActive=[]
try {
	var response = await fetch(url, options);
	var result1 = await response.json();
	console.log(result1);
  for(let i=0;i<10;i++){
  //console.log(result1.finance.result[0].quotes[i].symbol)
  //console.log(result1.finance.result[1].quotes[i].symbol)
  //console.log(result1.finance.result[2].quotes[i].symbol)

  gainers.push(result1.finance.result[0].quotes[i].symbol)
 losers.push(result1.finance.result[1].quotes[i].symbol)
  mostActive.push(result1.finance.result[2].quotes[i].symbol)
  }
  console.log(gainers,losers,mostActive);
  //make tables
  const table = document.getElementById("table");

  for (let i = 0; i < 10; i++) { // Iterate through data arrays
    const row = document.createElement("tr");
    var button3 = document.createElement("button");

    var button2 = document.createElement("button");

    var button1 = document.createElement("button");
    
    // Create a table row for each data entry

    // Create a cell for the gainer stock
    const gainerStocks = document.createElement("td");
    button1.textContent=gainers[i];
    button1.addEventListener("click", () => getChartData(gainers[i],gainers[i]))
    gainerStocks.appendChild(button1);
    row.appendChild(gainerStocks);

    // Create a cell for the loser stock
    const loserStocks = document.createElement("td");
    button2.textContent=losers[i];
    button2.addEventListener("click", () => getChartData(losers[i],losers[i]));
    loserStocks.appendChild(button2);
    row.appendChild(loserStocks);

    // Create a cell for the most active stock
    const actives = document.createElement("td");
    button3.textContent=mostActive[i];
    button3.addEventListener("click", () => getChartData(mostActive[i],mostActive[i]));
    actives.appendChild(button3);
    row.appendChild(actives);

    // Add the row to the table
    table.appendChild(row);
}

// Append the table to the document's body
//document.body.appendChild(table);
} catch (error) {
	console.error(error);
 }
newsData()
}
searchTops();


