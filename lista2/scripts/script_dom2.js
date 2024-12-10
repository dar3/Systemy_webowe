
'use strict';


function myImage() {
    var i = document.images.length;
    document.getElementById("ImageCount").innerHTML = i;
}

function myLinks() {
    let numb = document.links.length;
    document.getElementById("LinksNumber").innerHTML = numb;
}

function myForms() {
    let html = document.forms.namedItem("myCountry").innerHTML;
    document.getElementById("demo").innerHTML = html;
}

function myFlag() {
    const x = document.createElement("img");
    const t = document.createTextNode("Tutorials");
    x.setAttribute("src", "img/flag_of_Ukraine.png",);
    x.appendChild(t);
    document.body.appendChild(x);
}

function helpUkraine() {
    const x = document.createElement("A");
    const t = document.createTextNode("Support Ukraine");
    x.setAttribute("href", "https://savelife.in.ua/en/donate-en/#donate-army-card-once");
    x.setAttribute("target", "_blank");
    x.appendChild(t);
    document.body.appendChild(x);
}


function showElements() {

    const lista = document.getElementById("moja-lista").children;


    const output = document.getElementById("showEl");


    output.textContent = lista.item(0).textContent;


    console.log("Pobieranie elementów metodą item:");
    console.log(lista.item(0).textContent);
}