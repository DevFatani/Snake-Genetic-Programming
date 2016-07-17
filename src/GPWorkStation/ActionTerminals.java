package GPWorkStation;

import snake.Snake;

public class ActionTerminals {

    static final int RIGHT=1;
    static final int LEFT=2;
    static final int UP=3;
    static final int DOWN=4;

    public static class TurnLeft extends Node {

        public TurnLeft() {
            super();
            type = TERMINAL;//1
        }

        @Override
        public Double f(Snake snake) {
            int direction = snake.getDirection();

            switch(direction){
                case RIGHT:
                    direction = UP;
                    break;
                case LEFT:
                    direction = DOWN;
                    break;
                case UP:
                    direction = LEFT;
                    break;
                case DOWN:
                    direction = RIGHT;
                    break;
            }

            snake.keyEvent(direction);
            snake.move();

            return snake.getScore();

        }

        @Override
        public int arity() {
            return 0;
        }
        @Override
        public String name() {
            return "TurnLeft";
        }

        @Override
        public Node copy() {
            return new TurnLeft();
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

    public static class TurnRight extends Node {

        public TurnRight() {
            super();
            type = TERMINAL;
        }

        public Double f(Snake snake) {
            int direction = snake.getDirection();

            switch(direction){
                case RIGHT:
                    direction = DOWN;
                    break;
                case LEFT:
                    direction = UP;
                    break;
                case UP:
                    direction = RIGHT;
                    break;
                case DOWN:
                    direction = LEFT;
                    break;
            }

            snake.keyEvent(direction);
            snake.move();

            return snake.getScore();

        }

        @Override
        public int arity() {
            return 0;
        }
        @Override
        public String name() {
            return "TurnRight";
        }

        @Override
        public Node copy() {
            return new TurnRight();
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

    public static class Ahead extends Node {

        public Ahead() {
            super();
            type = TERMINAL;
        }

        @Override
        public Double f(Snake snake) {
            int direction = snake.getDirection();

            if(direction == 0)
                snake.keyEvent(RIGHT);

            snake.move();

            return snake.getScore();

        }

        @Override
        public int arity() {
            return 0;
        }
        @Override
        public String name() {
            return "Ahead";
        }

        @Override
        public Node copy() {
            return new Ahead();
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
}