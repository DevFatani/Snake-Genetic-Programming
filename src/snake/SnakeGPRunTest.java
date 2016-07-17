package snake;

import GPWorkStation.GPParameter;
import GPWorkStation.Node;
import GPWorkStation.ScoredType;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author s4330_000
 */
public class SnakeGPRunTest extends JFrame implements Snake, Runnable {

    private static final int SNAKEINIT = 5;

    private int sizeOfObstacles;
    
    private int numObstacles;

    private Cordinate normalSnakeFood = new Cordinate();

    private Cordinate speedSnakeFood = null;

    private Cordinate bonusSnakeFood = null;

    private Cordinate slowSnakeFood = null;

    private Cordinate snake[]/* = new Cordinate[X * Y]*/;

    private Cordinate[][] obstacles;

    private int board[][]/* = new int[X][Y]*/;

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

    private String stemp;

    private String s;

    private String t;

    private boolean started = false;

    private Thread setTime;

    private boolean activeBonusFood = false;

    private boolean activeSpeedFood = false;

    private boolean activeSlowFood = false;

    private byte ticFoodTypeCount = 0;

    private int bonusFoodRate = 0;

    private boolean isInsideSwitch;

    ScoredType<Node> indv;

    int ticks = 0;

    int noOfMoves = 0;

    double prevScore = 0;

    private GPParameter gpp;

    public SnakeGPRunTest(int speed, boolean isSpeedFood, boolean isSlowFood, boolean isBonusFood, ScoredType<Node> indv, GPParameter p_gpp) {

        super("Snake 5");

        gpp = p_gpp;

        this.sizeOfObstacles = gpp.SIZE_OF_OBSTACLES;

        numObstacles = gpp.NUM_OBSTACLES;

        snake = new Cordinate[gpp.X * gpp.Y];

        obstacles = new Cordinate[gpp.NUM_OBSTACLES][1];

        for (int i = 0; i < numObstacles; i++) {

            obstacles[i] = new Cordinate[sizeOfObstacles];

        }

        board = new int[gpp.X][gpp.Y];

        this.setUpSimulatorConfiguration(speed, isSpeedFood, isSlowFood, isBonusFood);

        this.setLayout(new FlowLayout());
        this.setSize(400, 400);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        for (int i = 0; i < gpp.X * gpp.Y; i++) {
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
        } catch (IOException ex) {
        }

        direction = NONE;
        gameState = 1;
        score = 0;
        started = false;
        this.indv = indv;

    }

    private void setUpSimulatorConfiguration(int speed, boolean isSpeedFood, boolean isSlowFood, boolean isBonusFood) {

        if (isSpeedFood) {
            this.speedSnakeFood = new Cordinate();

        }

        if (isSlowFood) {
            this.slowSnakeFood = new Cordinate();
        }

        if (isBonusFood) {
            this.bonusSnakeFood = new Cordinate();
        }

        this.speed = speed;

        if (this.speed >= 50 && this.speed <= 100) {
            this.bonusFoodRate = 35;
        } else if (this.speed >= 100 && this.speed <= 150) {
            this.bonusFoodRate = 55;
        } else {
            this.bonusFoodRate = 1;
        }
    }

    private int[] getVerCordinate(int position) {

        int x = 0;

        int y = 0;

        if (position == 0) {

            do {

                x = 2;

                y = new Random().nextInt((18 - 2) + 1) + 2;

            } while (board[x][y] == 3 || board[x][y] == 2);

        } else if (position == 1) {

            do {

                x = new Random().nextInt((18 - 2) + 1) + 2;

                y = 2;

            } while (board[x][y] == 3 || board[x][y] == 2);

        } else if (position == 2) {

            do {

                x = 2;

                y = new Random().nextInt((14 - 2) + 1) + 2;

            } while (board[x][y] == 3 || board[x][y] == 2);

        } else {

            do {

                x = new Random().nextInt((14 - 2) + 1) + 2;

                y = 2;

            } while (board[x][y] == 3 || board[x][y] == 2);

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

                for (int j = 0; j < sizeOfObstacles; j++) {

                    obstacles[i][j].setCordinate(x + j, y);

                }

                //Horizontal 
            } else {

                int xArr[] = getVerCordinate(1);

                cordinate = new Cordinate(xArr[0], xArr[1]);

                int x = cordinate.getX();

                int y = cordinate.getY();

                for (int j = 0; j < sizeOfObstacles; j++) {

                    obstacles[i][j].setCordinate(x, y + j);

                }

            }

        }
    }

    private void paintSnake() {
        Graphics g = this.getGraphics();
        g.drawImage(this.backGround, 10, 30, gpp.X * 10, gpp.Y * 10, this);
        g.setColor(Color.RED);

        if (started) {
            this.t = "Score " + this.score;
            g.clearRect(0, (gpp.Y * 10) + 31, (gpp.X * 10) + 100, (gpp.Y * 10) + 100);
            g.drawString(this.t, ((gpp.X * 10) / 2) - 20, (gpp.Y * 10) + 50);
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
                g.drawString(t, 5, (gpp.Y * 10) + 150);
            }
            for (int x = 0; x < gpp.X; x++) {
                for (int y = 0; y < gpp.Y; y++) {

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
            g.drawString(s, ((gpp.X * 10) / 2) - 20, (gpp.Y * 10) / 2);
            this.started = false;
        }
    }

    @Override
    public void run() {
        this.score = 0;
        for (int i = 0; i < gpp.X; i++) {
            for (int j = 0; j < gpp.Y; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < SNAKEINIT; i++) {
            snake[i].setCordinate(12 - i, 8);
        }

        if (numObstacles > 0) {

            createObstacles();

        }

        gameState = 2;

        for (int i = 0; i < this.snakeLength; i++) {
            this.board[this.snake[i].getX()][this.snake[i].getY()] = 1;
        }

        this.locateNormalFoodRandom();

        for (int i = 0; i < numObstacles; i++) {

            for (int j = 0; j < sizeOfObstacles; j++) {

                this.board[this.obstacles[i][j].getX()][this.obstacles[i][j].getY()] = 3;

            }
        }

        this.board[normalSnakeFood.getX()][normalSnakeFood.getY()] = 2;

//        //ini foods
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

        ticks = 0;
        while (gameState != 3) {
            Node root = indv.solution;
            root.f(this);
            ticks++;
        }

        System.out.println("ticjs ->>>" + ticks);
    }

    private void locateNormalFoodRandom() {
        ArrayList<Cordinate> free = new ArrayList();
        for (int x = 0; x < gpp.X; x++) {
            for (int y = 0; y < gpp.Y; y++) {
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
        for (int x = 0; x < gpp.X; x++) {
            for (int y = 0; y < gpp.Y; y++) {
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
        for (int x = 0; x < gpp.X; x++) {
            for (int y = 0; y < gpp.Y; y++) {
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

        for (int x = 0; x < gpp.X; x++) {
            for (int y = 0; y < gpp.Y; y++) {
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
        if (prevScore < score) {
            prevScore = score;
            noOfMoves = 0;
        } else if (noOfMoves > gpp.X * gpp.Y + 1) {
            gameState = 3;
        } else {
            noOfMoves++;
        }
        if (started) {
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

                if (this.snake[0].equals(normalSnakeFood)) {
                    this.snake[this.snakeLength].copy(this.snake[this.snakeLength - 1]);
                    this.snakeLength++;
                    this.locateNormalFoodRandom();
                    this.score++;
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

                    System.out.println("Random Value For Bonus Food: " + randomVal);

                    int sizeOfMaze = (gpp.X * gpp.Y);
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

                if (this.snake[0].getY() >= gpp.Y) {
                    this.snake[0].setY(gpp.Y - 1);
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

                if (this.snake[0].getX() >= gpp.X) {
                    this.snake[0].setX(gpp.X - 1);
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
                            if (snake[0].getX() == this.obstacles[i][j].getX()
                                    && snake[0].getY() == this.obstacles[i][j].getY()) {
                                this.gameState = 3;
                                System.out.println("Snkae touched the  obstacles :(");
                            }

                        }
                    }
                }

                if (gameState != 3) {

                    for (int x = 0; x < gpp.X; x++) {
                        for (int y = 0; y < gpp.Y; y++) {
                            this.board[x][y] = 0;
                        }
                    }

                    for (int i = 0; i < this.snakeLength; i++) {
                        this.board[this.snake[i].getX()][this.snake[i].getY()] = 1;
                    }
                    if (numObstacles > 0) {
                        for (int i = 0; i < numObstacles; i++) {
                            for (int j = 0; j < this.sizeOfObstacles; j++) {
                                board[obstacles[i][j].getX()][obstacles[i][j].getY()] = 3;

                            }
                        }
                    }
                    this.board[normalSnakeFood.getX()][normalSnakeFood.getY()] = 2;

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
                }
            }
        }

        paintSnake();

        try {
            sleep(speed);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

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
            Logger.getLogger(SnakeGPRunTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isSnakeHitObstaclesUp(int snakeHeadX, int sankeHeadY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public boolean isOccupiedObstacles(int x, int y) {
        return board[x][y] == 3;
    }

    @Override
    public int[][] getMaze() {
        return board;
    }

    @Override
    public Cordinate[][] getObstacles() {
        return obstacles;
    }

}
