import java.util.Random;
import java.util.Scanner;
public class NumberGussingGame
{
    public void meth1(){
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            int numberToGuess = random.nextInt(1000) + 1;
            int attempts=0;
            int guess=0;
            while (guess != numberToGuess) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + numberToGuess + " in " + attempts + " attempts.");
                }
            }
            scanner.close();
        }
    public static void main(String[] args) {
     System.out.println("Start");
        NumberGussingGame obj = new NumberGussingGame();
        //new NumberGussingGame .meth1();
        obj.meth1();
        System.out.println("Ends");

    }

}
