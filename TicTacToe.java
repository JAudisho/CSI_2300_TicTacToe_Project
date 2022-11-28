import java.util.Random;
import java.util.Scanner;

public class App {

    static int playerScore = 0;
    static int computerScore = 0;
    static Scanner input = new Scanner(System.in);

public static void main(String [] args){

    //2D Array with three rows that are represented by dashes, vertical bars, and spaces
    char [][] gameBoard = {{'_','|','_','|','_'},{'_', '|', '_','|','_'},{' ','|',' ','|',' '}};

    OutputBoard(gameBoard);
    boolean gameOver = false;
    boolean playAgain = true;

  while(playAgain) {
      //Checks to see if the game is actually over
      while (!gameOver) {
          System.out.println("Welcome to Tic Tac Toe!");
          //If the game is over after the player's turn, then the while loop is broken and the game will end 
          playerTurn(gameBoard);
          gameOver = GameOver(gameBoard);
          if (gameOver) {
              break;
          }
          
          //If the game is over after the computer's turn, then the while loop is broken and the game will end
          ComputerTurn(gameBoard);
          gameOver = GameOver(gameBoard);
          if (gameOver) {
              break;
          }
      }
      System.out.println("The Player's Score is: " +playerScore);
      System.out.println("The Computer's Score is: "+ computerScore);
      System.out.println("Play again? Y/N");
      input.nextLine();
      String result = input.nextLine();
      
      switch (result){
          //If player returns "Y", playAgain will remain true and the program will loop
          case "Y":
              playAgain = true;
              System.out.println("Ok! Let's play again!");
              refreshBoard(gameBoard);
              gameOver = false;
              OutputBoard(gameBoard);
              break;
          //If player returns "N", playAgain will become false and the program will end
          case "N":
              playAgain = false;
              System.out.println("Thank you for playing!");
              break;
          default:
              break;
      }

  }
}

//Prints out all characters and makes sure they're not apart of the same line

//A method that prints out our 2D char array, utilized by two for loops to do this
public static void OutputBoard(char [][] gameBoard){

    //For each row in the 2D Array
    for(char[] row : gameBoard){
        //For each char in the row
        for( char c : row){
            //Print char
            System.out.print(c);
        }
        //Creates another line once it's done 
        System.out.println();
    }
}

//Accepts an integer for the position (1-9) and integer for the player (1-2) and the gameboard itself
public static void ChangeBoard( int position, int player, char [][] gameBoard){

        char character;
        //If statement that will change upon the player integer that is inputted into the method
        //If the player intefer is equal to one, then we will use X to represent the player
        if(player==1){
            character = 'X';
            //If the player integer is not one or equal to one, then we will use O to represent the computer
        }else{
            character = 'O';
        }

        switch (position){
            //Depending on what number was entered the corresponding indencies will change based on the character
            case 1:
                gameBoard[0][0] = character;
                OutputBoard(gameBoard);
                break;
            //For example, if the player or computer passes 2, then the positioh [0][2] should update X or O so on and so forth
            case 2:
                gameBoard[0][2] = character;
                OutputBoard(gameBoard);
                break;
            case 3:
                gameBoard[0][4] = character;
                OutputBoard(gameBoard);
                break;
            case 4:
                gameBoard[1][0] = character;
                OutputBoard(gameBoard);
                break;
            case 5:
                gameBoard[1][2] = character;
                OutputBoard(gameBoard);
                break;
            case 6:
                gameBoard[1][4] = character;
                OutputBoard(gameBoard);
                break;
            case 7:
                gameBoard[2][0] = character;
                OutputBoard(gameBoard);
                break;
            case 8:
                gameBoard[2][2] = character;
                OutputBoard(gameBoard);
                break;
            case 9:
                gameBoard[2][4] = character;
                OutputBoard(gameBoard);
                break;
            default:
                break;

        }

}

//Scanner class saves the input from the user and passes the answer to our gameboard method, then we'll print the board out
public static void playerTurn(char[][] gameBoard){
    //Prompts the player with directions to get their input
    System.out.println("Please make your move. (1-9)");


    int move = input.nextInt();
    //Tests the input that the player gives us against this method
    boolean result = ValidMove(move,gameBoard);
    //If it is not, then we will prompt the player to gives us the correct move using a while loop
    while(!result){
        System.out.println("Invalid Move, Try again.");
        move = input.nextInt();
        result = ValidMove(move,gameBoard);
    }
    //If it returns true, then it's a valid move and the board will be updated
    System.out.println("The Player moved at position " + move + "!");
    ChangeBoard(move,1,gameBoard);


}


public static boolean ValidMove(int move, char[][] gameboard){

    switch (move){
        case 1:
            if(gameboard[0][0] == '_'){
                return true;
            } else{
                return false;
            }
        case 2:
            if(gameboard[0][2] == '_'){
                return true;
            } else{
                return false;
            }
        case 3:
            if(gameboard[0][4] == '_'){
                return true;
            } else{
                return false;
            }

        case 4:
            if(gameboard[1][0] == '_'){
                return true;
            } else{
                return false;
            }
        case 5:
            if(gameboard[1][2] == '_'){
                return true;
            } else{
                return false;
            }
        case 6:
            if(gameboard[1][4] == '_'){
                return true;
            } else{
                return false;
            }
        case 7:
            if(gameboard[2][0] == ' '){
                return true;
            } else{
                return false;
            }
        case 8:
            if(gameboard[2][2] == ' '){
                return true;
            } else{
                return false;
            }
        case 9:
            if(gameboard[2][4] == ' '){
                return true;
            } else{
                return false;
            }

        default:
            return false;
    }

}

//Creates a random number generator that utilizes the same code we used for the player method
public static void ComputerTurn(char [][] gameBoard){

    Random rand = new Random();
    int move = rand.nextInt(9)+1;

    boolean result = ValidMove(move,gameBoard);

    while(!result){
        move = rand.nextInt(9)+1;
        result = ValidMove(move, gameBoard);
    }

    System.out.println("Computer moved at position " + move + "!");
    ChangeBoard(move,2,gameBoard);
}

//Checks to see if the rows, columns, or diagonals are equal or if it's a tie.
public static boolean GameOver(char [][] gameboard){

    //Horizontal Win
    //If position one is equal to position two which is equal to position three; if all of those are equal to X, then player 1 wins
    if(gameboard[0][0] == 'X'&& gameboard[0][2] == 'X' && gameboard [0][4] == 'X' ){
        System.out.println("The Player wins!");
        //Incrementing player score to keep track of the score
        playerScore++;
        return true;
    }

    //If position one, two, and three are all equal to O then the computer wins
    if(gameboard[0][0] == 'O'&& gameboard[0][2] == 'O' && gameboard [0][4] == 'O' ){
        System.out.println("The Computer wins!");
        //Incrementing computer score to keep track of the score
        computerScore++;
        return true;
    }
    if(gameboard[1][0] == 'X'&& gameboard[1][2] == 'X' && gameboard [1][4] == 'X' ){
        System.out.println("Player wins!");
        playerScore++;
        return true;
    }
    if(gameboard[1][0] == 'O'&& gameboard[1][2] == 'O' && gameboard [1][4] == 'O' ){
        System.out.println("The Computer wins!");
        computerScore++;
        return true;
    }
    if(gameboard[2][0] == 'X'&& gameboard[2][2] == 'X' && gameboard [2][4] == 'X' ){
        System.out.println("Player wins!");
        playerScore++;
        return true;
    }
    if(gameboard[2][0] == 'O'&& gameboard[2][2] == 'O' && gameboard [2][4] == 'O' ) {
        System.out.println("The Computer wins!");
        computerScore++;
        return true;
    }

        //Vertical Wins
        if(gameboard[0][0] == 'X'&& gameboard[1][0] == 'X' && gameboard [2][0] == 'X' ){
            System.out.println("Player wins!");
            playerScore++;
            return true;
        }
        if(gameboard[0][0] == 'O'&& gameboard[1][0] == 'O' && gameboard [2][0] == 'O' ){
            System.out.println("Computer wins!");
            computerScore++;
            return true;
        }

        if(gameboard[0][2] == 'X'&& gameboard[1][2] == 'X' && gameboard [2][2] == 'X' ){
            System.out.println("Player wins!");
            playerScore++;
            return true;
        }
        if(gameboard[0][2] == 'O'&& gameboard[1][2] == 'O' && gameboard [2][2] == 'O' ){
            System.out.println("Computer wins!");
            computerScore++;
            return true;
        }

        if(gameboard[0][4] == 'X'&& gameboard[1][4] == 'X' && gameboard [2][4] == 'X' ){
            System.out.println("Player wins!");
            playerScore++;
            return true;
        }
        if(gameboard[0][4] == 'O'&& gameboard[1][4] == 'O' && gameboard [2][4] == 'O' ){
            System.out.println("Computer wins!");
            computerScore++;
            return true;
        }

        //Diagonal Wins
        if(gameboard[0][0] == 'X'&& gameboard[1][2] == 'X' && gameboard [2][4] == 'X' ){
            System.out.println("Player wins!");
            playerScore++;
            return true;
        }
        if(gameboard[0][0] == 'O'&& gameboard[1][2] == 'O' && gameboard [2][4] == 'O' ){
            System.out.println("Computer wins!");
            computerScore++;
            return true;
        }

        if(gameboard[2][0] == 'X'&& gameboard[1][2] == 'X' && gameboard [0][4] == 'X' ){
            System.out.println("Player wins!");
            playerScore++;
            return true;
        }
        if(gameboard[2][0] == 'O'&& gameboard[1][2] == 'O' && gameboard [0][4] == 'O' ){
            System.out.println("Computer wins!");
            computerScore++;
            return true;
        }
        
        //If you check every condition before this condition, and that not every condition is equal and to its empty state then it's a tie
        if(gameboard[0][0] != '_' && gameboard[0][2] != '_' && gameboard[0][4] != '_' && gameboard[1][0] !='_'&&
            gameboard[1][2] != '_' && gameboard[1][4] != '_' && gameboard[2][0] != ' ' && gameboard[2][2] != ' ' && gameboard[2][4] != ' '){
            System.out.println("Its a draw!");
            return true;
        }




return false;}

//Makes board return back to its empty state
public static void refreshBoard(char [][] gameBoard){
    gameBoard[0][0] = '_';
    gameBoard[0][2] = '_';
    gameBoard[0][4] = '_';
    gameBoard[1][0] = '_';
    gameBoard[1][2] = '_';
    gameBoard[1][4] = '_';
    gameBoard[2][0] = ' ';
    gameBoard[2][2] = ' ';
    gameBoard[2][4] = ' ';

}
}