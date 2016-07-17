package snake;

public class Cordinate {

    int x;
    int y;

    public Cordinate() {
    }

    public Cordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ("(" + x + "," + y + ")");
    }

    public void copy(Cordinate cord) {
        this.setX(cord.getX());
        this.setY(cord.getY());
    }

    @Override
    public boolean equals(Object obj) {
        Cordinate cord = (Cordinate) obj;
        return (this.x == cord.getX()) && (this.y == cord.getY());
    }
}
