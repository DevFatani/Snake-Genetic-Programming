package snake;

import GPWorkStation.GPParameter;
import GPWorkStation.Node;
import GPWorkStation.ScoredType;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s4330_000
 */
public class SnakeGPRun implements Snake, Runnable {

    public static final int SNAKEINIT = 5;
    public int snakeLength = SNAKEINIT;

    private int sizeOfObstacles;

    private int numObstacles;

    public static Cordinate normalSnakeFood = new Cordinate();

    public Cordinate speedSnakeFood = null;

    public Cordinate bonusSnakeFood = null;

    public Cordinate slowSnakeFood = null;

    public static Cordinate snake[]; 

    private Cordinate[][] obstacles;

    public static int board[][];

    int gameState = 1;

    double score = 0;

    int ticks = 0;

    boolean print = false;

    int noOfMoves = 0;

    double prevScore = 0;

    int NRuns;

    public int count = 0;
    int speed;
    public int add = 1;

    GPParameter gpp;
    static final int NONE = 0;
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;
    public static int direction = NONE;

    public String stemp;
    public String s;
    public String t;

    public ScoredType<Node> indv;

    public boolean started = false;
    public Thread setTime;

    public boolean activeBonusFood = false;
    public boolean activeSpeedFood = false;
    public boolean activeSlowFood = false;

    public byte ticFoodTypeCount = 0;
    public int bonusFoodRate = 0;

    public boolean isInsideSwitch;

    public SnakeGPRun(int speed, boolean isSpeedFood, boolean isSlowFood, boolean isBonusFood, ScoredType<Node> indv, int NRuns, GPParameter p_gpp) {

        this.gpp = p_gpp;

        this.sizeOfObstacles = gpp.SIZE_OF_OBSTACLES;

        numObstacles = gpp.NUM_OBSTACLES;

        snake = new Cordinate[gpp.X * gpp.Y];

        obstacles = new Cordinate[gpp.NUM_OBSTACLES][1];

        for (int i = 0; i < numObstacles; i++) {
     
            obstacles[i] = new Cordinate[sizeOfObstacles];
        
        
        }

        board = new int[gpp.X][gpp.Y];

        this.setUpSimulatorConfiguration(speed, isSpeedFood, isSlowFood, isBonusFood);

        for (int i = 0; i < gpp.X * gpp.Y; i++) {

            snake[i] = new Cordinate();

        }

        for (int i = 0; i < this.numObstacles; i++) {

            for (int j = 0; j < this.sizeOfObstacles; j++) {

                this.obstacles[i][j] = new Cordinate();

            }
        }

        direction = NONE;
        this.gameState = 1;
        this.score = 0;
        this.started = false;
        this.indv = indv;
        this.NRuns = NRuns;
    }

    private void setUpSimulatorConfiguration(int speed,
            boolean isSpeedFood, boolean isSlowFood, boolean isBonusFood
    ) {

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

            } while (board[x][y] == 3 ||board[x][y] == 2);

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

            
            System.out.println("obstalcesType = [" +  obstalcesType + "]");
            
            Cordinate cordinate;

            //Vertical
            if (obstalcesType == 1) {

                int xArr[] = getVerCordinate(0);

                cordinate = new Cordinate(xArr[0], xArr[1]);

                int x = cordinate.getX();

                int y = cordinate.getY();

                System.out.println("Virtical x= " + x + " y= " + y);
                
                
                for (int j = 0; j < sizeOfObstacles; j++) {

                    obstacles[i][j].setCordinate(x + j, y);

                }

                //Horizontal 
            } else {

                int xArr[] = getVerCordinate(1);

                cordinate = new Cordinate(xArr[0], xArr[1]);

                int x = cordinate.getX();

                int y = cordinate.getY();
                
                 System.out.println("Horizontal x= " + x + " y= " + y);
                

                for (int j = 0; j < sizeOfObstacles; j++) {

                    obstacles[i][j].setCordinate(x, y + j);

                }

            }

        }
    }

    @Override
    public void run() {

        this.ticks = 0;
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
            board[snake[i].getX()][snake[i].getY()] = 1;
        }

        this.locateNormalFoodRandom();

        
        
        board[normalSnakeFood.getX()][normalSnakeFood.getY()] = 2;

        for (int i = 0; i < numObstacles; i++) {

            for (int j = 0; j < sizeOfObstacles; j++) {

                board[this.obstacles[i][j].getX()][obstacles[i][j].getY()] = 3;

            }
        }

        if (this.bonusSnakeFood != null) {
            this.bonusSnakeFood.setCordinate(0, 0);
            board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 0;
        }
        if (this.speedSnakeFood != null) {
            this.speedSnakeFood.setCordinate(0, 0);
            board[this.speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 0;
        }
        if (this.slowSnakeFood != null) {
            this.slowSnakeFood.setCordinate(0, 0);
            board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 0;
        }

        while (gameState != 3) {
            Node root = indv.solution;
            root.f(this);
            ticks++;
        }
        indv.ss.add(score);
        indv.ss.addTicks(ticks);
    }

    private void locateNormalFoodRandom() {
        ArrayList<Cordinate> free = new ArrayList<>();
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
            System.out.println("Snake did not touch any thing");
        } else {
            noOfMoves++;
        }

        if (this.started) {
            for (int i = this.snakeLength - 1; i > 0; i--) {
                snake[i].copy(snake[i - 1]);
            }

            switch (direction) {
                case LEFT:
                    snake[0].setX(snake[0].getX() - 1);
                    break;
                case RIGHT:
                    snake[0].setX(snake[0].getX() + 1);
                    break;
                case UP:
                    snake[0].setY(snake[0].getY() - 1);
                    break;
                case DOWN:
                    snake[0].setY(snake[0].getY() + 1);
                    break;
            }

            if (this.gameState == 2) {

                if (snake[0].equals(normalSnakeFood)) {
                    snake[this.snakeLength].copy(snake[this.snakeLength - 1]);
                    this.snakeLength++;
                    this.locateNormalFoodRandom();
                    this.score++;
//                    this.ticFoodTypeCount++;
//                    System.out.println("ticFoodTypeCount: " + this.ticFoodTypeCount);
                } else {
                    for (int i = 1; i < this.snakeLength; i++) {
                        if (snake[i].equals(snake[0])) {
                            this.gameState = 3;
                            System.out.println("The snake touched itself " + i);
                            System.out.println("snake head " + snake[0]);
                            System.out.println("snake touched segment " + snake[i]);
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
                                    board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 4;
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
                                    board[speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 6;
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
                                    board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 5;
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
                    if (snake[0].equals(this.bonusSnakeFood)) {
                        this.bonusSnakeFood.setCordinate(0, 0);
                        board[this.bonusSnakeFood.getX()][this.bonusSnakeFood.getY()] = 0;
                        snake[this.snakeLength].copy(snake[this.snakeLength - 1]);
                        this.snakeLength += 5;
                        this.score += 5;
                        this.ticFoodTypeCount = 0;
//                            this.activeBonusFood = false;
                        this.isInsideSwitch = false;
                    }

                }

                if (this.speedSnakeFood != null) {
                    if (snake[0].equals(this.speedSnakeFood)) {
                        this.speedSnakeFood.setCordinate(0, 0);
                        board[this.speedSnakeFood.getX()][this.speedSnakeFood.getY()] = 0;
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
                    if (snake[0].equals(this.slowSnakeFood)) {
                        this.slowSnakeFood.setCordinate(0, 0);
                        board[this.slowSnakeFood.getX()][this.slowSnakeFood.getY()] = 0;
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

                if (snake[0].getY() >= gpp.Y) {
                    snake[0].setY(gpp.Y - 1);
                    System.out.println(snake[0].getY());
                    this.gameState = 3;
                    System.out.println("The snke touched the floor");
                    System.out.println("Snake " + snake[0]);
                }

                if (snake[0].getY() < 0) {
                    snake[0].setY(0);
                    this.gameState = 3;
                    System.out.println("The snke touched the roof");
                    System.out.println("Snake " + snake[0]);
                }

                if (snake[0].getX() >= gpp.X) {
                    snake[0].setX(gpp.X - 1);
                    this.gameState = 3;
                    System.out.println("The snke touched right wall");
                    System.out.println("Snake " + snake[0]);
                }

                if (snake[0].getX() < 0) {
                    snake[0].setX(0);
                    this.gameState = 3;
                    System.out.println("The snke touched left wall");
                    System.out.println("Snake " + snake[0]);
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
                            board[x][y] = 0;
                        }
                    }

                    for (int i = 0; i < this.snakeLength; i++) {
                        board[snake[i].getX()][snake[i].getY()] = 1;
                    }

                    if (numObstacles > 0) {
                        for (int i = 0; i < numObstacles; i++) {
                            for (int j = 0; j < this.sizeOfObstacles; j++) {
                                board[obstacles[i][j].getX()][obstacles[i][j].getY()] = 3;

                            }
                        }
                    }

                    board[normalSnakeFood.getX()][normalSnakeFood.getY()] = 2;

                    if (this.bonusSnakeFood != null) {
                        if (this.bonusSnakeFood.getX() != 0 && this.bonusSnakeFood.getY() != 0) {
                            board[this.bonusSnakeFood.getX()][bonusSnakeFood.getY()] = 4;
                        }
                    }
                    if (this.slowSnakeFood != null) {
                        if (this.slowSnakeFood.getX() != 0 && this.slowSnakeFood.getY() != 0) {
                            board[this.slowSnakeFood.getX()][slowSnakeFood.getY()] = 5;
                        }
                    }
                    if (this.speedSnakeFood != null) {
                        if (this.speedSnakeFood.getX() != 0 && this.speedSnakeFood.getY() != 0) {
                            board[this.speedSnakeFood.getX()][speedSnakeFood.getY()] = 6;
                        }
                    }
                }
            }
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
    public int getX() {
        return gpp.X;
    }

    @Override
    public int getY() {
        return gpp.Y;
    }

    public int getGameState() {
        return this.gameState;
    }

    public boolean isFoodRight() {
        if ((snake[0].getX() < gpp.X)
                && (snake[0].getY() < gpp.Y)
                && (snake[0].getX() >= 0)
                && (snake[0].getY() >= 0)) {
            if (normalSnakeFood.getX() > snake[0].getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFoodUp() {
        if ((snake[0].getX() < gpp.X)
                && (snake[0].getY() < gpp.Y)
                && (snake[0].getX() >= 0)
                && (snake[0].getY() >= 0)) {
            if (normalSnakeFood.getY() < snake[0].getY()) {
                return true;
            }
        }
        return false;
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
            if (!started) {
                started = true;
            }
        }
        if (key.equals("Right") && (direction != LEFT)) {
            direction = RIGHT;
            if (!started) {
                started = true;
            }
        }
        if (key.equals("Up") && (direction != DOWN)) {
            direction = UP;
            if (!started) {
                started = true;
            }
        }
        if (key.equals("Down") && (direction != UP)) {
            direction = DOWN;
            if (!started) {
                started = true;
            }
        }
    }

    @Override
    public boolean isOccupied(int x, int y) {
        return board[x][y] == 1;
    }

    @Override
    public int isOccupied2(int x, int y) {
        return board[x][y];
    }

    private void sleepComponent() {
        try {
            for (int i = 1; i <= 5; i++) {
//                this.setTitle("Time Remain: " + i);
                sleep(1000);
            }
//            this.setTitle("Snake 5");
        } catch (InterruptedException ex) {
            Logger.getLogger(SnakeGPRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isSnakeHitObstaclesUp(int snakeHeadX, int sankeHeadY) {
//        return obstaclesUp[sankeHeadY].equals()
        return false;
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
