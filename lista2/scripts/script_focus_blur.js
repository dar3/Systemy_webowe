'use strict'
const phoneInput = document.getElementById('phone');
const emailInput = document.getElementById('email');
const helpPhone = document.getElementById('helpPhone');
const helpEmail = document.getElementById('helpEmail');
const form = document.getElementById('personal-data-form');

// Toggle hints
    // phone
phoneInput.addEventListener('focus', () => {
    helpPhone.style.display = 'inline';
});

phoneInput.addEventListener('blur', () => {
    helpPhone.style.display = 'none';
});
    // email
emailInput.addEventListener('focus', () => {
    helpEmail.style.display = 'inline';
});

emailInput.addEventListener('blur', () => {
    helpEmail.style.display = 'none';
});

// Submit confirmation
form.addEventListener('submit', (event) => {
    const confirmSubmit = confirm('Czy na pewno chcesz wysłać formularz?');
    if (!confirmSubmit) {
        event.preventDefault();
    }
});

// Reset confirmation
form.addEventListener('reset', (event) => {
    const confirmReset = confirm('Czy na pewno chcesz zresetować formularz?');
    if (!confirmReset) {
        event.preventDefault();
    }
});