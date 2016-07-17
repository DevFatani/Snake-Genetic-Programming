package snake;

public interface Snake {

    public void run();

    public void move();

    public Cordinate getPosition();

    public Cordinate getFood();

    public Cordinate[] getSnake();

    public int getDirection();

    public double getScore();

    public int getX();

    public int getY();

//    public Cordinate[] getObstaclesUp();
//
//    public Cordinate[] getObstaclesDown();
//
//    public Cordinate[] getObstaclesCenter();

    public  Cordinate[][] getObstacles();
    
    public void keyEvent(int key);

    public boolean isOccupied(int x, int y);
    
    public boolean isOccupiedObstacles(int x, int y);

    public boolean isSnakeHitObstaclesUp(int snakeHeadX, int sankeHeadY);

    public int isOccupied2(int x, int y);
    
    
    public int[][] getMaze();
}
