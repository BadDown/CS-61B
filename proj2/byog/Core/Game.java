package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    public static final int canvasWIDTH = 768;
    public static final int canvasHEIGHT = 1024;

    public static boolean gameOver = false;


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        char firstChar;
        drawMenu();
        firstChar = getFirstChar();
        TETile[][] world;
        switch (firstChar) {
            case 'n':
                Long Seed = getSeed();
                world = generateWorld(Seed);
                generateInitWorld(world);
                startGame(world, null);
                break;
            case 'l':
                world = loadWorld();
                generateInitWorld(world);
                startGame(world, null);
                break;
            case 'q':
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;

        input = input.toLowerCase();
        char firstChar = input.charAt(0);
        switch (firstChar) {
            case 'n':
                long Seed = getInputSeed(input);
                input = input.substring(input.indexOf('s' + 1, input.length()));
                finalWorldFrame = generateWorld(Seed);
                generateInitWorld(finalWorldFrame);
                startGame(finalWorldFrame, input);
                break;
            case 'l':
                finalWorldFrame = loadWorld();
                generateInitWorld(finalWorldFrame);
                break;
            case 'q':
                System.exit(0);
                break;
            default:
                System.out.println("input is error !");
                break;
        }

        return finalWorldFrame;
    }

    private void drawMenu() {
        InitializeCanvas(canvasWIDTH, canvasHEIGHT);

        Font bigFont = new Font("Verdana", Font.BOLD, 22);
        StdDraw.setFont(bigFont);
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT * 2 / 3, "CS61B: THE GAME");
        Font smallFont = new Font("Verdana", Font.PLAIN, 16);
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2, "Load Game (L)");
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2 + 40, "New Game (N)");
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2 - 40, "Quit (Q)");

        StdDraw.show();
    }

    private void InitializeCanvas(int width, int height) {

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.white);
    }

    private char getFirstChar() {
        char firstChar;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            firstChar = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (firstChar == 'n' || firstChar == 'l' || firstChar == 'q') {
                return firstChar;
            }
        }
    }

    private Long getSeed() {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Verdana", Font.BOLD, 16);
        StdDraw.setFont(font);
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2, "Please input the seed and end with 'S'!");
        StdDraw.show();

        String Seed = "";
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            Character inputChar = Character.valueOf(Character.toLowerCase(StdDraw.nextKeyTyped()));
            if (inputChar.equals('s')) {
                if (Seed.isEmpty()) {
                    continue;
                } else {
                    try {
                        long returnlong = Long.valueOf(Seed);
                        return returnlong;
                    } catch (NumberFormatException e) {
                        throw e;
                    }
                }
            }
            if (Character.isDigit(inputChar)) {
                Seed = Seed + inputChar;
                Font seedFont = new Font("Verdana", Font.PLAIN, 12);
                StdDraw.setFont(seedFont);
                StdDraw.clear(Color.BLACK);
                StdDraw.text(canvasWIDTH / 2, canvasHEIGHT * 2 / 3, Seed);
                StdDraw.setFont(font);
                StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2, "Please input the seed and end with 'S'!");
                StdDraw.show();
            } else {
                Seed = "";
                Font seedFont = new Font("Verdana", Font.PLAIN, 12);
                StdDraw.setFont(seedFont);
                StdDraw.clear(Color.BLACK);
                StdDraw.text(canvasWIDTH / 2, canvasHEIGHT * 2 / 3, Seed);
                StdDraw.setFont(font);
                StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2, "Please input the seed and end with 'S'!");
                StdDraw.show();
            }
        }
    }

    private TETile[][] generateWorld(long Seed) {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        ArrayList<Position> alternatePosition = new ArrayList<>();
        ArrayList<Position> vertex;
        ArrayList<Position> wallPosition;
        Random RANDOM = new Random(Seed);
        int trynum = 10;
        vertex = Hallway.initializeWorld(world);
        Hallway.generateMaze(world, alternatePosition, RANDOM);
        Hallway.delHallway(world, vertex, RANDOM);
        for (int n = 0; n < trynum; n += 1) {
            Room.generateRoom(world, RANDOM, vertex);
        }
        wallPosition = Wall.roomWall(world);
        Wall.GenerateLockedDoor(world, wallPosition, RANDOM);
        Player.generatePlayer(vertex, world, RANDOM);
        return world;
    }

    private TETile[][] loadWorld() {
        File f = new File("./saveWorld.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] loadWorld = (TETile[][]) os.readObject();
                Player.xPos = (int) os.readObject();
                Player.yPos = (int) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new TETile[WIDTH][HEIGHT];
    }

    private void saveWorld(TETile[][] world) {
        File f = new File("./saveWorld.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
            os.writeObject(Player.xPos);
            os.writeObject(Player.yPos);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    private long getInputSeed(String input) {
        int indexS = input.indexOf('s');

        String seed = input.substring(1, indexS);

        return Long.parseLong(seed);
    }

    private void generateInitWorld(TETile[][] world) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
    }

    private void drawFrame(TETile[][] world) {
        StdDraw.clear(Color.BLACK);


        Font mazeFont = new Font("Monaco", Font.BOLD, 14);
        StdDraw.setFont(mazeFont);
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        int xOffset = 0;
        int yOffset = 0;
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y
                            + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);
            }
        }

        Font font = new Font("Verdana", Font.BOLD, 16);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        String s = mouseTile(world);
        StdDraw.text(3, world[0].length - 2, s);


        StdDraw.show();
    }

    private String mouseTile(TETile[][] world) {
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();
        String s = null;
        if (mouseX >= world.length || mouseX < 0 || mouseY >=world[0].length || mouseY < 0) {
            return s = "";
        }
        TETile t = world[mouseX][mouseY];
        if (ReturnTile.returnFloor.equals(t)) {
            s = "floor";
        } else if (ReturnTile.returnNothing.equals(t)) {
            s = "Nothing";
        } else if (ReturnTile.returnPlayer.equals(t)) {
            s = "player";
        } else if (ReturnTile.returnWall.equals(t)) {
            s = "wall";
        } else if (ReturnTile.returnLockedDoor.equals(t)) {
            s = "lockedDoor";
        } else {
            throw new IllegalStateException("Unexpected value: " + t);
        }
        return s;
    }

    private void startGame(TETile[][] world, String s) {
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();

        if (s != null) {
            char[] sinput = s.toCharArray();
            for (int n = 0; n < sinput.length; n += 1) {
                char in = sinput[n];
                in = Character.toLowerCase(in);
                if (in == 'w' || in == 's' || in == 'a' || in == 'd') {
                    Player.move(in, world);
                    //gameOver = Check.gameOver(player, world);
                } else {
                    if (in == ':' && n != sinput.length - 1) {
                        if (sinput[n + 1] == 'q') {
                            saveWorld(world);
                            System.exit(0);
                        }
                    }
                }
            }
        }

        while (!gameOver) {
            if (mouseX != (int) StdDraw.mouseX() || mouseY != (int) StdDraw.mouseY()) {
                mouseX = (int) StdDraw.mouseX();
                mouseY = (int) StdDraw.mouseY();
                drawFrame(world);
            }

           if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char in = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (in == 'w' || in == 's' || in == 'a' || in == 'd') {
                Player.move(in, world);
                //gameOver = Check.gameOver(player, world);
                drawFrame(world);
            } else {
                if (in == ':') {
                    char inin;
                    while (true) {
                        if (!StdDraw.hasNextKeyTyped()) {
                            continue;
                        }
                        inin = Character.toLowerCase(StdDraw.nextKeyTyped());
                        break;
                    }
                    if (inin == 'q') {
                        saveWorld(world);
                        System.exit(0);
                    }
                } else {
                    continue;
                }
            }
        }

        drawGameOver();
        StdDraw.pause(1500);
        System.exit(0);
    }

    private void drawGameOver() {
        InitializeCanvas(canvasWIDTH, canvasHEIGHT);

        Font bigFont = new Font("Verdana", Font.BOLD, 22);
        StdDraw.setFont(bigFont);
        StdDraw.text(canvasWIDTH / 2, canvasHEIGHT / 2, "Congralation! You win!");

        StdDraw.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playWithKeyboard();
    }
}
