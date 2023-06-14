import {nodes, links} from './data.js';
import {fillCircleColor, lineProp, lineType, DetermineNodeSize} from './functions.js';

const svg = d3.select('#container');
const width = +svg.attr('width');
const height = +svg.attr('height');
const centerX = width / 2;
const centerY = height / 2;

const simulation = d3.forceSimulation(nodes)
      .force("charge",d3.forceManyBody().strength(-400))
      .force("link", d3.forceLink(links).distance(link => link.source.distance))
      .force("center", d3.forceCenter(centerX, centerY));

const arrow = svg.append("svg:defs").append("svg:marker")
      .attr("id", "arrow")
      .attr("viewBox", "0 -5 10 10")
      .data(links)
      .attr('refX', -80)
      .attr("markerWidth", 5)
      .attr("markerHeight", 5)
      .attr("orient", "auto")
      .append("svg:path")
      .attr("d", "M0,-5L10,0L0,5");

const lines = svg
      .selectAll('line.link')
      .data(links)
      .enter().append('path')
      .attr('stroke', link => lineProp(link))
      .attr('marker-start', (d) => "url(#arrow)")
      .attr("stroke-width", 2)
      .style("stroke-dasharray", link => lineType(link))
      .style("stroke-dashoffset", "5");


const circles = svg
      .selectAll('circle')
      .data(nodes)
      .enter().append('circle')
      .attr("fill", node => fillCircleColor(node))
      .attr('r', node => DetermineNodeSize(node));

const text = svg
      .selectAll('text')
      .data(nodes)
      .enter().append('text')
      .attr("fill", "black")
      .attr("text-anchor", "middle")
      .attr("alignment-baseline", "middle")
      .text(node => node.NodeName);

simulation.on('tick', () => {
  console.log('tick');

  lines.attr( "d", (d) => "M" + d.source.x + "," + d.source.y + ", " + d.target.x + "," + d.target.y);
  arrow.attr('fill', link => lineProp(link));
    
  circles.attr('cx', d => d.x)
         .attr('cy', d => d.y);
  
  text.attr('x', d => d.x)
      .attr('y', d => d.y);
});