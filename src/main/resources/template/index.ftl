<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Map of a Viral Video</title>
<style>
	.fill-undefined {
		fill: #F7FCF5;
	}
	.graticule {
	  fill: none;
	  stroke: #fff;
	  stroke-width: .5px;
	  stroke-opacity: .5;
	}

	.subunit-boundary {
		fill: none;
		stroke: #bbb;
		stoke-width: .5px;
	}

	/** Colors by http://colorbrewer2.org */
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
	body {
		font-family: Arial, Helvetica, sans-serif;
		margin: 0;
		background-color: #777;
	}
	header {
		border-top: 5px solid rgb(109, 179, 63);
		color: white;
		height: 50px;
		background-color: black;
		width: 100%;
		margin: 0;
	}
	h1 {
		width: 960px;
		margin: 0 auto;
	}
	#mapContainer {
		width: 960px;
		height: 500px;
		margin: 0px auto;
		position: relative;
	}
	.description {
		color: white;
		margin: 10px 0px 0px 0px;
		background-color: rgb(109, 179, 63);
		width: 100%;
	}
	.description > div {
		width: 960px;
		margin: 0px auto;
		padding: 5px 0px;
	}
	#level1 {
		background-color: #E5F5E0;
	}
	#level2 {
		background-color: #C7E9C0;
	}
	#level3 {
		background-color: #A1D99B;
	}
	#level4 {
		background-color: #74C476;
	}
	#level5 {
		background-color: #41AB5D;
	}
	#level6 {
		background-color: #238B45;
	}
	#level7 {
		background-color: #005A32;
	}
</style>
</head>
<header>
	<h1>Map</h1>
</header>
<div id="mapContainer">
	<table cellpadding="0" cellspacing="0" style="margin:10px;width:100px;position:absolute;top:0;left:0;">
		<tr>
			<td id="level1">&nbsp;</td>
			<td id="level2">&nbsp;</td>
			<td id="level3">&nbsp;</td>
			<td id="level4">&nbsp;</td>
			<td id="level5">&nbsp;</td>
			<td id="level6">&nbsp;</td>
			<td id="level7">&nbsp;</td>
		</tr>
		<tr>
			<td>0</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>max</td>
		</tr>
	</table>
</div>
<article class="description">
	<div>
		<p>In 2003, a video named "Star Wars Kid" was uploaded to the website Waxy.org.  Within the first 2 weeks, it was downloaded 1.1 million times.  To put this number into context:
			<ul>
				<li>MySpace was formed in 2003</li>
				<li>Mark Zuckerberg would begin coding Facebook while at Harvard the next year (2004)</li>
				<li>YouTube wasn't founded until 2005</li>
				<li>A 30GB iPod cost over $500</li>
			</ul>
		</p>
		<p>In 2008, the owner of the site posted an article about the experience, including open sourcing the Apache server logs.  This 1.6GB file contains almost nine million records in it covering the traffic the site received over a six month period when the video was first posted.  The map above highlights where the requests for the video came from based upon mapping the IP addresses in the log file using the MaxMind database.</p>
	</div>
</article>
<script src="./js/d3.v3.min.js"></script>
<script src="./js/d3.geo.projection.v0.min.js"></script>
<script src="./js/topojson.v1.min.js"></script>
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

var svg = d3.select("#mapContainer").append("svg")
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

<!--
<table>
<#list countries as country>
	<tr>
		<td>${country.countryCode}</td>
		<td>${country.count}</td>
		<td>${country.scale}</td>
	</tr>
</#list>
</table>
-->
</body>
</html>
