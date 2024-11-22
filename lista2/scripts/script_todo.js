function startApp() {
    const consoleDiv = document.getElementById("console");
    let tasks = []; 
    let option;

    function logMessage(message) {
        consoleDiv.innerHTML += `<p>${message}</p>`;
    }

    
    while (true) {
        option = prompt(
            "Wybierz opcję:\n1. Dodaj zadanie\n2. Wyświetl zadania\n3. Usuń zadanie\n4. Wyjdź"
        );

       
        option = parseInt(option);

        switch (option) {
            case 1: 
                let newTask;
                do {
                    newTask = prompt("Wpisz nowe zadanie:");
                } while (!newTask || newTask.trim() === ""); 
                tasks.push(newTask.trim());
                logMessage(`Dodano zadanie: ${newTask}`);
                break;

            case 2: 
                if (tasks.length === 0) {
                    logMessage("Lista zadań jest pusta.");
                } else {
                    logMessage("Twoje zadania:");
                    tasks.forEach((task, index) => logMessage(`${index + 1}. ${task}`));
                }
                break;

            case 3: 
                if (tasks.length === 0) {
                    logMessage("Nie ma zadań do usunięcia.");
                } else {
                    let taskToRemove;
                    do {
                        taskToRemove = prompt(
                            `Podaj numer zadania do usunięcia (1-${tasks.length}):`
                        );
                        taskToRemove = parseInt(taskToRemove);
                    } while (
                        isNaN(taskToRemove) ||
                        taskToRemove < 1 ||
                        taskToRemove > tasks.length
                    );

                    const removedTask = tasks.splice(taskToRemove - 1, 1);
                    logMessage(`Usunięto zadanie: ${removedTask}`);
                }
                break;

            case 4: 
                logMessage("Zakończono zarządzanie zadaniami.");
                return;

            default: 
                logMessage("Nieprawidłowa opcja. Spróbuj ponownie.");
        }
    }
}
