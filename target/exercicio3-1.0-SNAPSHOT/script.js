// Função para exibir mensagem temporária
function showMessage(text, tipo) {
    const msgDiv = document.createElement('div');
    msgDiv.className = `message ${tipo}`; // 'success' ou 'error'
    msgDiv.textContent = text;

    // Adiciona ao body ou a um container específico
    document.body.appendChild(msgDiv);

    // Animação de fade out depois de 3 segundos
    setTimeout(() => {
        msgDiv.style.opacity = '0';
        setTimeout(() => msgDiv.remove(), 500);
    }, 3000);
}

// Exemplo de uso:
// Ao carregar a página, verifica se a URL tem um parâmetro de status vindo do Servlet
document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');

    if(status === 'sucesso') {
        showMessage('Evento cadastrado com sucesso!', 'success');
    } else if(status === 'removido') {
        showMessage('Evento removido com sucesso!', 'success');
    } else if(status === 'erro') {
        showMessage('Ocorreu um erro. Tente novamente.', 'error');
    }
});
