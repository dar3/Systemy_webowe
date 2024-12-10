'use strict';


function ChangeBackground() {
    document.body.style.backgroundColor = "red";

}

function ChangeTextColor() {
    document.getElementById("div1").style.color = 'blue';
}


function myFunction() {
    document.getElementById("demo").style.fontFamily = "Impact,Charcoal,sans-serif";

}

function mySelection() {
    
    const selectElement = document.querySelector('#mySelect');
    const output = selectElement.value;

    
    document.querySelector('.output').textContent = output;

    
    const sampleText = document.querySelector('#sampleText');
    sampleText.style.fontFamily = output;
}
