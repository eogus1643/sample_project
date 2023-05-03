$(document).ready(function(){
		// [ 챌린지 차트 ]
	/////////// 차트 늘리기 ///////////
	var bar_ctx = document.getElementById('ch-chart__box--graph-1').getContext('2d');

	var line_grd = bar_ctx.createLinearGradient(0, 0, 300, 0);
	line_grd.addColorStop(0, '#FF51AA');
	line_grd.addColorStop(1, '#8C7BEC');

	//data
	var data = {
		labels: ['1', '2', '3', '3', '4', '5', '6', '7'],
		datasets: [
			{
				label: ['', '', '', '', '', '2,400,000원'],
				fill: true,
				data: [0, 30, 0, 0, 40, 60],
				tension: 0.1
			}
		]
	};


	//tooltip custom
	const roundedBorderTooltip = {
		id: 'roundedBorderTooltip',
		afterDraw(chart, args, options){
			const {ctx} = chart;
			ctx.save();

			//caret
			function caret(x, y){
				ctx.fillStyle = '#1a1a1a';
				ctx.beginPath();
				ctx.moveTo(x, y);
				ctx.lineTo(x - 5, y - 5);
				ctx.lineTo(x + 5, y - 5);
				ctx.fill();
			}
			
			//roundRect
			function roundRect(x, y, w, h, radius){
				const r = x + w;
				const b = y + h;
				ctx.fillStyle = '#1a1a1a';
				ctx.beginPath();
				ctx.moveTo(x + radius, y);
				ctx.lineTo(r - radius, y);
				ctx.quadraticCurveTo(r, y, r, y + radius);
				ctx.lineTo(r, y + h - radius);
				ctx.quadraticCurveTo(r, b, r - radius, b);
				ctx.lineTo(x + radius, y + h);
				ctx.quadraticCurveTo(x, b, x, b - radius);
				ctx.lineTo(x, y + radius);
				ctx.quadraticCurveTo(x, y, x + radius, y);
				ctx.fill();
			}

			function textLabel(label, x, y){
				ctx.font = '11px "Noto Sans KR"';
				ctx.fillStyle = 'white';
				ctx.fillText(label, x, y)
			}

			chart.data.datasets.forEach((dataset, i) => {
				chart.getDatasetMeta(i).data.forEach((datapoint, index) => {

					if (chart.data.datasets[i].label[index] != ''){

						const { x, y } = datapoint.tooltipPosition();			
						
						//text
						var text = chart.data.datasets[i].label[index];					
						const textWidth = ctx.measureText(text).width;
						
						//caret
						caret(x, y-7)
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8); //x, y, w, h, radius

						//text
						textLabel(text, x - (textWidth / 2), y - 20);

					};
					
				})
			});
		}
	}


	//option
	var options = {
		responsive: true,
		title: {
			text: 'multiple colors for bars',
			display: true
		},
		scales: {
			x: {
				display: false
			},
			y: {				
				display: true,
				min: -15,
				max: 100,				
				grid: {
					display: true,
					color: ['rgba(255,255,255,0)', '#F5376E', '#F5376E', '#F5376E', ],
					borderWidth: 0,	
					lineWidth: 1,
					borderDash: [2, 2],
				},
				ticks: {
					color: ['rgba(255,255,255,0)', '#000', '#000', '#000', ],
					stepSize: 50,
					callback: function(value, index, ticks) {
                        return value+'%';
                    },
					font: {
                        size: 10
                    }
				}
			}

		},

		elements: {
			line: {
				borderColor: line_grd,
				borderWidth: 7,
				backgroundColor: 'rgba(0,0,0,0)'
			},
			point: {
				pointBackgroundColor: '#fff',
				radius: 4,
			}
		},

		plugins: {
            legend: {
                display: false
            },
			tooltip: {
				enabled: false //툴팁 감추기
            }
        }
	};

	var mychart = new Chart(document.getElementById('ch-chart__box--graph-1'), {
		type: 'line',
		data: data,
		options: options,
		plugins: [roundedBorderTooltip]
	});




	/////////// 차트 줄이기 ///////////
	var bar_ctx = document.getElementById('ch-chart__box--graph-2').getContext('2d');

	var line_grd = bar_ctx.createLinearGradient(0, 0, 300, 0);
	line_grd.addColorStop(0, '#FF51AA');
	line_grd.addColorStop(1, '#FF9CC5');

	//data
	var data = {
		labels: ['1', '2', '3', '3', '4', '5', '6', '7'],
		datasets: [
			{
				label: '자산',
				fill: true,
				data: [0, -10],
				backgroundColor: line_grd,
				borderRadius: 5,
				barPercentage: 1
			}
		]
	};
	
	//option
	var options = {
		responsive: true,
		title: {
			text: 'multiple colors for bars',
			display: true
		},
		scales: {
			x: {
				display: false
			},
			y: {				
				display: true,
				min: -10,
				max: 10,				
				grid: {
					display: true,
					color: '#F5376E',
					borderWidth: 0,	
					lineWidth: 1,
					borderDash: [2, 2],
				},
				ticks: {
					color: '#000',
					stepSize: 10,
					callback: function(value, index, ticks) {
                        return value+'%';
                    },
					font: {
                        size: 10
                    }
				}
			}

		},

		elements: {
			line: {
				borderColor: line_grd,
				borderWidth: 7,
				backgroundColor: 'rgba(0,0,0,0)'
			},
			point: {
				pointBackgroundColor: '#fff',
				radius: 4,
			}
		},

		plugins: {
            legend: {
                display: false
            },
			tooltip: {
                backgroundColor: '#000',
				cornerRadius: 10,
				padding: 10,
				displayColors: false,
				yAlign: 'bottom',
				callbacks: {
					title: function(context){
						console.log(context);
						return ``;
					},
                    label: () => {
                    return "";
                    },
					afterBody: function(context){
						return `${context[0].formattedValue}%`
					}
				}
            }
        }
	};

	var mychart = new Chart(document.getElementById('ch-chart__box--graph-2'), {
		type: 'bar',
		data: data,
		options: options
	});
});

