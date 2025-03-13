function sendMessage() {
    let inputField = document.getElementById("user-input");
    let userMessage = inputField.value.trim();
    if (userMessage === "") return;

    appendMessage(userMessage, "user");

    fetch("http://localhost:8080/api/chat/send?userId=123", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ message: userMessage })
    })
    .then(response => response.json())
    .then(data => appendMessage(data.botResponse, "bot"))
    .catch(() => appendMessage("❌ Error: Unable to connect to server.", "bot"));

    inputField.value = "";
}
