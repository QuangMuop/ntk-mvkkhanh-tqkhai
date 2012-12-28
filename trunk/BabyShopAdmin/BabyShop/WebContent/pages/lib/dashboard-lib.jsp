<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared-res/js/kendo.dataviz.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared-res/css/kendoui/kendo.dataviz.min.css"/>
<script>
function f_ToptenSellingToys(){
	$.ajax({
		type : "POST",
		url : "chart?",
		data : "chartName=TopTenSellingToys",
		dataType : "json",
		async : true,
		cache : false,
		timeout : 50000,
		success : function(data) {
			var jSonObject = data;	
			var toyChartSeries = new Array();
			for(var i = 0 ; i < jSonObject.length ; i++){
				var toyChartSerie = new Object();
				toyChartSerie.name = jSonObject[i].toyName;
				toyChartSerie.data = new Array();
				toyChartSerie.data.push(jSonObject[i].quantity);
				//alert(productChartSerie.data);
				toyChartSerie.type = "column";
				toyChartSeries.push(toyChartSerie);
			}
			//alert(JSON.stringify(productChartSeries));
			$("#topTenSellingToys").kendoChart({
				theme : $(document).data("kendoSkin") || "default",
				title : {
					text : "Top 10 Đồ chơi Bán Chạy Nhất"
				},
				legend : {
					position : "bottom"
				},
				chartArea : {
					background : ""
				},
				seriesDefaults : {
					type : "column"
				},
				series : toyChartSeries,
				valueAxis : {
					labels : {
						format : "{000} đơn vị"
					}
				},
				categoryAxis : {
					categories : [ "Các đồ chơi" ],
					color : "#FF3366"
				},
				tooltip : {
					visible : true,
					format : "{000} đơn vị"
				}
			});
			$(".column").height($(document).height());
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('Có lỗi');
		}
	});
}

function f_ToysSoldPerDay(){
	$.ajax({
		type : "POST",
		url : "chart?",
		data : "chartName=ToysSoldPerDay",
		dataType : "json",
		async : true,
		cache : false,
		timeout : 50000,
		success : function(data) {
			var jSonObject = data;	
			var toyChartSeries = new Array();
			var dateSeries = new Array();
			var toyChartSerie = new Object();
			toyChartSerie.name = "Số lượng đồ chơi";
			toyChartSerie.data = new Array();
			toyChartSerie.type = "line";
			for(var i = 0 ; i < jSonObject.length ; i++){
				
				dateSeries.push(jSonObject[i].date);			
				toyChartSerie.data.push(jSonObject[i].quantity);

			}
			toyChartSeries.push(toyChartSerie);
			//alert(JSON.stringify(productChartSeries));
			$("#toysSoldPerDay").kendoChart({
				theme : $(document).data("kendoSkin") || "default",
				title : {
					text : "Số lượng đồ chơi bán ra mỗi ngày"
				},
				legend : {
					position : "bottom"
				},
				chartArea : {
					background : ""
				},
				seriesDefaults : {
					type : "line"
				},
				series : toyChartSeries,
				valueAxis : {
					labels : {
						format : "{000} đơn vị"
					}
				},
				categoryAxis : {
					categories : [ "Các ngày" ],
					color : "#FF3366",
					categories: dateSeries
				},
				tooltip : {
					visible : true,
					format : "{000} đơn vị"
				}
			});
			$(".column").height($(document).height());
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert('Có lỗi');
		}
	});
}

                $(document).ready(function() {
                	$("#topOfPage").click(function(){
        				$.scrollTo( 0, 500);
        				return false;
        				});
                    setTimeout(function() {
                        // Initialize the chart with a delay to make sure
                        // the initial animation is visible
                        f_ToptenSellingToys();
                        f_ToysSoldPerDay();

                        $("#chart").bind("kendo:skinChange", function(e) {
                        	f_ToptenSellingToys();
                        	f_ToysSoldPerDay();
                        });
                    }, 400);
                });
</script>