package GPWorkStation;

import snake.Snake;

public class Functions {

    public static class Ifte extends Node {

        public Ifte() {
            super();
            type = FUNCTION;//1
            types[0] = LOGICALOPERATOR;//5
            types[1] = FUNCTION;//1
            types[2] = FUNCTION;//1
        }

        @Override
        public Double f(Snake snake) {
            ConsFigures.currentFunc = this.ID;
            visits++;
            try {
                if ((Boolean) na[0].f(snake)) {
                    return (Double) na[1].f(snake);
                } else {
                    return (Double) na[2].f(snake);
                }
            } catch (Exception ex) {
                return (Double) na[1].f(snake);
            }
        }

        @Override
        public int arity() {
            return 3;
        }

        @Override
        public String name() {
            return "ifte";
        }

        @Override
        public String print(String tree) {
            tree = tree.concat(this.name() + "(");
            tree = na[0].print(tree);
            tree = tree.concat(",");
            tree = na[1].print(tree);
            tree = tree.concat(",");
            tree = na[2].print(tree);
            tree = tree.concat(")");
            return tree;
        }
    }

    public static class Progn2 extends Node {

        public Progn2() {
            super();
            type = FUNCTION;
            types[0] = FUNCTION;
            types[1] = FUNCTION;
        }

        @Override
        public Double f(Snake snake) {
            ConsFigures.currentFunc = this.ID;
            visits++;
            na[0].f(snake);
            return (Double) na[1].f(snake);
        }

        @Override
        public int arity() {
            return 2;
        }

        @Override
        public String name() {
            return "Progn2";
        }

        @Override
        public String print(String tree) {
            tree = tree.concat(this.name() + "(");
            tree = na[0].print(tree);
            tree = tree.concat(",");
            tree = na[1].print(tree);
            tree = tree.concat(")");

            return tree;
        }
    }
}
