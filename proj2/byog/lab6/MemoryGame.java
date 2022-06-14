package byog.lab6;

import byog.TileEngine.TETile;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.security.DrbgParameters;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
/*
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }
*/

        long seed = Long.parseLong("1000");
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String randomString = "";
        for (int i = 0; i < n; i += 1) {
            char randomChar = CHARACTERS[rand.nextInt(CHARACTERS.length)];
            randomString += randomChar;
        }
        return randomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen

        // Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Verdana", Font.BOLD, 30   );
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width / 2, height / 2, s);


        // If game is not over, display relevant game information at the top of the screen
        if (!gameOver) {
            Font UIfont = new Font("Verdana", Font.BOLD, 20);
            StdDraw.setFont(UIfont);
            StdDraw.textLeft(1, height - 1, "Round: " + round);
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)]);
            StdDraw.text(width / 2, height - 1, String.valueOf(StdDraw.mouseX()));
            StdDraw.line(0,height - 2, width, height - 2);
        }

        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i+1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String input = "";
        drawFrame(input);

        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char in = StdDraw.nextKeyTyped();
            if (in == '\b' && input.length() > 0) {
                input = input.substring(0,input.length() - 1);
            } else {
                input += String.valueOf(in);
            }
            drawFrame(input);

        }
        StdDraw.pause(1000);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        //TODO: Establish Game loop
        while (!gameOver) {
            drawFrame("Round:" + round);
            StdDraw.pause(1500);

            String randomString = generateRandomString(round);
            flashSequence(randomString);
            playerTurn = true;
            String input = solicitNCharsInput(round);

            playerTurn = false;

            if (input.equals(randomString)) {
                round += 1;
            } else {
                drawFrame("Game Over! You made it to round:" + round);
                gameOver = true;
            }
        }
    }

}
