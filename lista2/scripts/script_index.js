let globalCounter = 0;

function showMessage() {
    window.alert("Welcome to our webpage!");
    document.writeln("Alert");
}

function calculateSum() {
    const num1 = parseFloat(window.prompt("First number:"));
    const num2 = parseFloat(window.prompt("Second number:"));
    if (!isNaN(num1) && !isNaN(num2)) {
        const sum = num1 + num2;
        document.getElementById("div").innerHTML = `Sum: ${sum}`;
    } else {
        window.alert("These are not numbers!");
    }
}

function generateRandomNumber() {
    const randomNumber = Math.floor(Math.random() * 100) + 1;
    window.alert(`Generated number: ${randomNumber}`);
}

function showDynamicText() {
    globalCounter++;
    window.alert(`You clicked it ${globalCounter} times!`);
}

function runLoop() {
    let result = "";
    for (let i = 1; i <= 5; i++) {
        result += `Iteration ${i}\n`;
    }
    window.alert(result);
}

document.getElementById("greeting").addEventListener("contextmenu", showMessage);
document.getElementById("greeting").addEventListener("click", calculateSum);
document.getElementById("history").addEventListener("click", generateRandomNumber);
document.getElementById("stockholm").addEventListener("click", showDynamicText);
document.getElementById("run_loop").addEventListener("click", runLoop);


// to add: 
// button.addEventListener()
//  parse Int
// case
// while
// do while
// create a number guessing game
