// Pega o botão pelo ID
const btnDark = document.getElementById('btnDark');

// Quando clicar no botão
btnDark.addEventListener('click', function() {
    // Alterna o tema dark no body
    document.body.classList.toggle('dark-mode');
    
    // Muda o emoji do botão
    if (document.body.classList.contains('dark-mode')) {
        btnDark.textContent = '☀️'; // Sol quando está escuro
    } else {
        btnDark.textContent = '🌙'; // Lua quando está claro
    }
});
