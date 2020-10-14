function myfunc() {
	const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let id = urlParams.get('ticker');
	findBySymbol(id);
	g(id);
}



function findBySymbol(symbol) {
       // done using promise using then function syntax
        fetch("stocks/history/"+symbol).then(function(result) {
            return result.json();
        }).then(function (cdList) {
            let htmlString = "<table class='table table-striped'><thead><tr><th>Date</th><th>Open</th><th>Low</th><th>High</th><th>Close</th><th>Adj. Close</th><th>Volume</th></tr></thead>";
            cdList.map((cd) => {
                htmlString+="<tr>";
                htmlString+="<td>"+ cd.date + "</td>";
                htmlString+="<td>"+ cd.open + "</td>";
                htmlString+="<td>"+ cd.low + "</td>";
                htmlString+="<td>"+ cd.high + "</td>";
                htmlString+="<td>"+ cd.close + "</td>";
                htmlString+="<td>"+ cd.adjClose + "</td>";
                htmlString+="<td>"+ cd.volume + "</td>";
              //  htmlString+="<td><button class='btn btn-primary' onClick='deleteById(\"" + cd.hexString + "\")'>Delete</button></td>";

                htmlString+="</tr>";
            });
            htmlString += "</table>";
            document.getElementById("cdDiv").innerHTML = htmlString;
        });
    }
    
    function g(ticker) {
		  var dataPoints = [];
		  var stockChart = new CanvasJS.StockChart("stockChartContainer",{
		    theme: "light2", //"light1", "dark1", "dark2"
		    title:{
		      text:"Closing Price"
		    },
		    charts: [{
		      axisX: {
		        crosshair: {
		          enabled: true,
		          snapToDataPoint: true
		        }
		      },
		      axisY: {
		        prefix: "$",
		        crosshair: {
		          enabled: true,
		          snapToDataPoint: true,
		          valueFormatString: "$#,###.##"
		        }
		      },
		      toolTip: {
		        shared: true
		      },
		      data: [{
		        type: "spline",
		        name: "Price",
		        yValueFormatString: "$#,###.##",
		        dataPoints : dataPoints
		      }]
		    }],
		    navigator: {
		      slider: {
		        minimum: new Date(2018, 08, 01),
		        maximum: new Date(2018, 10, 01)
		      }
		    }
		  });
		  $.getJSON("/stocks/history/"+ticker, function(data) {
		    for(var i = 0; i < data.length; i++){
		      dataPoints.push({x: new Date(data[i].date), y: Number(data[i].close)});
		    }
		    stockChart.render();
		  });
		}
		
