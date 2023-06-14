//Generate the nodes for classes and properties first perferably in JSON
export const nodes = [];
//Generate the links by first finding out if they exist in previous data and assigning indicies to them and formatting as JSON (perferably)
export const links = [];

import { nodesArray } from "./nodes.js";
import { linkArray } from "./link.js";

const addNode = (node) => {
    console.log(node.x);
    nodes.push(node);
};

const addLink = (link) => {
    links.push(link);
}

for (let i = 0; i < nodesArray.length; i++) {
    addNode(nodesArray[i]);
}

for (let i = 0; i < linkArray.length; i++) {
    addLink(linkArray[i]);
}