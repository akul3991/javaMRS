var param = {
		width : 600,
		height : 600,
		axisLength : 260,
		noOfAxes : 5,
		labelMargin : 20,
		valueMargin : 20
}

function draw() {
	var big5Values = [
	              	{label : "Estroversione", value : document.getElementById("big5_estr").innerHTML},
	              	{label : "Apertura", value : document.getElementById("big5_ape").innerHTML},
	              	{label : "Gradevolezza", value : document.getElementById("big5_grad").innerHTML},
	              	{label : "Coscenziosità", value : document.getElementById("big5_cosc").innerHTML},
	              	{label : "Nevroticismo", value : document.getElementById("big5_nevr").innerHTML}
	];
	
	var graph = d3.select("#pRadar")
	              .append("svg")
	              .attr("width", param.width)
	              .attr("height", param.height)
	              .append("g");
	
	var axis = graph.selectAll(".axis")
	                .data(big5Values)
	                .enter();
	
	var axisLines = axis.append("line")
	                    .attr("x1", param.width/2)
	                    .attr("y1", param.height/2)
	                    .attr("x2", function(d, i) {return param.width/2 + axisTipX(i)})
	                    .attr("y2", function(d, i) {return param.height/2 + axisTipY(i)})
	                    .attr("class", "axis")
	                    .style("stroke", "black")
	                    .style("stroke-width", "1px");
	
	var axisLabels = axis.append("text")
	                     .attr("x", function(d, i) {return param.width/2 + axisTipX(i)})
	                     .attr("y", function(d, i) {return param.height/2 + axisTipY(i) - param.labelMargin})
	                     .text(function(d) {return d.label})
	                     .style("font-size", 12)
	                     .style("text-anchor", "middle")
	                     .style("fill", "black")
	
	var area = graph.selectAll(".graphArea")
				    .data([big5Values])
				    .enter()
				    .append("polygon")
				    .attr("points", function(d) {
				    	pointStr = "";
				    	d.forEach(function (dEl, i, arr) {
				    		pointStr += plotPoint(dEl, i);
				    		if (i+1 < arr.length)
				    			pointStr += " ";
				    	});
				    	return pointStr;
				    })
				    .attr("class", "graphArea")
				    .style("fill", "blue")
				    .style("fill-opacity", 0.6);
	
	var axisValues = axis.append("text")
	                     .attr("x", function(d, i) {return dataPoint(d, i).x + param.valueMargin*cosine(360*i/param.noOfAxes)})
	                     .attr("y", function(d, i) {return dataPoint(d, i).y + param.valueMargin*sine(360*i/param.noOfAxes)})
	                     .text(function(d) {return d.value})
	                     .style("font-size", 12)
	                     .style("fill", "black")
	                     .style("stroke", "black");
}

function axisTip(axisNo) {
	return {
		x : param.width/2 + param.axisLength * cosine(360*axisNo/param.noOfAxes),
		y : param.height/2 + param.axisLength * sine(360*axisNo/param.noOfAxes)
	}
}

function dataPoint(data, i) {
	var origin = {
			x : param.width/2,
			y : param.height/2
	};
	
	var pointPosition = {
			x : param.axisLength * cosine(360*i/param.noOfAxes) * (data.value/5.0),
			y : param.axisLength * sine(360*i/param.noOfAxes) * (data.value/5.0)
	};
	
	return {x : origin.x + pointPosition.x, y : origin.y + pointPosition.y}
}

function axisTipX(axisNo) {
	return param.axisLength * cosine(360*axisNo/param.noOfAxes);
}

function axisTipY(axisNo) {
	return param.axisLength * sine(360*axisNo/param.noOfAxes);
}

function cosine(deg) {
	return Math.cos(deg * Math.PI / 180);
}

function sine(deg) {
	return Math.sin(deg * Math.PI / 180);
}

function plotPoint(data, i) {
	var origin = {
			x : param.width/2,
			y : param.height/2
	};
	
	var pointX = origin.x + axisTipX(i) * (data.value/5.0);
	var pointY = origin.y + axisTipY(i) * (data.value/5.0);
	
	return pointX + " " + pointY;
}