$(document).ready(function(){
	$('.ch-caution__button').click(function(){
		if($(this).is('.on')) {
			$(this).removeClass('on');
			$(this).next().slideUp('fast');
		}else {
			$(this).addClass('on');
			$(this).next().slideDown('fast');
		}
	});	
});

// Sample chart script
function loadSampleChartScript() {
	/////////// 모으기 차트 ///////////
	var bar_ctx = document.getElementById('fp-chart--graph-1').getContext('2d');

	var bg_1 = bar_ctx.createLinearGradient(0, 200, 0, 0);
	bg_1.addColorStop(0, '#5182FF');
	bg_1.addColorStop(1, '#3617CE');

	//data
	var data = {
		labels: ['21년  10월', '21년  11월', '21년  12월'],
		datasets: [
			{
				label: ['', '', '지난달 비교 20%감소'], // x만원 뒤에 붙일 텍스트
				data: [50, 45, 40], //소득
				backgroundColor: ['#DFE1EA', '#DFE1EA', bg_1],
				borderRadius: 6,
				barPercentage: 0.6,
				borderColor: ['#717598', '#717598', '#FF0047'],
				borderWidth: 0
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
			function caret(x, y, color){
				ctx.fillStyle = color;
				ctx.beginPath();
				ctx.moveTo(x, y);
				ctx.lineTo(x - 5, y - 5);
				ctx.lineTo(x + 5, y - 5);
				ctx.fill();
			}
			
			//roundRect
			function roundRect(x, y, w, h, radius, color){
				const r = x + w;
				const b = y + h;
				ctx.fillStyle = color;
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
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						//const text = chart.data.datasets[i].data[index] + '만원 ' + chart.data.datasets[i].label[index];
						const text = chart.data.datasets[i].label[index];
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];					
						
						//caret
						caret(x, y-7, color)
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color

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
		layout: {
			padding: {
                top: 30
            }
		},
		scales: {
			x: {
				stacked: true,
				grid: {
					borderColor: '#1a1a1a',
					borderWidth: 2,
					lineWidth: 0,
					z: 1
				},				
				ticks: {
					color: '#1a1a1a',
					font: {
						family: "'Noto Sans KR', 'sans-serif'",
                        size: 11,
						weight: 400
                    }
				}
			},
			y: {
				display: false
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

	var mychart = new Chart(document.getElementById('fp-chart--graph-1'), {
		type: 'bar',
		data: data,
		options: options,
		plugins: [roundedBorderTooltip]
	});
	
	/////////// 늘리기 차트 ///////////
	var bar_ctx = document.getElementById('fp-chart--graph-pie').getContext('2d');

	var bg_1 = bar_ctx.createLinearGradient(0, 150, 0, 50);
	bg_1.addColorStop(0, '#CD82EC');
	bg_1.addColorStop(1, '#8C7BEC');

	var bg_2 = bar_ctx.createLinearGradient(0, 150, 0, 50);
	bg_2.addColorStop(0, '#53DEE5');
	bg_2.addColorStop(1, '#58AFE9');

	var bg_3 = bar_ctx.createLinearGradient(0, 100, 0, 0);
	bg_3.addColorStop(0, '#78E9C1');
	bg_3.addColorStop(1, '#15CDC0');

	//data
	var data = {
		labels: ['1,569만원', '2,746만원', '3,531만원'],
		datasets: [
			{
				label: '자산',
				data: [20.1, 35.4, 44.5],
				backgroundColor: [bg_3, bg_2, bg_1],
				borderColor: '#fff',
				borderWidth: 1
			}
		]
	};

	//tooltip custom
	const pieChartText = {
		id: 'pieChartText',
		afterDraw(chart, args, options){
			const {ctx} = chart;
			ctx.save();

			function labelPercent(label, x, y){
				ctx.font = '15px "Noto Sans KR"';
				ctx.fillStyle = 'white';
				ctx.fillText(label, x, y)
			}

			function labelText(label, x, y){
				ctx.font = '10px "Noto Sans KR"';
				ctx.fillStyle = 'white';
				ctx.fillText(label, x, y)
			}

			chart.data.datasets.forEach((dataset, i) => {
				chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
					const { x, y } = datapoint.tooltipPosition();
					//console.log(datapoint.tooltipPosition())
					
					//text		
					const percent = chart.data.datasets[i].data[index]+'%';
					const percentWidth = ctx.measureText(percent).width;

					const text = chart.data.labels[index];
					const textWidth = ctx.measureText(text).width;

					//text
					labelPercent(percent, x - (percentWidth / 2)-5, y - 8);
					labelText(text, x - (textWidth / 2), y + 5);
					
				})
			});
		}
	}
	
	//option
	var options = {
		responsive: false,
		plugins: {
            legend: {
                display: false
            },
			tooltip: {
				enabled: false, //툴팁 감추기
            }
        }
	};

	var mychart = new Chart(document.getElementById('fp-chart--graph-pie'), {
		type: 'pie',
		data: data,
		options: options,
		plugins: [pieChartText]
	});
};

// recommend chart script
function loadRecommendChartScript(elementId) {
	var bar_ctx = document.getElementById(elementId).getContext('2d');
	
	if (elementId.indexOf("reduce") > 0) {
		var bg_1 = bar_ctx.createLinearGradient(0, 0, 0, 150);
		bg_1.addColorStop(0, 'rgba(142, 122, 234, 0.6)');
		bg_1.addColorStop(1, 'rgba(142, 122, 234, 0)');
	
		var line_grd = bar_ctx.createLinearGradient(0, 0, 300, 0);
		line_grd.addColorStop(0, '#8C7BEC');
		line_grd.addColorStop(1, '#FF51AA');
		
	} else {
		var bg_1 = bar_ctx.createLinearGradient(0, 200, 0, 0);
		bg_1.addColorStop(0, '#5182FF');
		bg_1.addColorStop(1, '#3617CE');
	}
		
	if (elementId == "collect-chart--graph-0") {
		// 모으기_기초체력_소득수준		
		var data = {
			labels: ['나'],
			datasets: [
				{
					label: '150', //소득
					data: [2], //분위
					backgroundColor: bg_1,
					borderRadius: 6
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
						const { x, y } = datapoint.tooltipPosition();			
						
						//text
						const text = chart.data.datasets[i].label + '만원';
						const textWidth = ctx.measureText(text).width;		
						
						//caret
						caret(x, y-7)
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8); //x, y, w, h, radius
	
						//text
						textLabel(text, x - (textWidth / 2), y - 20);
						
					})
				});
			}
		}
	
		//option
		var options = {
			responsive: false,
			layout: {
				padding: {
	                top: 42
	            }
			},
			scales: {
				x: {
					stacked: false,
					grid: {
						borderWidth: 0,
						lineWidth: 0,
						z: 1
					},
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 11,
							weight: 400
	                    }
					}
				},
				y: {
					display: false,
					min: 0,
					max: 10 //분위 레벨 max값
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
		
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip]
		});
		
	} else if (elementId == "collect-chart--graph-1") {
		// 모으기 면역력 비상 예비자금
		var data = {
			labels: ['보유', '권장'],
			datasets: [
				{
					label: ['0.7개월', '3개월'],
					data: [220, 906], //자금
					backgroundColor: [bg_1, '#DFE1EA'],
					borderRadius: 6,
					barPercentage: 0.54,
					borderColor: ['#1a1a1a', '#FF0047'],
					borderWidth: 0
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
				function caret(x, y, color){
					ctx.fillStyle = color;
					ctx.beginPath();
					ctx.moveTo(x, y);
					ctx.lineTo(x - 5, y - 5);
					ctx.lineTo(x + 5, y - 5);
					ctx.fill();
				}
				
				//roundRect
				function roundRect(x, y, w, h, radius, color){
					const r = x + w;
					const b = y + h;
					ctx.fillStyle = color;
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
				
				//textLabel
				function textLabel(label, x, y){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = 'white';
					ctx.fillText(label, x, y)
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						if (chart.data.datasets[i].data[index] == 0){
							var text = '없음';
						} else {
							var text = chart.data.datasets[i].data[index].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '만원';
						};
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];
						
						//caret
						caret (x, y-7, color);
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color
	
						//text
						textLabel (text, x - (textWidth / 2), y - 20);					
						
					})
				});
			}
		}
	
	
		const dashLine = {
			id: 'dashLine',
			beforeDraw(chart, args, options){
				const {ctx} = chart;
				ctx.save();
	
				
				//dashLine
				function dashLine(y){
					ctx.save();
					ctx.strokeStyle = '#D0D2E0';
					ctx.beginPath();
					ctx.moveTo(46, y)
					ctx.lineTo(chart.width-46, y)
					ctx.setLineDash([2, 2]);
					ctx.stroke()
					ctx.restore();
				}
				
				//textLabel
				function textLabel(label, x, y, align){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = '#7A7A7A';
					ctx.textAlign = align;
					ctx.fillText(label, x, y);
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text
						const text = chart.data.datasets[i].label[index];
						const textWidth = ctx.measureText(text).width;
	
						//dashLine					
						dashLine (y);
	
						//text
						//console.log( (datapoint, index) );
	
						if ((datapoint, index) == 0){
							textLabel (text, 42, y + 5, 'right');
						} else {
							textLabel (text, chart.width-42, y + 5, 'left');
						};
						
					})
				});
			}
		}
	
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 60,
					left: 46,
					right: 46
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						borderColor: '#1a1a1a',
						borderWidth: 2,
						lineWidth: 0,
						borderDash: false,
						z: 1
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 12,
							weight: 400
	                    }
					}
				},
				y: {
					display: false
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
		
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip, dashLine]
		});
		
	} else if (elementId == "collect-chart--graph-3") {
		// 모으기 지속력 미래준비 저축투자
		var data = {
			labels: ['현재', '권장'],
			datasets: [
				{
					label: ['', '이상'],
					data: [48, 120], //자금
					backgroundColor: [bg_1, '#DFE1EA'],
					borderRadius: 6,
					barPercentage: 0.4,
					borderColor: ['#1a1a1a', '#FF0047'],
					borderWidth: 0
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
				function caret(x, y, color){
					ctx.fillStyle = color;
					ctx.beginPath();
					ctx.moveTo(x, y);
					ctx.lineTo(x - 5, y - 5);
					ctx.lineTo(x + 5, y - 5);
					ctx.fill();
				}
				
				//roundRect
				function roundRect(x, y, w, h, radius, color){
					const r = x + w;
					const b = y + h;
					ctx.fillStyle = color;
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
				
				//textLabel
				function textLabel(label, x, y){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = 'white';
					ctx.fillText(label, x, y)
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						if (chart.data.datasets[i].data[index] == 0){
							var text = '없음';
						} else {
							var text = chart.data.datasets[i].data[index].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '만원 ' + chart.data.datasets[i].label[index];
						};
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];
						
						//caret
						caret (x, y-7, color);
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color
	
						//text
						textLabel (text, x - (textWidth / 2), y - 20);					
						
					})
				});
			}
		}
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 60
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						borderColor: '#1a1a1a',
						borderWidth: 2,
						lineWidth: 0,
						borderDash: false,
						z: 1
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 12,
							weight: 400
	                    }
					}
				},
				y: {
					display: false
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
		
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip]
		});		
		
	} else if (elementId == "collect-chart--graph-4") {
		// 모으기 지속력 미래준비 금융자산		
		var data = {
			labels: ['현재', '권장'],
			datasets: [
				{
					label: ['', '이상'],
					data: [4280, 10700], //자금
					backgroundColor: [bg_1, '#DFE1EA'],
					borderRadius: 6,
					barPercentage: 0.4,
					borderColor: ['#1a1a1a', '#FF0047'],
					borderWidth: 0
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
				function caret(x, y, color){
					ctx.fillStyle = color;
					ctx.beginPath();
					ctx.moveTo(x, y);
					ctx.lineTo(x - 5, y - 5);
					ctx.lineTo(x + 5, y - 5);
					ctx.fill();
				}
				
				//roundRect
				function roundRect(x, y, w, h, radius, color){
					const r = x + w;
					const b = y + h;
					ctx.fillStyle = color;
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
				
				//textLabel
				function textLabel(label, x, y){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = 'white';
					ctx.fillText(label, x, y)
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						if (chart.data.datasets[i].data[index] == 0){
							var text = '없음';
						} else {
							var text = chart.data.datasets[i].data[index].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '만원 ' + chart.data.datasets[i].label[index];
						};
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];
						
						//caret
						caret (x, y-7, color);
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color
	
						//text
						textLabel (text, x - (textWidth / 2), y - 20);					
						
					})
				});
			}
		}
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 60
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						borderColor: '#1a1a1a',
						borderWidth: 2,
						lineWidth: 0,
						borderDash: false,
						z: 1
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 12,
							weight: 400
	                    }
					}
				},
				y: {
					display: false
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
		
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip]
		});
	} else if (elementId == "reduce-chart--graph-2") {
		// 줄이기 기초체력 지출 지출추이
		var data = {
			labels: ['D-2월', 'D-1월', '오늘'],
			datasets: [
				{
					label: '',
					fill: true,
					data: [0, 400000, 900000], //지출금액
					backgroundColor: [bg_1],
					tension: 0.4
				}
			]
		};
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 37
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						display: true,
						borderColor: '#eee',
						borderWidth: 0,					
						lineWidth: 1,
						borderDash: [2, 2],
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 11,
							weight: 400
	                    }
					}
				},
				y: {				
					display: false,
					min: -50000
				}
	
			},
	
			elements: {
				line: {
					borderColor: line_grd,
					borderWidth: 10,
					z: 1
				},
				point: {
					pointBackgroundColor: '#fff',
					radius: 5,
					z: 1
				}
			},
	
			plugins: {
	            legend: {
	                display: false
	            },
				tooltip: {
	                backgroundColor: '#000',
					cornerRadius: 10,
					padding: 8,
					displayColors: false,
					yAlign: 'bottom',
					callbacks: {
						title: function(context){							
							return ``;
						},
	                    label: () => {
							return "";
	                    },
						afterBody: function(context){
							return `${context[0].formattedValue}원`
						}
					}
	            }
	        }
		};
	
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'line',
			data: data,
			options: options
		});
		
	} else if (elementId == "add-chart--graph-2") {
		// 늘리기 지속력 투자성향 투자비중	
		var data = {
			labels: ['현재', '권장'],
			datasets: [
				{
					label: ['', '이상'],
					data: [8, 20], //자금
					backgroundColor: [bg_1, '#DFE1EA'],
					borderRadius: 6,
					barPercentage: 0.4,
					borderColor: ['#1a1a1a', '#FF0047'],
					borderWidth: 0
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
				function caret(x, y, color){
					ctx.fillStyle = color;
					ctx.beginPath();
					ctx.moveTo(x, y);
					ctx.lineTo(x - 5, y - 5);
					ctx.lineTo(x + 5, y - 5);
					ctx.fill();
				}
				
				//roundRect
				function roundRect(x, y, w, h, radius, color){
					const r = x + w;
					const b = y + h;
					ctx.fillStyle = color;
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
				
				//textLabel
				function textLabel(label, x, y){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = 'white';
					ctx.fillText(label, x, y)
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						if (chart.data.datasets[i].data[index] == 0){
							var text = '없음';
						} else {
							var text = chart.data.datasets[i].data[index].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '만원 ' + chart.data.datasets[i].label[index];
						};
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];
						
						//caret
						caret (x, y-7, color);
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color
	
						//text
						textLabel (text, x - (textWidth / 2), y - 20);					
						
					})
				});
			}
		}
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 60
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						borderColor: '#1a1a1a',
						borderWidth: 2,
						lineWidth: 0,
						borderDash: false,
						z: 1
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 12,
							weight: 400
	                    }
					}
				},
				y: {
					display: false
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
	
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip]
		});
		
	} else if (elementId == "add-chart--graph-3") {
		// 늘리기 지속력 투자성향 투자자산
		var data = {
		labels: ['현재', '권장'],
		datasets: [
				{
					label: ['', '이상'],
					data: [941, 2354], //자금
					backgroundColor: [bg_1, '#DFE1EA'],
					borderRadius: 6,
					barPercentage: 0.4,
					borderColor: ['#1a1a1a', '#FF0047'],
					borderWidth: 0
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
				function caret(x, y, color){
					ctx.fillStyle = color;
					ctx.beginPath();
					ctx.moveTo(x, y);
					ctx.lineTo(x - 5, y - 5);
					ctx.lineTo(x + 5, y - 5);
					ctx.fill();
				}
				
				//roundRect
				function roundRect(x, y, w, h, radius, color){
					const r = x + w;
					const b = y + h;
					ctx.fillStyle = color;
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
				
				//textLabel
				function textLabel(label, x, y){
					ctx.font = '11px "Noto Sans KR"';
					ctx.fillStyle = 'white';
					ctx.fillText(label, x, y)
				}
	
	
				chart.data.datasets.forEach((dataset, i) => {
					chart.getDatasetMeta(i).data.forEach((datapoint, index) => {
						const { x, y } = datapoint.tooltipPosition();
						//console.log(datapoint.tooltipPosition())					
						
						//text		
						//const text = chart.data.labels[index] + ':' + chart.data.datasets[i].data[index];
						if (chart.data.datasets[i].data[index] == 0){
							var text = '없음';
						} else {
							var text = chart.data.datasets[i].data[index].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '만원 ' + chart.data.datasets[i].label[index];
						};
						const textWidth = ctx.measureText(text).width;
						const color = chart.data.datasets[i].borderColor[index];
						
						//caret
						caret (x, y-7, color);
					
						//rounded Rectangle
						roundRect (x - ((textWidth + 16) / 2), y - 37, textWidth + 16, 26, 8, color); //x, y, w, h, radius, color
	
						//text
						textLabel (text, x - (textWidth / 2), y - 20);					
						
					})
				});
			}
		}
		
		//option
		var options = {
			responsive: true,
			layout: {
				padding: {
	                top: 60
	            }
			},
			scales: {
				x: {
					stacked: true,
					grid: {
						borderColor: '#1a1a1a',
						borderWidth: 2,
						lineWidth: 0,
						borderDash: false,
						z: 1
					},				
					ticks: {
						color: '#1a1a1a',
						font: {
							family: "'Noto Sans KR', 'sans-serif'",
	                        size: 12,
							weight: 400
	                    }
					}
				},
				y: {
					display: false
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
	
		var mychart = new Chart(document.getElementById(elementId), {
			type: 'bar',
			data: data,
			options: options,
			plugins: [roundedBorderTooltip]
		});
	}	
};

// challenge chart script
function loadChlngChartScript(jsonChlngInfo) {
	var chartId;
	for(key in jsonChlngInfo) {		
	    // 모으기 차트일 경우		    
	    if (jsonChlngInfo[key].chlngSe == "collect") {
	    	chartId = "ch-chart__box--graph-" + String(parseInt(key) + 1);
	    	
	    	var bar_ctx = document.getElementById(chartId).getContext('2d');
	    	
			var line_grd = bar_ctx.createLinearGradient(0, 0, 300, 0);
			line_grd.addColorStop(0, '#FF51AA');
			line_grd.addColorStop(1, '#8C7BEC');
		
			//data
			var arrChlngDtlList = jsonChlngInfo[key].chlngDtlInfoList;
			
			// 목표 개월 + 1 로 라벨 생성
			var dataLabel = [];
			// 차수 마지막 값만 입력
			var datasetLabel = [];
			var datasetData = [];
			
			if (arrChlngDtlList != null) {
				
				for(var i=0; i < arrChlngDtlList.length; i++) {
					dataLabel.push(arrChlngDtlList[i].dtlKeyVal);
					
					if (arrChlngDtlList.length - 1 == i) {
						if(arrChlngDtlList[i].sumAmt < 0) { // 모으기 금액이 마이너스일 때 0원
							arrChlngDtlList[i].sumAmt = 0;
							
						} else if(arrChlngDtlList[i].sumAmt == null){ // 모으기 배치 금액값이 null일 때 0원
							arrChlngDtlList[i].sumAmt = 0;
						}
						datasetLabel.push(arrChlngDtlList[i].sumAmt + "원");
					} else {
						datasetLabel.push('');	
					}
					if(arrChlngDtlList[i].amtToPt >= 100){
						arrChlngDtlList[i].amtToPt = 100; // 챌린지 차트 보이는 퍼센트 영역
					} else if(arrChlngDtlList[i].amtToPt <= 0){
						arrChlngDtlList[i].amtToPt = 0; // 챌린지 차트 보이는 퍼센트 영역
					}
					datasetData.push(arrChlngDtlList[i].amtToPt);
				}
				
			}
			
			dataLabel.push('');
			
			var data = {
				labels: dataLabel,
				datasets: [
					{
						label: datasetLabel,
						fill: true,
						data: datasetData,
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
						max: 130,		// 차트 높이 수정		
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
		                display: false // false
		            },
					tooltip: {
						enabled: false //툴팁 감추기
		            }
		        }
			};
		
			var mychart = new Chart(document.getElementById(chartId), {
				type: 'line',
				data: data,
				options: options,
				plugins: [roundedBorderTooltip]
			});
			
		// 줄이기 차트일 경우
		} else if (jsonChlngInfo[key].chlngSe == "reduce") {
			chartId = "ch-chart__box--graph-" + String(parseInt(key) + 1);
			
			var bar_ctx = document.getElementById(chartId).getContext('2d');

			var line_grd = bar_ctx.createLinearGradient(0, 0, 300, 0);
			line_grd.addColorStop(0, '#FF51AA');
			line_grd.addColorStop(1, '#FF9CC5');

//			console.log("chlngDtlInfoList : " , jsonChlngInfo[key].chlngDtlInfoList);				
			var arrChlngDtlList = jsonChlngInfo[key].chlngDtlInfoList;
			// 목표 개월
			var dataLabel = [];
			// 차수 마지막 값만 입력
			var datasetLabel = [];
			var datasetData = [];
			
			if (arrChlngDtlList != null) {
				for(var i=0; i < arrChlngDtlList.length; i++) {
					
					if(arrChlngDtlList[i].dtlKeyVal != 1){ // 달성기간이 1이 아닐때 (한달이 넘어갈 때)
						dataLabel.push(arrChlngDtlList[i].dtlKeyVal);
						datasetData.push(arrChlngDtlList[i].amtToPt);
					}
					
				}
			}
			
//			console.log("dataLabel : " , dataLabel);
//			console.log("datasetData : " , datasetData);
			
			dataLabel.push('');
			
			//data
			var data = {
				labels: dataLabel,
				datasets: [
					{
						label: '자산',
						fill: true,
						data: datasetData,
						backgroundColor: line_grd,
						borderRadius: 5,
						barPercentage: 1
					}
				]
			};
			
			var maxGoalAmtPt = jsonChlngInfo[key].goalAmtPt;
			var minGoalAmtPt = '-' + jsonChlngInfo[key].goalAmtPt;
			
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
						min: Number(minGoalAmtPt),
						max: Number(maxGoalAmtPt),				
						grid: {
							display: true,
							color: '#F5376E',
							borderWidth: 0,	
							lineWidth: 1,
							borderDash: [2, 2],
						},
						ticks: {
							color: '#000',
							stepSize: jsonChlngInfo[key].goalAmtPt,
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
								return ``;
							},
		                    label: () => {
		                    return "";
		                    },
							afterBody: function(context){
								return context[0].formattedValue + `%`
							}
						}
		            }
		        }
			};

			var mychart = new Chart(document.getElementById(chartId), {
				type: 'bar',
				data: data,
				options: options
			});
		}
	}		
};

// Slider script
function loadSliderScript() {
	//슬라이더
	$('.ch-home__slider').slick({
		swipe: true,
		autoplay: false,
		arrows: false,
		dots: true,
		infinite: false,		
		asNavFor: '.ch-home__title--slider'
	});
	$('.ch-home__slider').on('afterChange', function(event, slick, currentSlide) {
		if ($('.slick-active').is('.is-more') === true) {
			$('.slick-active .ch-chart__all').show();
		} else {
			$('.slick-slide .ch-chart__all').hide();
		};
	});

	$('.ch-home__title--slider').slick({
		asNavFor: '.ch-home__slider',
		arrows: false,
		fade: true,
		infinite: false
	});
};

// 챌린지홈에 디데이 날짜 롤링
$(document).ready(function(){
	$('.chlng__money-rolling').slick({
		swipe: true,
		arrows: false,
		dots: false,
		infinite: true,
		fade: true,
		autoplay: true,
		autoplaySpeed: 3000
	});
});

// 랜덤으로 값을 던져줌
function shuffle(array) { 		
	array.sort(() => Math.random() - 0.5); 
}

// YYYY-MM-DD 형태의 문자를 YYYYMMDD 형태로 변환
function fncDateToStr(argDate) {
	var tmp = '';
	
	if (argDate !== undefined){
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		tmp = String(argDate).replace(/(^\s*)|(\s*$)/gi, '').replace(regExp, ''); // 공백 및 특수문자 제거
    }
    
    return tmp;
}

// YYYYMMDD 형태의 문자를 YYYY.MM.DD 형태로 변환
function fncStrFormatDate(argDate) {
	var y = argDate.substr(0,4);
	var m = argDate.substr(4,2);
	var d = argDate.substr(6,2);
    
    return y + '.' + m + '.' + d;
}

// 챌린지 등록 Step 중 인자값에 맞는 Step으로 이동
function chlngRegStepMove(chlngSe, step, params) {		
    let f = document.createElement('form');
    
    let obj;
    
    $.each(params , function(index, key) {
    	obj = document.createElement('input');
	    obj.setAttribute('type', 'hidden');
	    obj.setAttribute('name', index);
	    obj.setAttribute('value', key);
	    
	    f.appendChild(obj);	
	});
   
    f.setAttribute('method', 'post');
    
    if (chlngSe == "collect") f.setAttribute('action', '/mydata/pass/challenge/ch03/ch03CollectRegStep' + step);
	else if (chlngSe == "add") f.setAttribute('action', '/mydata/pass/challenge/ch04/ch04AddRegStep' + step);	
    else if (chlngSe == "reduce") f.setAttribute('action', '/mydata/pass/challenge/ch05/ch05ReduceRegStep' + step);	 
    
    document.body.appendChild(f);
    f.submit();
}


// 챌린지 상세 정보로 이동
function chlngInfoDetail(chlngSe, chlngSn) {		
    let f = document.createElement('form');
    
    let obj;
    
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'chlngSn');
    obj.setAttribute('value', chlngSn);
    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    
    if (chlngSe == "collect") f.setAttribute('action', '/mydata/pass/challenge/ch03/ch03CollectChlngDtl');
    else if (chlngSe == "add") f.setAttribute('action', '/mydata/pass/challenge/ch04/ch04AddChlngDtl');
    else if (chlngSe == "reduce") f.setAttribute('action', '/mydata/pass/challenge/ch05/ch05ReduceChlngDtl');	 
    
    document.body.appendChild(f);
    f.submit();
}

// 자산 연결, 자산 등록 화면으로 이동
function moveRinkUrl(url, tokenId) {
    let f = document.createElement('form');
    
    let obj;
    
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'tokenId');
    obj.setAttribute('value', tokenId);
    
    f.appendChild(obj);
    f.setAttribute('method', 'post');
    
    f.setAttribute('action', url); 
    
    document.body.appendChild(f);    
    f.submit();
}
