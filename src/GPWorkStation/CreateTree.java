package GPWorkStation;

public class CreateTree {

    static Node createTree(StringBuffer tree, GPParameter gpp) {
        int e1 = tree.indexOf("(");
        e1 = e1 < 0 ? 10000 : e1;
        int e2 = tree.indexOf(",");
        e2 = e2 < 0 ? 10000 : e2;
        int index = Math.min(e1, e2);
        String str = tree.toString();
        if (index < 10000) {
            str = tree.substring(0, Math.min(e1, e2));
        }
        while (str.contains(")")) {
            str = str.substring(0, str.length() - 1);
        }

        e1 = tree.indexOf("(");
        e1 = e1 < 0 ? 10000 : e1;
        e2 = tree.indexOf(",");
        e2 = e2 < 0 ? 10000 : e2;
        index = Math.min(e1, e2) + 1;
        if (index < 10000) {
            tree.replace(0, tree.length() - 1, tree.substring(index));
        }

        Node node = null;

        if (str.equalsIgnoreCase("Ifte")) {
            node = new Functions.Ifte();
        } else if (str.equalsIgnoreCase("Progn2")) {
            node = new Functions.Progn2();
        } else if (str.equalsIgnoreCase("And")) {
            node = new LogicalOperators.AND();
        } else if (str.equalsIgnoreCase("Or")) {
            node = new LogicalOperators.OR();
        } else if (str.equalsIgnoreCase("NOT")) {
            node = new LogicalOperators.NOT();
        } //        ActionTerminals
        else if (str.equalsIgnoreCase("TurnLeft")) {
            node = new ActionTerminals.TurnLeft();
        } else if (str.equalsIgnoreCase("TurnRight")) {
            node = new ActionTerminals.TurnRight();
        } else if (str.equalsIgnoreCase("Ahead")) {
            node = new ActionTerminals.Ahead();
        } //        LogicalTerminals
        else if (str.equalsIgnoreCase("IsDangerAhead")) {
            node = new LogicalTerminals.IsDangerAhead(gpp);
        } else if (str.equalsIgnoreCase("IsDanger2StepsAhead")) {
            node = new LogicalTerminals.IsDanger2StepsAhead(gpp);
        } else if (str.equalsIgnoreCase("IsWallAhead")) {
            node = new LogicalTerminals.IsWallAhead(gpp);
        } else if (str.equalsIgnoreCase("IsWall2StepsAhead")) {
            node = new LogicalTerminals.IsWall2StepsAhead(gpp);
        } else if (str.equalsIgnoreCase("IsWallLeft")) {
            node = new LogicalTerminals.IsWallLeft(gpp);
        } else if (str.equalsIgnoreCase("IsWallRight")) {
            node = new LogicalTerminals.IsWallRight(gpp);
        } else if (str.equalsIgnoreCase("IsFoodAhead")) {
            node = new LogicalTerminals.IsFoodAhead(gpp);
        } else if (str.equalsIgnoreCase("IsFoodRight")) {
            node = new LogicalTerminals.IsFoodRight(gpp);
        } else if (str.equalsIgnoreCase("IsFoodLeft")) {
            node = new LogicalTerminals.IsFoodLeft(gpp);
        } else if (str.equalsIgnoreCase("IsMovingUp")) {
            node = new LogicalTerminals.IsMovingUp();
        } else if (str.equalsIgnoreCase("IsMovingDown")) {
            node = new LogicalTerminals.IsMovingDown();
        } else if (str.equalsIgnoreCase("IsMovingRight")) {
            node = new LogicalTerminals.IsMovingRight();
        } else if (str.equalsIgnoreCase("IsMovingLeft")) {
            node = new LogicalTerminals.IsMovingLeft();
        } else if (str.equalsIgnoreCase("IsBodyLeft")) {
            node = new LogicalTerminals.IsBodyLeft();
        } else if (str.equalsIgnoreCase("IsBodyRight")) {
            node = new LogicalTerminals.IsBodyRight();
        } else if (str.equalsIgnoreCase("IsBodyAhead")) {
            node = new LogicalTerminals.IsBodyAhead();
        } else if (str.equalsIgnoreCase("IsObstacles")) {
            node = new LogicalTerminals.IsObstacles();
        } else if (str.equalsIgnoreCase("IsDangerWithObstaclesAhead")) {
            node = new LogicalTerminals.IsDangerWithObstaclesAhead(gpp);
        } else if (str.equalsIgnoreCase("IsDangerWithObstcles2StepsAhead")) {
            node = new LogicalTerminals.IsDangerWithObstcles2StepsAhead(gpp);
        }else if(str.equalsIgnoreCase("IsObstacles2Step")){
            node = new LogicalTerminals.IsObstacles2Step();
        }else if(str.equalsIgnoreCase("IsFoodInRow")){
            node = new LogicalTerminals.IsFoodInRow();
        }else {
            System.out.println("no function was found for (" + str + ")");
        }

        for (int i = 0; i < node.arity(); i++) {
            node.na[i] = createTree(tree, gpp);
        }
        return node;
    }
}
