# Calculator

A simple desktop calculator built with Java Swing.

## Features

- Basic operations: addition, subtraction, multiplication, division
- Clear (AC), sign toggle (+/-), and percent (%) buttons
- Removes unnecessary trailing zeros from results (e.g. `5.0` shows as `5`)

## Built With

- Java
- Swing (`JFrame`, `JButton`, `JLabel`, `JPanel`)
- IntelliJ IDEA

## How to Run

1. Clone the repository:
```
   git clone https://github.com/NotRobbert/calc.git
```
2. Open the project in IntelliJ IDEA (or any Java IDE).
3. Run `Main.java`.

## What I Learned

- Building a GUI with Swing layouts and event listeners
- Comparing strings correctly with `.equals()` instead of `==`
- Using `switch` and lambdas to simplify code

## Possible Improvements

- Keyboard input support
- More scientific buttons
- Handling division by zero
- Chaining operations (e.g. `2 + 3 + 4`)
