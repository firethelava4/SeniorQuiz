const questions = [
    {
        question: "What should you do if you receive an email asking for your password?",
        options: [
            "Reply with your password to verify your account.",
            "Ignore it or report it as phishing.",
            "Click the link and follow the instructions.",
            "Forward it to your contacts."
        ],
        answer: 1
    },
    {
        question: "What is the safest way to create a password?",
        options: [
            "Use your birthdate or name.",
            "Use a random combination of letters, numbers, and symbols.",
            "Use the same password for all accounts.",
            "Write it on a sticky note on your monitor."
        ],
        answer: 1
    },
    {
        question: "What does 'HTTPS' in a web address indicate?",
        options: [
            "The website is safe and secure.",
            "The website is free to access.",
            "The website is fake.",
            "The website requires a login."
        ],
        answer: 0
    }
];

let currentQuestionIndex = 0;
let score = 0;

function loadQuestion() {
    const quizDiv = document.getElementById("quiz");
    const question = questions[currentQuestionIndex];
    quizDiv.innerHTML = `
        <div class="question">${question.question}</div>
        ${question.options
            .map((option, index) => `<button class="option" onclick="checkAnswer(${index})">${option}</button>`)
            .join("")}
    `;
}

function checkAnswer(selectedOption) {
    const question = questions[currentQuestionIndex];
    const resultDiv = document.getElementById("result");

    if (selectedOption === question.answer) {
        score++;
        resultDiv.textContent = "Correct!";
        resultDiv.style.color = "green";
    } else {
        resultDiv.textContent = "Wrong! Try to stay vigilant.";
        resultDiv.style.color = "red";
    }

    // Disable buttons after answering
    document.querySelectorAll(".option").forEach(btn => (btn.disabled = true));
}

function nextQuestion() {
    const resultDiv = document.getElementById("result");
    resultDiv.textContent = "";

    currentQuestionIndex++;
    if (currentQuestionIndex < questions.length) {
        loadQuestion();
    } else {
        document.getElementById("quiz").innerHTML = `<div>Your score is ${score} out of ${questions.length}!</div>`;
        document.getElementById("next-btn").style.display = "none";
    }
}

// Initialize the quiz
loadQuestion();
