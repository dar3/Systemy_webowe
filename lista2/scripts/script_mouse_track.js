const trackingArea = document.getElementById('trackingArea');

trackingArea.addEventListener('mousemove', (event) => {
    console.log(`Cursor movement detected: clientX=${event.clientX}, clientY=${event.clientY}, screenX=${event.screenX}, screenY=${event.screenY}`);
});

trackingArea.addEventListener('mousedown', (event) => {
    console.log(`Mouse click detected: altKey=${event.altKey}, ctrlKey=${event.ctrlKey}, shiftKey=${event.shiftKey}`);
});

trackingArea.addEventListener('mouseover', () => {
    console.log('Cursor entered the area');
});

trackingArea.addEventListener('mouseout', () => {
    console.log('Cursor left the area');
});

document.addEventListener('keydown', (event) => {
    console.log(`Key Code: ${event.keyCode}`);
});