# Tic-Tac-Toe Game With Mini-Max Algorithm

> [!IMPORTANT]
> ### This is a game play shows how the game work.
>![Game Play](https://media.giphy.com/media/fKzZs916Jqo1kSGqVt/giphy.gif)

<hr>

- I used `JavaFX` to create the game UI.

- I add some comments to show how the logic and the design work.

- The game is over when all fields are taken, or one player has three in a row (horizontally, vertically or diagonally).

- I used `MiniMax` Algorithm to make the computer play and get the best move every time.

- The Algorithm work by searching the tree of all possible moves and choosing the move that leads to the best score.

- The score is calculated by the `MiniMax` function which returns the best score for the current player.

- If the computer plays then we try to maximize the score, and minimize it otherwise.

- I used **Recursion** and **Backtracking** to get the best move and undo the move after calculating it, then choose the best cell to play.

- You can find the source code in `src` directory


## 🚀&nbsp; Installation
1. Clone the repo

2. Run `./mvnw clean install` command.

3. Run the `./mvnw exec:java -Dexec.mainClass="com.tictactoe.Main"` command.