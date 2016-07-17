package GPWorkStation;

import snake.Snake;

public class LogicalOperators {

    public static class AND extends Binary {

        public AND() {
            super();
            type = LOGICALOPERATOR;
            types[0]=LOGICALOPERATOR;
            types[1]=LOGICALOPERATOR;
        }

        @Override
        public Boolean f(Snake snake) {
            return (Boolean)na[0].f(snake) == true ? true : false && (Boolean)na[1].f(snake) == true;
        }
        @Override
        public int arity() {
            return 2;
        }
        @Override
        public String name() {
            return "AND";
        }
    }

    public static class OR extends Binary {

        public OR() {
            super();
            type = LOGICALOPERATOR;
            types[0]=LOGICALOPERATOR;
            types[1]=LOGICALOPERATOR;
        }

        @Override
        public Boolean f(Snake snake) {
            return (Boolean)na[0].f(snake) ? true : false || (Boolean)na[1].f(snake);
        }
        @Override
        public int arity() {
            return 2;
        }
        @Override
        public String name() {
            return "OR";
        }
    }

     public static class NOT extends Unary {

        public NOT() {
            super();
            type = LOGICALOPERATOR;
            types[0]=LOGICALOPERATOR;
        }

        @Override
        public Boolean f(Snake snake) {

            return !(Boolean)na[0].f(snake);
        }

        @Override
        public String name() {
            return "NOT";
        }
    }
}
