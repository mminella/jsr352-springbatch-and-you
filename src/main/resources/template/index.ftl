<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.fill-undefined {
		fill: #F7FCF5;
	}
	.graticule {
	  fill: none;
	  stroke: #777;
	  stroke-width: .5px;
	  stroke-opacity: .5;
	}

	.subunit-boundary {
		fill: none;
		stroke: #bbb;
		stoke-width: .5px;
	}

	.fill-1 {
		fill: #E5F5E0;
	}

	.fill-2 {
		fill: #C7E9C0;
	}

	.fill-3 {
		fill: #A1D99B;
	}

	.fill-4 {
		fill: #74C476;
	}

	.fill-5 {
		fill: #41AB5D;
	}

	.fill-6 {
		fill: #238B45;
	}

	.fill-7 {
		fill: #005A32;
	}
</style>
</head>
<body>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="http://d3js.org/d3.geo.projection.v0.min.js"></script>
<script src="http://d3js.org/topojson.v1.min.js"></script>
<script>
var scores = {};
<#list countries as country>
scores['${country.countryCode}'] = ${country.scale};
</#list>

var width = 960,
	height = 500;

var projection = d3.geo.robinson()
					.scale(150)
					.translate([width / 2, height / 2])
					.precision(.1);

var path = d3.geo.path().projection(projection);

var svg = d3.select("body").append("svg")
	.attr("width", width)
	.attr("height", height);

var graticule = d3.geo.graticule();

svg.append("path")
	.datum(graticule)
	.attr("class", "graticule")
	.attr("d", path);

d3.json("./js/world.json", function(error, world) {

	var subunits = topojson.feature(world, world.objects.subunits);

	svg.selectAll(".subunit")
	    .data(subunits.features)
  		.enter().append("path")
    	.attr("class", function(d) { console.log(d.id); return "fill-" + scores[d.id]; })
    	.attr("d", path);

    svg.append("path")
      .datum(topojson.mesh(world, world.objects.subunits, function(a, b) { return a !== b; }))
      .attr("d", path)
      .attr("class", "subunit-boundary");
});
</script>

<table>
<#list countries as country>
	<tr>
		<td>${country.countryCode}</td>
		<td>${country.count}</td>
		<td>${country.scale}</td>
	</tr>
</#list>
</table>
</body>
</html>
