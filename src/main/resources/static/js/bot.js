document.getElementById('chat-icon').addEventListener('click', function() {
  const chatbotCard = document.querySelector('.chatbot-card');
  chatbotCard.classList.toggle('show');
});

document.getElementById('user-input').addEventListener('keydown', async function(event) {
  if (event.key === 'Enter') {
    await sendMessage();
  }
});

document.getElementById('send-button').addEventListener('click', async function() {
  await sendMessage();
});

async function sendMessage() {
  const userInput = document.getElementById('user-input').value;
  if (userInput) {
    const messagesDiv = document.getElementById('messages');
    messagesDiv.innerHTML += `<div><strong>Bạn:</strong> ${userInput}</div>`;
    document.getElementById('user-input').value = '';

    const response = await fetch('/api/chat/send', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ message: userInput }),
    });

    const data = await response.json();
    if (data.response) {
      messagesDiv.innerHTML += `<div><strong>Chatbot:</strong> ${data.response}</div>`;
    }
  }
}
