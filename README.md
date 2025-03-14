# **AI-Powered Chatbot for Mental Health Support**


## **📌 Project Description**
This project is an **AI-driven chatbot** designed to provide **mental health support** using **advanced NLP techniques**. The chatbot leverages **sentiment analysis, context awareness**, and **personalized responses** to help users manage emotions such as stress, anxiety, and depression.

It integrates **Gemini API** to enhance response generation, ensuring **empathetic and meaningful conversations**.

---

## **🚀 Features**

✔️ **Real-time Sentiment Analysis** – Detects user emotions and responds accordingly.  
✔️ **Context Awareness** – Understands and remembers user inputs within a conversation.  
✔️ **Predefined & AI-Powered Responses** – Uses predefined responses for common emotions and **Gemini API** for dynamic conversations.  
✔️ **Mental Health Support Focus** – Provides advice, stress relief techniques, and encouragement.  
✔️ **Light & Dark Mode UI** – Customizable chat interface with an elegant design.  
✔️ **Database Integration** – Stores chat history using **SQLite**.  
✔️ **Secure & Private** – No user authentication required, ensuring anonymity.  
✔️ **Crisis Support** – Provides **helpline suggestions** for users in distress.

---

## **📂 Project Structure**

```
AI-Chatbot/
│── src/
│   ├── main/
│   │   ├── java/com/chatbot/
│   │   │   ├── controller/       # Handles API requests
│   │   │   ├── service/          # Chatbot logic, NLP, Gemini API integration
│   │   │   ├── model/            # Data models (User, Message)
│   │   │   ├── repository/       # Database handling
│   ├── resources/
│   │   ├── application.properties  # Configuration settings
│── static/
│   ├── index.html   # Chatbot UI
│   ├── style.css    # Frontend styling
│   ├── script.js    # Chat functionality
│── pom.xml          # Maven dependencies
│── README.md        # Project documentation
```

---

## **🛠️ Technologies Used**

### **Backend (Spring Boot & AI)**
- **Spring Boot (Java)** – REST API for chatbot responses
- **SQLite** – Stores user messages
- **Gemini API** – Generates **context-aware mental health responses**
- **Stanford CoreNLP** – Sentiment analysis

### **Frontend (Web Interface)**
- **HTML, CSS, JavaScript** – **Interactive chatbot UI**
- **Dark Mode & Responsive Design**

---

## **⚙️ Installation & Setup**

### **🔹 Prerequisites**
1️⃣ **Java 17+** installed  
2️⃣ **Maven** installed (`mvn -version` to check)  
3️⃣ **API Key for Gemini** from [Google AI](https://ai.google.dev/)

### **🔹 Steps to Run the Chatbot**

#### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/your-username/mental-health-chatbot.git
cd mental-health-chatbot
```

#### **2️⃣ Configure API Key**
Set your **Gemini API Key** in `application.properties`:
```properties
gemini.api.key=YOUR_GEMINI_API_KEY
```

#### **3️⃣ Run the Application**
```bash
mvn spring-boot:run
```
💡 The chatbot will start at **`http://localhost:8080`**

#### **4️⃣ Open the Chatbot UI**
Open `index.html` in a browser and start chatting!

---

## **📝 API Endpoints**

| Method | Endpoint | Description |
|--------|----------|-------------|
| **POST** | `/api/chat/send` | Send a message to the chatbot |
| **GET** | `/api/chat/history` | Fetch previous user messages |

---

## **📊 Example Conversations**

| **User Input** | **Bot Response** |
|---------------|-----------------|
| "I'm feeling stressed." | "Take a deep breath. Try mindfulness. I'm here for you. 💙" |
| "I need help." | "If it's an emergency, please reach out to a mental health professional. You're not alone!" |
| "Can you generate a program?" | "I focus on mental health. Let’s talk about how you’re feeling." |

---

## **🛠️ Future Enhancements**

🔹 **Voice Chat Support** – Allow users to **speak** instead of typing  
🔹 **Deeper AI Analysis** – Improve NLP for **better sentiment understanding**  
🔹 **User Emotion Tracking** – Suggest long-term **mental health strategies**

---

## **🤝 Contributing**
Want to improve this chatbot? Feel free to **fork, modify, and contribute!** 🚀

---

## **📝 License**
This project is **open-source** under the **MIT License**.

---

🎯 **Stay strong! Your mental health matters!** 💙