package snake;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author s4330_000
 */
public class SnakeUserRun extends JFrame implements Snake, KeyListener {

    private int X;

    private int Y;

    private static final int SNAKEINIT = 5;

    private int sizeOfObstacles;

    private int numObstacles;

    private Cordinate normalSnakeFood = new Cordinate();

    private Cordinate speedSnakeFood = null;

    private Cordinate bonusSnakeFood = null;

    private Cordinate slowSnakeFood = null;

    private Cordinate snake[];

    private Cordinate[][] obstacles;

    private int board[][];

    private Image snakeBodySegment;

    private Image wall;

    private Image bonusFood;

    private Image speedFood;

    private Image slowFood;

    private Image normalFood;

    private Image backGround;

    private int snakeLength = SNAKEINIT;

    private int gameState = 1;

    private int speed;

    private int count = 0;

    private double score = 0;

    private int add = 1;

    private static int direction;

    private static final int NONE = 0;

    private static final int RIGHT = 1;

    private static final int LEFT = 2;

    private static final int UP = 3;

    private static final int DOWN = 4;

    private String s;

    private String t;

    private boolean started = false;

    private byte ticFoodTypeCount = 0;

    private int bonusFoodRate = 0;

    private boolean isInsideSwitch;

    public SnakeUserRun(boolean pIsSmallSimulator, int pSizeOfObstacles, int pNumObstacles, int pSpeed, boolean pIsSpeedFood, boolean pIsSlowFood, boolean pIsBonusFood) {

        super("Snake User Run");

        this.sizeOfObstacles = pSizeOfObstacles;

        this.numObstacles = pNumObstacles;

        this.speed = pSpeed;

        this.setUpSimulatorConfiguration(pIsSmallSimulator, pIsSpeedFood, pIsSlowFood, pIsBonusFood);

        this.setLayout(new FlowLayout());

        this.setSize(400, 400);

        this.setFocusable(true);

        this.addKeyListener(this);

        this.setLocationRelativeTo(null);

        this.setVisible(true);

        for (int i = 0; i < this.X * this.Y; i++) {

            this.snake[i] = new Cordinate();
        }

        for (int i = 0; i < this.numObstacles; i++) {

            for (int j = 0; j < this.sizeOfObstacles; j++) {

                this.obstacles[i][j] = new Cordinate();

            }
        }

        try {

            this.backGround = ImageIO.read(new File("congruent_outline.png"));

            this.snakeBodySegment = ImageIO.read(new File("dot.gif"));

            this.wall = ImageIO.read(new File("wall.png"));

            this.normalFood = ImageIO.read(new File("steak.png"));

            this.speedFood = ImageIO.read(new File("chilis.png"));

            this.bonusFood = ImageIO.read(new File("sweet_cake.png"));

            this.slowFood = ImageIO.read(new File("hamburger.png"));

            this.slowFood = ImageIO.read(new File("hamburger.png"));

        } catch (IOException ex) {

        }
    }

    public void playSound() {

        System.out.println("dslkjfsdkj");
        final AudioPlayer mgp = AudioPlayer.player;
        AudioStream bmg;
        AudioData md;

//         ContinuousAudioDataStream loop = null;
        AudioDataStream loop = null;

        try {

            bmg = new AudioStream(new FileInputStream("120.wav"));

            md = bmg.getData();

            loop = new AudioDataStream(md);

        } catch (IOException ex) {

        }

        mgp.start(loop);
        
      

    }

    private void setUpSimulatorConfiguration(boolean isSmallSimulator, boolean isSpeedFood, boolean isSlowFood, boolean isBonusFood) {

        if (isSmallSimulator) {

            this.X = this.Y = 16;

        } else {

            this.X = this.Y = 20;

        }

        if (isSpeedFood) {

            this.speedSnakeFood = new Cordinate();

        }
        if (isSlowFood) {

            this.slowSnakeFood = new Cordinate();

        }

        if (isBonusFood) {

            this.bonusSnakeFood = new Cordinate();

        }

        if (this.speed >= 50 && this.speed <= 100) {

            this.bonusFoodRate = 35;

        } else if (this.speed >= 100 && this.speed <= 150) {

            this.bonusFoodRate = 55;

        } else {

            this.bonusFoodRate = 1;

        }

        this.snake = new Cordinate[this.X * this.Y];

        this.board = new int[this.X][this.Y];

        this.obstacles = new Cordinate[this.numObstacles][1];

        for (int i = 0; i < this.numObstacles; i++) {

            this.obstacles[i] = new Cordinate[this.sizeOfObstacles];

        }

    }

    private void paintSnake() {

        Graphics g = this.getGraphics();

        this.requestFocus();

        g.drawImage(this.backGround, 10, 30, X * 10, Y * 10, this);

        g.setColor(Color.RED);

        if (started) {

            this.t = "Score " + this.score;
            g.clearRect(0, (Y * 10) + 31, (X * 10) + 100, (Y * 10) + 100);
            g.drawString(this.t, ((X * 10) / 2) - 20, (Y * 10) + 50);
        }

        if (this.gameState == 1) {

            for (int i = 0; i < SNAKEINIT; i++) {

                snake[i].setCordinate(12 - i, 8);

            }

            if (numObstacles > 0) {

                createObstacles();

            }

            this.gameState = 2;
        }

        if (this.gameState == 2 || this.gameState == 3) {
            if (!this.started) {
                this.t = "Use the key board arrows to move";
                g.drawString(t, 5, (Y * 10) + 150);
            }
            for (int x = 0; x < X; x++) {
                for (int y = 0; y < Y; y++) {

                    //SANKE BODY
                    if (board[x][y] == 1) {
                        g.drawImage(this.snakeBodySegment, x * 10 + 10, y * 10 + 30, this);
                    }
                    //SNAKE FOOD
                    if (board[x][y] == 2) {
                        g.drawImage(this.normalFood, x * 10 + 10, y * 10 + 30, this);
                    }
                    //OBSTACLES
                    if (board[x][y] == 3) {
                        g.drawImage(this.wall, x * 10 + 10, y * 10 + 30, this);
                    }
                    //BONUS FOOD
                    if (board[x][y] == 4) {
                        g.drawImage(this.bonusFood, x * 10 + 10, y * 10 + 30, this);
                    }
                    //SLOW FOOD
                    if (board[x][y] == 5) {
                        g.drawImage(this.slowFood, x * 10 + 10, y * 10 + 30, this);
                    }
                    //SPEED FOOD
                    if (board[x][y] == 6) {
                        g.drawImage(this.speedFood, x * 10 + 10, y * 10 + 30, this);
                    }

                }
            }
        }

        if (this.gameState == 3) {
            this.s = "GAME OVER";
            g.drawString(s, ((X * 10) / 2) - 20, (Y * 10) / 2);
            this.started = false;
        }
    }

    private int[] getVerCordinate(int position) {

        int x = 0;

        int y = 0;

        if (position == 0) {

            do {

                x = 2;

                y = new Random().nextInt((18 - 2) + 1) + 2;

            } while (this.board[x][y] == 3 || this.board[x][y] == 2);

        } else if (position == 1) {

            do {

                x = new Random().nextInt((18 - 2) + 1) + 2;

                y = 2;

            } while (this.board[x][y] == 3 || this.board[x][y] == 2);

        } else if (position == 2) {

            do {

                x = 2;

                y = new Random().nextInt((14 - 2) + 1) + 2;

            } while (this.board[x][y] == 3 || this.board[x][y] == 2);

        } else {

            do {

                x = new Random().nextInt((14 - 2) + 1) + 2;

                y = 2;

            } while (this.board[x][y] == 3 || this.board[x][y] == 2);

        }

        return new int[]{x, y};

    }

    private void createObstacles() {

        for (int i = 0; i < this.numObstacles; i++) {

            int obstalcesType = (int) (Math.random() * 2);

            Cordinate cordinate;

            //Vertical
            if (obstalcesType == 1) {

                int xArr[] = getVerCordinate(0);

                cordinate = new Cordinate(xArr[0], xArr[1]);

                int x = cordinate.getX();

                int y = cordinate.getY();

                for (int j = 0; j < this.sizeOfObstacles; j++) {

                    this.obstacles[i][j].setCordinate(x + j, y);

                }

                //Horizontal 
            } else {

                int xArr[] = getVerCordinate(1);

                cordinate = new Cordinate(xArr[0], xArr[1]);

                int x = cordinate.getX();

                int y = cordinate.getY();

                for (int j = 0; j < this.sizeOfObstacles; j++) {

                    this.obstacles[i][j].setCordinate(x, y + j);

                }

            }

        }
    }

    @Override
    public void run() {

        this.paintSnake();

        this.score = 0;

        for (int i = 0; i < this.X; i++) {
            for (int j = 0; j < this.Y; j++) {
                this.board[i][j] = 0;
            }
        }

        for (int i = 0; i < this.snakeLength; i++) {
            this.board[this.snake[i].getX()][this.snake[i].getY()] = 1;
        }

        for (int i = 0; i < numObstacles; i++) {

            for (int j = 0; j < sizeOfObstacles; j++) {

                this.board[this.obstacles[i][j].getX()][this.obstacles[i][j].getY()] = 3;

            }
        }

        this.normalSnakeFood.setCordinate(1, 1);

        this.board[this.normalSnakeFood.getX()][this.normalSnakeFood.getY()] = 2;

        //ini foods
        if (this.bonusSnakeFood != null) {
            this.bonusSnakeFood.setCordinate(0, 0);
            this.board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 0;
        }
        if (this.speedSnakeFood != null) {
            this.speedSnakeFood.setCordinate(0, 0);
            this.board[this.speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 0;
        }
        if (this.slowSnakeFood != null) {
            this.slowSnakeFood.setCordinate(0, 0);
            this.board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 0;
        }

        while (true) {

            if (this.started) {
                for (int i = this.snakeLength - 1; i > 0; i--) {
                    this.snake[i].copy(this.snake[i - 1]);
                }

                switch (direction) {
                    case LEFT:
                        this.snake[0].setX(this.snake[0].getX() - 1);
                        break;
                    case RIGHT:
                        this.snake[0].setX(this.snake[0].getX() + 1);
                        break;
                    case UP:
                        this.snake[0].setY(this.snake[0].getY() - 1);
                        break;
                    case DOWN:
                        this.snake[0].setY(this.snake[0].getY() + 1);
                        break;
                }

                if (this.gameState == 2) {

                    if (this.snake[0].equals(this.normalSnakeFood)) {
                        this.snake[this.snakeLength].copy(this.snake[this.snakeLength - 1]);
                        this.snakeLength++;
//                        this.playSound();
                        this.locateNormalFoodRandom();
                        this.score += this.add;
                        this.ticFoodTypeCount++;
                        System.out.println("ticFoodTypeCount: " + this.ticFoodTypeCount);
                    } else {
                        for (int i = 1; i < this.snakeLength; i++) {
                            if (this.snake[i].equals(this.snake[0])) {
                                this.gameState = 3;
                                System.out.println("The snake touched itself " + i);
                                System.out.println("snake head " + this.snake[0]);
                                System.out.println("snake touched segment " + this.snake[i]);
                            }
                        }
                    }

//                    if (this.ticFoodTypeCount >= 5) {
                    if (!this.isInsideSwitch && this.ticFoodTypeCount >= 5) {

                        int randomVal = (int) (Math.random() * 4);

//                        System.out.println("Random Value For Bonus Food: " + randomVal);
                        int sizeOfMaze = (this.X * this.Y);
                        sizeOfMaze -= 30;

                        switch (randomVal) {
                            case 0:
                            case 1:
                                if (this.bonusSnakeFood != null) {
                                    if (/*!this.activeBonusFood && */this.score < sizeOfMaze) {
                                        this.locateBonusFoodRandom();
                                        this.board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 4;
//                                        this.activeBonusFood = true;
                                        this.isInsideSwitch = true;
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                sleepComponent();
                                                bonusSnakeFood.setCordinate(0, 0);
                                                board[bonusSnakeFood.getX()][bonusSnakeFood.getY()] = 0;
                                                snake[snakeLength].copy(snake[snakeLength - 1]);
//                                                activeBonusFood = false;
                                                ticFoodTypeCount = 0;
                                                if (isInsideSwitch) {
                                                    isInsideSwitch = false;
                                                }
                                            }
                                        }.start();
                                    }
                                }
                                break;
                            case 2:
                                if (this.speedSnakeFood != null) {
                                    if (/*!this.activeSpeedFood && */this.score < sizeOfMaze) {
                                        this.locateSpeedFoodRandom();
                                        this.board[speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 6;
//                                        this.activeSpeedFood = true;
                                        this.isInsideSwitch = true;
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                sleepComponent();
                                                speedSnakeFood.setCordinate(0, 0);
                                                board[speedSnakeFood.getX()][speedSnakeFood.getY()] = 0;
//                                                activeSpeedFood = false;
                                                if (isInsideSwitch) {
                                                    isInsideSwitch = false;
                                                }
                                            }
                                        }.start();
                                    }
                                }
                                break;
                            case 3:
                                if (this.slowSnakeFood != null) {
                                    if (/*!this.activeSlowFood &&*/this.score < sizeOfMaze) {
                                        this.locateSlowFoodRandom();
                                        this.board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 5;
//                                        this.activeSlowFood = true;
                                        this.isInsideSwitch = true;
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                sleepComponent();
                                                slowSnakeFood.setCordinate(0, 0);
                                                board[slowSnakeFood.getX()][slowSnakeFood.getY()] = 0;
//                                                activeSlowFood = false;
                                                if (isInsideSwitch) {
                                                    isInsideSwitch = false;
                                                }
                                            }
                                        }.start();
                                    }
                                }
                                break;
                            default:
                                System.out.println("no thing");
                                break;
                        }
                    }
//                    }
                    if (this.bonusSnakeFood != null) {
                        if (this.snake[0].equals(this.bonusSnakeFood)) {
                            this.bonusSnakeFood.setCordinate(0, 0);
                            this.board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 0;
                            this.snake[this.snakeLength].copy(this.snake[this.snakeLength - 1]);
                            this.snakeLength += 5;
                            this.score += 5;
                            this.ticFoodTypeCount = 0;
//                            this.activeBonusFood = false;
                            this.isInsideSwitch = false;
                        }

                    }

                    if (this.speedSnakeFood != null) {
                        if (this.snake[0].equals(this.speedSnakeFood)) {
                            this.speedSnakeFood.setCordinate(0, 0);
                            this.board[this.speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 0;
                            this.speed -= this.bonusFoodRate;
                            this.ticFoodTypeCount = 0;
//                            this.activeSpeedFood = false;
                            this.isInsideSwitch = false;
                            new Thread() {
                                @Override
                                public void run() {
                                    sleepComponent();
                                    speed += bonusFoodRate;
                                }
                            }.start();
                        }
                    }

                    if (this.slowSnakeFood != null) {
                        if (this.snake[0].equals(this.slowSnakeFood)) {
                            this.slowSnakeFood.setCordinate(0, 0);
                            this.board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 0;
                            this.speed += this.bonusFoodRate;
                            this.ticFoodTypeCount = 0;
//                            this.activeSlowFood = false;
                            this.isInsideSwitch = false;
                            new Thread() {
                                @Override
                                public void run() {
                                    sleepComponent();
                                    speed -= bonusFoodRate;
                                }
                            }.start();
                        }
                    }

//                    }
                    if (this.snake[0].getY() >= this.Y) {
                        this.snake[0].setY(this.Y - 1);
                        System.out.println(this.snake[0].getY());
                        this.gameState = 3;
                        System.out.println("The snke touched the floor");
                        System.out.println("Snake " + snake[0]);
                    }

                    if (this.snake[0].getY() < 0) {
                        this.snake[0].setY(0);
                        this.gameState = 3;
                        System.out.println("The snke touched the roof");
                        System.out.println("Snake " + this.snake[0]);
                    }

                    if (this.snake[0].getX() >= X) {
                        this.snake[0].setX(X - 1);
                        this.gameState = 3;
                        System.out.println("The snke touched right wall");
                        System.out.println("Snake " + this.snake[0]);
                    }

                    if (this.snake[0].getX() < 0) {
                        this.snake[0].setX(0);
                        this.gameState = 3;
                        System.out.println("The snke touched left wall");
                        System.out.println("Snake " + this.snake[0]);
                    }

                    if (numObstacles > 0) {

                        for (int i = 0; i < this.numObstacles; i++) {

                            for (int j = 0; j < sizeOfObstacles; j++) {
                                if (this.snake[0].getX() == this.obstacles[i][j].getX()
                                        && this.snake[0].getY() == this.obstacles[i][j].getY()) {
                                    this.gameState = 3;
                                    System.out.println("Snkae touched the  obstacles :(");
                                }

                            }
                        }
                    }

                    for (int x = 0; x < X; x++) {
                        for (int y = 0; y < Y; y++) {
                            this.board[x][y] = 0;
                        }
                    }

                    for (int i = 0; i < this.snakeLength; i++) {
                        this.board[this.snake[i].getX()][this.snake[i].getY()] = 1;
                    }

                    if (numObstacles > 0) {
                        for (int i = 0; i < numObstacles; i++) {
                            for (int j = 0; j < this.sizeOfObstacles; j++) {
                                this.board[obstacles[i][j].getX()][this.obstacles[i][j].getY()] = 3;

                            }
                        }
                    }

                    this.board[this.normalSnakeFood.getX()][this.normalSnakeFood.getY()] = 2;

                    if (this.bonusSnakeFood != null) {
                        if (this.bonusSnakeFood.getX() != 0 && this.bonusSnakeFood.getY() != 0) {
                            this.board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 4;
                        }
                    }
                    if (this.slowSnakeFood != null) {
                        if (this.slowSnakeFood.getX() != 0 && this.slowSnakeFood.getY() != 0) {
                            this.board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 5;
                        }
                    }
                    if (this.speedSnakeFood != null) {
                        if (this.speedSnakeFood.getX() != 0 && this.speedSnakeFood.getY() != 0) {
                            this.board[this.speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 6;
                        }
                    }

                } else if (this.gameState == 3) {
                    if (this.count < (1500 / this.speed)) {
                        this.count++;
                    } else {
                        this.count = 0;
                        this.gameState = 1;
                        this.repaint();
                    }
                    return;
                } else {
                    System.out.println("Else");
                }
            }

            this.paintSnake();

            try {
                sleep(speed);
            } catch (InterruptedException e) {
            }
        }
    }

    private void locateNormalFoodRandom() {
        ArrayList<Cordinate> free = new ArrayList();
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (board[x][y] != 1
                        && board[x][y] != 3
                        && board[x][y] != 4
                        && board[x][y] != 5
                        && board[x][y] != 6) {
                    free.add(new Cordinate(x, y));
                }
            }
        }

        free.add(normalSnakeFood);
        Random random = new Random();
        if (free.isEmpty()) {
            this.gameState = 3;
        } else {

            int randomVal = random.nextInt(free.size()) - 1;

            while (randomVal > free.size() - 1 || randomVal < 0) {
                randomVal = random.nextInt(free.size()) - 1;
            }

            normalSnakeFood = free.get(randomVal);

        }
    }

    private void locateSpeedFoodRandom() {
        ArrayList<Cordinate> free = new ArrayList();
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (board[x][y] != 1
                        && board[x][y] != 3
                        && board[x][y] != 2) {
                    free.add(new Cordinate(x, y));
                }
            }
        }

        free.add(speedSnakeFood);
        Random random = new Random();
        if (free.isEmpty()) {
            this.gameState = 3;
        } else {

            int randomVal = random.nextInt(free.size()) - 1;

            while (randomVal > free.size() - 1 || randomVal < 0) {
                randomVal = random.nextInt(free.size()) - 1;
            }

            speedSnakeFood = free.get(randomVal);

        }
    }

    private void locateSlowFoodRandom() {
        ArrayList<Cordinate> free = new ArrayList();
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (board[x][y] != 1
                        && board[x][y] != 3
                        && board[x][y] != 2) {
                    free.add(new Cordinate(x, y));
                }
            }
        }

        free.add(slowSnakeFood);
        Random random = new Random();
        if (free.isEmpty()) {
            this.gameState = 3;
        } else {

            int randomVal = random.nextInt(free.size()) - 1;

            while (randomVal > free.size() - 1 || randomVal < 0) {

                randomVal = random.nextInt(free.size()) - 1;
            }

            slowSnakeFood = free.get(randomVal);

        }
    }

    private void locateBonusFoodRandom() {

        ArrayList<Cordinate> free = new ArrayList();

        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (board[x][y] != 1 && board[x][y] != 3 && board[x][y] != 2) {
                    free.add(new Cordinate(x, y));
                }
            }
        }

        free.add(bonusSnakeFood);
        Random random = new Random();

        if (free.isEmpty()) {
            this.gameState = 3;
        } else {

            int randomVal = random.nextInt(free.size()) - 1;

            while (randomVal > free.size() - 1 || randomVal < 0) {

                randomVal = random.nextInt(free.size()) - 1;
            }

            bonusSnakeFood = free.get(randomVal);
        }
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cordinate getPosition() {
        return snake[0];
    }

    @Override
    public Cordinate getFood() {
        return normalSnakeFood;
    }

    @Override
    public Cordinate[] getSnake() {
        return snake;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public void keyEvent(int key) {
        switch (key) {
            case 1:
                this.keyEvent("Right");
                break;
            case 2:
                this.keyEvent("Left");
                break;
            case 3:
                this.keyEvent("Up");
                break;
            case 4:
                this.keyEvent("Down");
                break;
        }
    }

    public void keyEvent(String key) {
        if (key.equals("Left") && (direction != RIGHT)) {
            direction = LEFT;
            if (!this.started) {
                this.started = true;
            }
        }
        if (key.equals("Right") && (direction != LEFT)) {
            direction = RIGHT;
            if (!this.started) {
                this.started = true;
            }
        }
        if (key.equals("Up") && (direction != DOWN)) {
            direction = UP;
            if (!this.started) {
                this.started = true;
            }
        }
        if (key.equals("Down") && (direction != UP)) {
            direction = DOWN;
            if (!this.started) {
                this.started = true;
            }
        }
    }

    @Override
    public boolean isOccupied(int x, int y) {
        return this.board[x][y] == 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        KeyStroke stroke = KeyStroke.getKeyStrokeForEvent(e);
        int key = stroke.getKeyCode();

//        if ((KeyEvent.getKeyText(key).equals("Left")) && (direction != RIGHT)) {
//            direction = LEFT;
//            if (!this.started) {
//                this.started = true;
//            }
//        }
//        if ((KeyEvent.getKeyText(key).equals("Right")) && (direction != LEFT)) {
//            direction = RIGHT;
//            if (!this.started) {
//                this.started = true;
//            }
//        }
//
//        if ((KeyEvent.getKeyText(key).equals("Up")) && (direction != DOWN)) {
//            direction = UP;
//            if (!this.started) {
//                this.started = true;
//            }
//        }
//
//        if ((KeyEvent.getKeyText(key).equals("Down")) && (direction != UP)) {
//            direction = DOWN;
//            if (!this.started) {
//                this.started = true;
//            }
//        }
//        
        if (key == 37 && (direction != RIGHT)) {
            direction = LEFT;
            if (!this.started) {
                this.started = true;
            }
        }
        if (key == 39 && (direction != LEFT)) {
            direction = RIGHT;
            if (!this.started) {
                this.started = true;
            }
        }

        if (key == 38 && (direction != DOWN)) {
            direction = UP;
            if (!this.started) {
                this.started = true;
            }
        }

        if (key == 40 && (direction != UP)) {
            direction = DOWN;
            if (!this.started) {
                this.started = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public int isOccupied2(int x, int y) {
        return board[x][y];
    }

    private void sleepComponent() {
        try {
            for (int i = 1; i <= 5; i++) {
                this.setTitle("Time Remain: " + i);
                sleep(1000);
            }
            this.setTitle("Snake 5");

        } catch (InterruptedException ex) {
            Logger.getLogger(SnakeUserRun.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isSnakeHitObstaclesUp(int snakeHeadX, int sankeHeadY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOccupiedObstacles(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] getMaze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cordinate[][] getObstacles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
