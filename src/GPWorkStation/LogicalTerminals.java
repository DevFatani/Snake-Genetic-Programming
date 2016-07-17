package GPWorkStation;

import snake.Cordinate;
import snake.Snake;

public class LogicalTerminals {

    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;

    public static class IsFoodAhead extends Node {

        GPParameter gpp;

        public IsFoodAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {
            int dir = snake.getDirection();

            switch (dir) {
                case UP:
                    return snake.getFood().getX() == gpp.X && snake.getFood().getY() < gpp.Y;
                case DOWN://?
                    return snake.getFood().getX() == gpp.X && snake.getFood().getX() < gpp.X;
                case LEFT://?
                    return snake.getFood().getX() == gpp.X && snake.getFood().getX() > gpp.X;
                case RIGHT:
                    return snake.getFood().getY() > gpp.Y;
                default:
                    return false;

            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsFoodAhead";
        }

        @Override
        public Node copy() {
            return new IsFoodAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsFoodLeft extends Node {

        GPParameter gpp;

        public IsFoodLeft(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {
            int dir = snake.getDirection();

            switch (dir) {
                case UP:
                    return snake.getFood().getX() < gpp.X;
                case DOWN:
                    return snake.getFood().getX() >= gpp.X;
                case LEFT:
                    return snake.getFood().getY() >= gpp.Y;
                case RIGHT:
                    return snake.getFood().getY() < gpp.Y;
                default:
                    return false;
            }
        }

        public int arity() {
            return 0;
        }

        public String name() {
            return "IsFoodLeft";
        }

        public Node copy() {
            return new IsFoodLeft(gpp);
        }

        public Node newNode() {
            return copy();
        }

        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsFoodRight extends Node {

        GPParameter gpp;

        public IsFoodRight(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;

            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {
            int dir = snake.getDirection();

            switch (dir) {
                case UP:
                    return snake.getFood().getX() > gpp.X;
                case DOWN:
                    return snake.getFood().getX() <= gpp.X;
                case LEFT:
                    return snake.getFood().getY() <= gpp.Y;
                case RIGHT:
                    return snake.getFood().getY() > gpp.Y;
                default:
                    return false;
            }
        }

        public int arity() {
            return 0;
        }

        public String name() {
            return "IsFoodRight";
        }

        public Node copy() {
            return new IsFoodRight(gpp);
        }

        public Node newNode() {
            return copy();
        }

        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsMovingUp extends Node {

        public IsMovingUp() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {
            return snake.getDirection() == UP;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsMovingUp";
        }

        @Override
        public Node copy() {
            return new IsMovingUp();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsMovingDown extends Node {

        public IsMovingDown() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            return snake.getDirection() == DOWN;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsMovingDown";
        }

        @Override
        public Node copy() {
            return new IsMovingDown();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsMovingLeft extends Node {

        public IsMovingLeft() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            return snake.getDirection() == LEFT;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsMovingLeft";
        }

        @Override
        public Node copy() {
            return new IsMovingLeft();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsMovingRight extends Node {

        public IsMovingRight() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {
            return snake.getDirection() == RIGHT;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsMovingRight";
        }

        @Override
        public Node copy() {
            return new IsMovingRight();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsWall2StepsAhead extends Node {

        GPParameter gpp;

        public IsWall2StepsAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.getSnake()[0].getY() == 1;
                case DOWN:
                    return snake.getSnake()[0].getY() == gpp.Y - 2;
                case LEFT:
                    return snake.getSnake()[0].getX() == 1;
                case RIGHT:
                    return snake.getSnake()[0].getX() > gpp.X - 2;
                default:
                    return false;
            }

        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsWall2StepsAhead";
        }

        @Override
        public Node copy() {
            return new IsWall2StepsAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsWallAhead extends Node {

        GPParameter gpp;

        public IsWallAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.getSnake()[0].getY() < 1;
                case DOWN:
                    return snake.getSnake()[0].getY() > gpp.Y - 2;
                case LEFT:
                    return snake.getSnake()[0].getX() < 1;
                case RIGHT:
                    return snake.getSnake()[0].getX() > gpp.X - 2;
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsWallAhead";
        }

        @Override
        public Node copy() {
            return new IsWallAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsWallLeft extends Node {

        GPParameter gpp;

        public IsWallLeft(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;

            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.getSnake()[0].getX() == 0;
                case DOWN:
                    return snake.getSnake()[0].getX() == gpp.X - 1;
                case LEFT:
                    return snake.getSnake()[0].getY() == gpp.Y - 1;
                case RIGHT:
                    return snake.getSnake()[0].getY() == 0;
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsWallLeft";
        }

        @Override
        public Node copy() {
            return new IsWallLeft(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsWallRight extends Node {

        GPParameter gpp;

        public IsWallRight(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case DOWN:
                    return snake.getSnake()[0].getX() == 0;
                case UP:
                    return snake.getSnake()[0].getX() == gpp.X - 1;
                case RIGHT:
                    return snake.getSnake()[0].getY() == gpp.Y - 1;
                case LEFT:
                    return snake.getSnake()[0].getY() == 0;
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsWallRight";
        }

        @Override
        public Node copy() {
            return new IsWallRight(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsDangerAhead extends Node {

        GPParameter gpp;

        public IsDangerAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return (snake.getSnake()[0].getY() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1)));
                case DOWN:
                    return (snake.getSnake()[0].getY() == gpp.Y - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1)));
                case LEFT:
                    return (snake.getSnake()[0].getX() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY())));
                case RIGHT:
                    return (snake.getSnake()[0].getX() == gpp.X - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY())));
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsDangerAhead";
        }

        @Override
        public Node copy() {
            return new IsDangerAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsDangerWithObstaclesAhead extends Node {

        GPParameter gpp;

        public IsDangerWithObstaclesAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    boolean isFoodUp = !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1));
                    return (snake.getSnake()[0].getY() == 0)
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && isFoodUp)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && isFoodUp);
                case DOWN:
                    boolean isFoodDown = !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1));
                    return (snake.getSnake()[0].getY() == gpp.Y - 1)
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && isFoodDown)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && isFoodDown);
                case LEFT:
                    boolean isFoodLeft = !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()));
                    return (snake.getSnake()[0].getX() == 0)
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && isFoodLeft)
                            || (snake.isOccupied(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && isFoodLeft);
                case RIGHT:
                    boolean isFoodRight = !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()));
                    return (snake.getSnake()[0].getX() == gpp.X - 1)
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && isFoodRight)
                            || (snake.isOccupied(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && isFoodRight);
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsDangerWithObstaclesAhead";
        }

        @Override
        public Node copy() {
            return new IsDangerWithObstaclesAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsBodyAhead extends Node {

        public IsBodyAhead() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1));
                case DOWN:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1));
                case LEFT:
                    return snake.isOccupied(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()));
                case RIGHT:
                    return snake.isOccupied(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()));
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsBodyAhead";
        }

        @Override
        public Node copy() {
            return new IsBodyAhead();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsBodyRight extends Node {

        public IsBodyRight() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.isOccupied(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()));
                case DOWN:
                    return snake.isOccupied(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()));
                case LEFT:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1));
                case RIGHT:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1));
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsBodyRight";
        }

        @Override
        public Node copy() {
            return new IsBodyRight();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsBodyLeft extends Node {

        public IsBodyLeft() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    return snake.isOccupied(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()));
                case DOWN:
                    return snake.isOccupied(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()));
                case LEFT:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1));
                case RIGHT:
                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1));
                default:
                    return false;
            }
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsBodyLeft";
        }

        @Override
        public Node copy() {
            return new IsBodyLeft();
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsDanger2StepsAhead extends Node {

        GPParameter gpp;

        public IsDanger2StepsAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    if (snake.getSnake()[0].getY() < 2) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getY() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)))) {
                        return true;
                    }
                    break;
                case DOWN:
                    if (snake.getSnake()[0].getY() > gpp.Y - 3) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getY() == gpp.Y - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2)))) {
                        return true;
                    }
                    break;
                case LEFT:
                    if (snake.getSnake()[0].getX() < 2) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getX() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())))) {
                        return true;
                    }
                    break;
                case RIGHT:
                    if (snake.getSnake()[0].getX() > gpp.X - 3) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getX() == gpp.X - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY())))) {
                        return true;
                    }
                    break;
            }
            return false;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsDanger2StepsAhead";
        }

        @Override
        public Node copy() {
            return new IsDanger2StepsAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsDangerWithObstcles2StepsAhead extends Node {

        GPParameter gpp;

        public IsDangerWithObstcles2StepsAhead(GPParameter p_gpp) {
            super();
            type = LOGICALTERMINAL;
            gpp = p_gpp;
        }

        @Override
        public Boolean f(Snake snake) {

            switch (snake.getDirection()) {
                case UP:
                    if (snake.getSnake()[0].getY() < 2) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getY() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)))
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2)))) {
                        return true;
                    }
                    break;
                case DOWN:
                    if (snake.getSnake()[0].getY() > gpp.Y - 3) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getY() == gpp.Y - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2)
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2)))
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2))
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2))) {
                        return true;
                    }
                    break;
                case LEFT:
                    if (snake.getSnake()[0].getX() < 2) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getX() == 0)
                            || (snake.isOccupied(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())))
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY())))) {
                        return true;
                    }
                    break;
                case RIGHT:
                    if (snake.getSnake()[0].getX() > gpp.X - 3) {
                        return true;
                    }
                    if ((snake.getSnake()[0].getX() == gpp.X - 1)
                            || (snake.isOccupied(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY())
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY())))
                            || (snake.isOccupiedObstacles(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY()))
                            && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY()))) {
                        return true;
                    }
                    break;
            }
            return false;
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public String name() {
            return "IsDangerWithObstcles2StepsAhead";
        }

        @Override
        public Node copy() {
            return new IsDangerWithObstcles2StepsAhead(gpp);
        }

        @Override
        public Node newNode() {
            return copy();
        }

        @Override
        public String print(String tree) {

            tree = tree.concat(this.name());

            return tree;
        }
    }

    public static class IsObstacles extends Node {

        public IsObstacles() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {
            switch (snake.getDirection()) {
                case UP:
//                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() - 1));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 1));

                case DOWN:
//                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() + 1));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 1));

                case LEFT:
//                    return snake.isOccupied(snake.getObstaclesUp()[snake.getSnake()[0].getX()].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getObstaclesUp()[snake.getSnake()[0].getX()].getX() - 1, snake.getSnake()[0].getY()));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 1, snake.getSnake()[0].getY()));

                case RIGHT:
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 1, snake.getSnake()[0].getY()));
                default:
                    return false;
            }
        }

        @Override
        public String name() {
            return "IsObstacles";
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public Node copy() {
            return new IsObstacles();
        }

        @Override
        public String print(String tree) {
            tree = tree.concat(this.name());
            return tree;
        }

    }

    public static class IsObstacles2Step extends Node {

        public IsObstacles2Step() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {
            switch (snake.getDirection()) {
                case UP:
//                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() - 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() - 1));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() - 2));

                case DOWN:
//                    return snake.isOccupied(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() + 1) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getObstaclesUp()[snake.getSnake()[0].getY()].getY() + 1));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX(), snake.getSnake()[0].getY() + 2));

                case LEFT:
//                    return snake.isOccupied(snake.getObstaclesUp()[snake.getSnake()[0].getX()].getX() - 1, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getObstaclesUp()[snake.getSnake()[0].getX()].getX() - 1, snake.getSnake()[0].getY()));
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() - 2, snake.getSnake()[0].getY()));

                case RIGHT:
                    return snake.isOccupiedObstacles(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY()) && !snake.getFood().equals(new Cordinate(snake.getSnake()[0].getX() + 2, snake.getSnake()[0].getY()));
                default:
                    return false;
            }
        }

        @Override
        public String name() {
            return "IsObstacles2Step";
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public Node copy() {
            return new IsObstacles();
        }

        @Override
        public String print(String tree) {
            tree = tree.concat(this.name());
            return tree;
        }

    }

    public static class IsFoodInRow extends Node {

        public IsFoodInRow() {
            super();
            type = LOGICALTERMINAL;
        }

        @Override
        public Boolean f(Snake snake) {

            for (int i = 0; i < snake.getMaze()[snake.getSnake()[0].getX()].length; i++) {
                if (snake.getMaze()[i][snake.getSnake()[0].getY()] == 2) {
                    System.out.println("True");
                    return true;
                }
                 
            }
          

            return false;
        }

        @Override
        public String name() {
            return "IsFoodInRow";
        }

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public Node copy() {
            return new IsFoodInRow();
        }

        @Override
        public String print(String tree) {
            tree = tree.concat(this.name());
            return tree;
        }
    }
}
