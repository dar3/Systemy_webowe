

const randomNumber = Math.floor(Math.random() * 100) + 1;
let attemptsLeft = 3; 
document.getElementById("attempts").innerText = attemptsLeft;


function checkGuess() {
    const userGuess = parseInt(document.getElementById("guess").value);
    const message = document.getElementById("message");

    if (isNaN(userGuess) || userGuess < 1 || userGuess > 100) {
        message.textContent = "Podaj liczbę z zakresu 1-100!";
        return;
    }

    attemptsLeft--; 
    document.getElementById("attempts").innerText = attemptsLeft;

    if (userGuess === randomNumber) {
        message.innerHTML = `<strong>Gratulacje! Odgadłeś liczbę ${randomNumber}!</strong>`;
        disableGame();
    } else if (attemptsLeft === 0) {
        message.innerHTML = `<strong>Przegrałeś! Wylosowana liczba to ${randomNumber}.</strong>`;
        disableGame();
    } else {
        message.textContent = `Twoja liczba jest za ${userGuess < randomNumber ? "mała" : "duża"}. Spróbuj ponownie.`;
    }
}


function disableGame() {
    document.getElementById("guess").disabled = true;
    document.querySelector("button").disabled = true;
}






// function Guesser(a){

// let x = document.getElementById("myNumber").value;

// if(x>10){
//     document.getElementById("demo").innerHTML = x;
// }


// document.getElementById("demo").innerHTML = x;
// document.getElementById("clickMe").onclick = doFunction;

