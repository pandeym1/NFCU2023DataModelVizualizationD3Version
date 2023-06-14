import {nodes, links} from './data.js';

const PRIM_COLOR = "lightblue";
const CLASS_COLOR = "#69b3a2";
const CLASS_LINK_COLOR = "black";
const PRIM_LINK_COLOR = "darkgrey";

export function fillCircleColor(node) { 
    if (node.NodeType == "String") {
      return PRIM_COLOR;
    } else {
      return CLASS_COLOR;
    }
}

export function lineProp(link) { 
    console.log(link)
    if (link.target.NodeType != "String" && link.source.NodeType != "String") {
      return CLASS_LINK_COLOR;
    } else {
      return PRIM_LINK_COLOR;
    }
}

export function lineType(link) { 
    console.log(link)
    if (link.target.NodeType != "String" && link.source.NodeType != "String") {
      return "0 0";
    } else {
      return "5 5";
    }
}

export function DetermineNodeSize(node) {
  if (node.NodeType == "String") {
    return 40;
  } else {
    return 50;
  }
}