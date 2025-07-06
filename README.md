# kotlin-lotto

## Project Overview

This is a **Kotlin** project for building a simple lottery ticket machine.  
The application allows users to purchase lotto tickets, enter winning numbers (6 digits separated by comma) and a bonus number, generate the appropriate number of Lotto tickets and check winning results.

---

## Features

- Accepts user input for purchase amount and issue lotto tickets.
- Each ticket costs **1,000 KRW** and contains **6 unique random numbers** between **1** and **45**.
- Display the total number of tickets purchased.
- Display each ticket in a new line.
- Accepts **winning numbers** (6 unique random numbers between 1 and 45) and a **bonus number** (must be different from the winning numbers) from the user.
- Matches purchased tickets against the winning numbers and determines the winning rank:
  - 1st Prize: 6 matches — **2,000,000,000 KRW**
  - 2nd Prize: 5 matches + bonus — **30,000,000 KRW**
  - 3rd Prize: 5 matches — **1,500,000 KRW**
  - 4th Prize: 4 matches — **50,000 KRW**
  - 5th Prize: 3 matches — **5,000 KRW**
- Displays lotto result statistics (number of winning tickets per rank).
- Calculates and displays the **return rate** (rounded to two decimal places).
- Handles invalid input and throw on IllegalArgumentException with specific error messages.

---

## Programming Requirements

- All features must be implemented using **TDD**, and each must have corresponding unit tests (excluding UI logic).
- Separate core logic from UI logic.
- Use of dedicated classes such as InputView and ResultView for UI-related code. 
- Limit indentation depth to 1 (must not exceed 2). 
- Keep each function/method under 10 lines. 
- Ensure each function does only one thing. 
- Use an Enum class to model the prize ranks. 
- Use a **First-Class Collection** to encapsulate collections meaningfully.

---

## Testing

- All business logic is unit tested with **JUnit 5** and **AssertJ**.
- Exception handling tests ensure invalid inputs are properly managed.

---

## Example Usage

Please enter the purchase amount. <br>
14000 <br>
You have purchased 14 tickets. <br>
[8, 21, 23, 41, 42, 43] <br>
[3, 5, 11, 16, 32, 38] <br>
[7, 11, 16, 35, 36, 44] <br>
[1, 8, 11, 31, 41, 42] <br>
[13, 14, 16, 38, 42, 45] <br>
[7, 11, 30, 40, 42, 43] <br>
[2, 13, 22, 32, 38, 45] <br>
[23, 25, 33, 36, 39, 41] <br>
[1, 3, 5, 14, 22, 45] <br>
[5, 9, 38, 41, 43, 44] <br>
[2, 8, 9, 18, 19, 21] <br>
[13, 14, 18, 21, 23, 35] <br>
[17, 21, 29, 37, 42, 45] <br>
[3, 8, 27, 30, 35, 44] 

Please enter last week’s winning numbers. <br>
1, 2, 3, 4, 5, 6 <br>
Please enter the bonus number. <br>
7

Winning Statistics <br>
<span> ------------------ </span> <br>
3 Matches (5,000 KRW) - 1 tickets <br>
4 Matches (50,000 KRW) - 0 tickets <br>
5 Matches (1,500,000 KRW) - 0 tickets <br>
5 Matches + Bonus Ball (30,000,000 KRW) - 0 tickets <br>
6 Matches (2,000,000,000 KRW) - 0 tickets <br>
Total return rate is 0.35 (A rate below 1 means a loss) <br>

---

## How to Run

1. Clone the repository.
2. Open with **IntelliJ IDEA**.
3. Run the `Application.kt` file to start the program.
4. Follow the prompts to play!