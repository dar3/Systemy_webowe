'use strict';

const para = document.createElement("p");
para.innerText = "This is a paragraph. CreateElement was used to create paragraph";

document.body.appendChild(para);

const textNode = document.createTextNode("TextNode was used");
document.body.appendChild(textNode);

function Appender() {


    const node = document.createElement("li");
    const textnode = document.createTextNode("Juice");
    node.appendChild(textnode);
    document.getElementById("myList").appendChild(node);
}


function InsertingBefore() {

    const newNode = document.createElement("li");
    const textNode = document.createTextNode("Blue");
    newNode.appendChild(textNode);

    const list = document.getElementById("myList2");
    list.insertBefore(newNode, list.children[0]);
}

function Replacer() {

    const element = document.getElementById("myList").children[0];
    const newNode = document.createTextNode("Wine");
    element.replaceChild(newNode, element.childNodes[0]);
}

function Remover() {
// If a list has child nodes, remove the first (index 0)

    const list = document.getElementById("myList");
    if (list.hasChildNodes()) {
        list.removeChild(list.children[0]);
    }

}


function ParentNodeAdder(){
    const lista = document.getElementById("myList3");
    const przycisk = document.getElementById("addElement");
    const nowyElement = document.createElement("li");
    const liczbaElementow = lista.children.length;
    nowyElement.textContent = `Element ${liczbaElementow + 1}`;

    // Dodaj nowy element do listy
    lista.appendChild(nowyElement);
}




    