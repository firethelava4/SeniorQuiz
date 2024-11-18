// Select elements
const quizContainer = document.getElementById("quiz");
const resultContainer = document.getElementById("result");
const nextButton = document.getElementById("next");

let currentQuestion = 0;
let score = 0;

// Highlight answer logic
function highlightAnswer(selected, correctOptionIndex) {
    const options = document.querySelectorAll(".option");
    options.forEach((option, index) => {
        if (index === correctOptionIndex) {
            option.classList.add("correct"); // Highlight correct answer in green
        } else if (option === selected) {
            option.classList.add("incorrect"); // Highlight wrong selection in red
        }
        option.classList.remove("hover"); // Disable hover effects
        option.style.pointerEvents = "none"; // Disable clicks on all options
    });
}

// Question logic
function showQuestion() {
    const question = questions[currentQuestion];
    quizContainer.innerHTML = `
        <div class="question">${question.question}</div>
        ${question.options
            .map(
                (option, index) =>
                    `<div class="option" onclick="checkAnswer(${index})">${option}</div>`
            )
            .join("")}
    `;
}

// Check the answer
function checkAnswer(selectedOptionIndex) {
    const question = questions[currentQuestion];
    const correctOptionIndex = question.answer;

    if (selectedOptionIndex === correctOptionIndex) {
        score++;
    }
    highlightAnswer(
        document.querySelectorAll(".option")[selectedOptionIndex],
        correctOptionIndex
    );
}

// Show next question
nextButton.addEventListener("click", () => {
    currentQuestion++;
    if (currentQuestion < questions.length) {
        showQuestion();
    } else {
        quizContainer.innerHTML = `<h2>Quiz Over! Your score: ${score}/${questions.length}</h2>`;
        nextButton.style.display = "none"; // Hide next button
    }
});

// Start quiz
showQuestion();
