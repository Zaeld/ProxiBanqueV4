/**
 * 
 */
function showMessage() {
	alert("Hello World!");
}

jQuery(document).ready(function() {
	console.log("dans le ready...");
	testLancement();
	
//	console.log("test récupération...");
//	
//	console.log($("#dataNbTransaction").attr("value"));
//
//	var str = $("#dataNbTransaction").attr("value");
//	var list = str.split(",");
//	console.log(list);
	
});

function testLancement() {
	console.log("test de lancement...");
	
	var strNbTransaction = $("#dataNbTransaction").attr("value");
	var listNbTransaction = strNbTransaction.split(",");
	console.log(listNbTransaction);
	var strValues = $("#dataValues").attr("value");
	var listValues = strValues.split(",");
	console.log(listValues);
	var strMonths = $("#dataMonths").attr("value");
	var listMonths = strMonths.split(",");
	console.log(listMonths);
	
	var ctx = document.getElementById("myChart");
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : listMonths,
			datasets : [
					{
						label : '# de transaction',
						data : listNbTransaction,
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)',
								'rgba(255, 99, 132, 0.2)' ],
						yAxisID : 'y-axis-1',
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)', 'rgba(255,99,132,1)',
								'rgba(255,99,132,1)' ],
						borderWidth : 1
					},
					{
						label : 'Valeur en €',
						data : listValues,
						backgroundColor : [ 'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(54, 162, 235, 0.2)' ],
						yAxisID : 'y-axis-2',
						borderColor : [ 'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(54, 162, 235, 1)' ],
						borderWidth : 1
					} ]
		},
		options : {
			scales : {
				// yAxes: [{
				// ticks: {
				// beginAtZero:true
				// }
				// }]

				yAxes : [ {
					type : 'linear', // only linear but allow scale type
					// registration. This allows extensions
					// to exist solely for log scale for
					// instance
					display : true,
					position : 'left',
					id : 'y-axis-1',
				}, {
					type : 'linear', // only linear but allow scale type
					// registration. This allows extensions
					// to exist solely for log scale for
					// instance
					display : true,
					position : 'right',
					id : 'y-axis-2',
					gridLines : {
						drawOnChartArea : false
					}
				} ],

			}
		}
	});

	console.log("fin du test de lancement...");
}