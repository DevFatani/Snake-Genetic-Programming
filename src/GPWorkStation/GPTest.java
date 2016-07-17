package GPWorkStation;

import static GPWorkStation.CreateTree.createTree;
import snake.*;

public class GPTest extends CreateTree /*implements Constants*/ {
    /*
     1-
     "ifte(IsWallLeft,ifte(IsFoodRight,ifte(IsDangerAhead,ifte(OR(IsWall2StepsAhead,IsWallAhead),ifte(IsFoodLeft,ifte(IsWallLeft,TurnRight,TurnLeft),ifte(IsWall2StepsAhead,Ahead,TurnLeft)),ifte(NOT(IsFoodRight),TurnRight,TurnRight)),ifte(IsMovingDown,ifte(IsBodyLeft,ifte(IsWallRight,TurnLeft,TurnRight),ifte(IsFoodRight,TurnLeft,TurnRight)),ifte(IsMovingUp,ifte(IsFoodLeft,TurnRight,Ahead),TurnRight))),ifte(IsMovingUp,TurnLeft,ifte(NOT(IsWall2StepsAhead),TurnRight,Ahead))),ifte(NOT(NOT(IsDanger2StepsAhead)),ifte(OR(IsFoodLeft,AND(IsBodyRight,NOT(IsBodyAhead))),ifte(IsBodyAhead,ifte(IsMovingLeft,ifte(IsFoodRight,TurnLeft,TurnLeft),ifte(IsDanger2StepsAhead,TurnLeft,TurnLeft)),ifte(IsDanger2StepsAhead,Ahead,ifte(IsBodyRight,TurnLeft,TurnLeft))),ifte(IsBodyAhead,ifte(IsWallRight,ifte(IsWallRight,TurnLeft,TurnRight),ifte(IsMovingLeft,TurnRight,Ahead)),ifte(IsWallAhead,ifte(IsWallRight,TurnLeft,TurnRight),ifte(IsMovingDown,Ahead,TurnLeft)))),ifte(OR(IsWallAhead,AND(AND(IsFoodLeft,IsFoodRight),AND(IsBodyLeft,IsMovingUp))),ifte(IsWallAhead,ifte(IsMovingUp,Ahead,ifte(IsMovingRight,Ahead,TurnLeft)),ifte(OR(IsDangerAhead,IsMovingLeft),ifte(IsDangerAhead,TurnRight,TurnRight),ifte(IsDangerAhead,TurnRight,Ahead))),ifte(NOT(IsMovingLeft),ifte(IsDanger2StepsAhead,ifte(IsMovingRight,TurnLeft,TurnLeft),ifte(IsWall2StepsAhead,TurnRight,Ahead)),ifte(NOT(IsDanger2StepsAhead),TurnLeft,ifte(IsDangerAhead,TurnRight,TurnRight))))))"
     2-ifte(OR(IsWallAhead,IsFoodInRow),ifte(OR(IsWallAhead,IsWallLeft),TurnRight,Ahead),Ahead)
     3-ifte(OR(IsWallAhead,IsFoodInRow),ifte(or(isfoodinrow,IsObstacles),ifte(or(ismovingup,ismovingdown),ifte(or(ismovingright,ismovingleft),turnright,turnright),ahead),turnright),Ahead)
    
     */
    
    
    public static void main(String[] args) {
        GPParameter gpp = new GPParameter();
        gpp.X = gpp.Y = 20;
        
        gpp.NUM_OBSTACLES = 3;
        gpp.SIZE_OF_OBSTACLES = 9;
        
//            /*gpp.SIZE_OBSTACLES_UP =  */gpp.SIZE_OBSTACLES_CENTER =  /*gpp.SIZE_OBSTACLES_DONW =*/ 16;


//              new GPTest(new StringBuffer("ifte(IsWallAhead,ifte(isFoodRight,TurnRight,ifte(isFoodLeft,TurnLeft,ifte(IsWallAhead,TurnRight,TurnLeft))),ifte(isFoodRight,TurnRight,Ahead))"), gpp);
//                      new GPTest(new StringBuffer("ifte(IsWallLeft,ifte(IsFoodAhead,ifte(IsMovingLeft,TurnRight,TurnRight),ifte(IsWall2StepsAhead,TurnLeft,ifte(IsWall2StepsAhead,TurnLeft,ifte(AND(IsBodyRight,IsBodyAhead),Ahead,ifte(IsDangerAhead,TurnRight,TurnRight))))),ifte(IsMovingUp,TurnLeft,ifte(IsObstacles,ifte(IsMovingLeft,ifte(IsFoodRight,ifte(IsFoodLeft,TurnLeft,TurnRight),TurnLeft),TurnLeft),ifte(IsBodyRight,ifte(IsMovingRight,ifte(IsDangerAhead,TurnLeft,Ahead),ifte(IsWall2StepsAhead,TurnLeft,Ahead)),ifte(AND(IsDangerWithObstaclesAhead,IsWall2StepsAhead),Ahead,ifte(IsWall2StepsAhead,Ahead,Ahead))))))"), gpp);

        String t= "ifte(OR(IsWallAhead,IsFoodInRow),ifte(or(isfoodinrow,IsObstacles),ifte(or(ismovingup,ismovingdown),ifte(or(ismovingright,ismovingleft),turnright,turnright),ahead),turnright),Ahead)";
//        new GPTest(new StringBuffer("ifte(OR(iswallahead,isfoodinrow),ifte(isfoodinrow,ifte(or(ismovingup,ismovingdown),ifte(or(ismovingright,ismovingleft),ifte(isbodyahead,turnleft,turnright),ifte(isdangerahead,turnleft,ifte(isbodyahead,turnleft,turnright))),turnright),turnright),Ahead)"), gpp);
                 
//        new GPTest(new StringBuffer("ifte(OR(IsWallAhead,IsFoodInRow),ifte(isfoodinrow,ifte(or(ismovingup,ismovingdown),ifte(or(ismovingright,ismovingleft),turnright,ifte(IsObstacles,turnright,turnright)),ahead),turnright),Ahead)"), gpp);
        new GPTest(new StringBuffer(t), gpp);

    }
    public GPTest(StringBuffer sTree, GPParameter gpp){
//        new Thread(){

//            @Override
//            public void run() {
             new SnakeGPRunTest(107, false, false, false, new ScoredType<>(createTree(sTree,gpp)), gpp).run();
//            }
            
//        }.start();
    }

//    public static void main(String[] args) {
//        StringBuffer sTree;
//
////        sTree = new StringBuffer("Ahead");
//        //the perfect controller
////        sTree = new StringBuffer("(ifFoodAhead(ifDangerAhead(left)(forward))(ifDangerAhead(ifDangerRight(left)(progn2(right)(ifFoodAhead(ifDangerRight(forward)(right)))(ifDangerRight(forward)(right))))(ifDangerRight(ifDangerLeft(forward)(left))(ifDangerLeft(right)(progn2(left)(ifFoodAhead(ifDangerLeft(right)(left))(ifDangerRight(left)(progn2(ifDangerAhead(right)(ifDangerLeft(right)(left)))(ifDangerRight(forward)(right))))))))))");
//        sTree = new StringBuffer(
//"ifte(IsWallRight,ifte(IsMovingDown,ifte(IsObstacles,ifte(IsFoodRight,ifte(IsWallLeft,ifte(IsFoodAhead,ifte(IsBodyRight,ifte(IsWall2StepsAhead,TurnLeft,ifte(AND(IsMovingLeft,IsBodyLeft),TurnRight,ifte(IsMovingDown,TurnLeft,TurnLeft))),ifte(IsBodyRight,ifte(IsWallAhead,ifte(IsDanger2StepsAhead,TurnLeft,Ahead),ifte(IsFoodLeft,TurnLeft,TurnLeft)),ifte(IsMovingDown,ifte(IsBodyRight,TurnLeft,Ahead),ifte(IsWallRight,TurnRight,TurnRight)))),ifte(AND(IsMovingLeft,OR(OR(IsBodyLeft,IsWall2StepsAhead),IsFoodRight)),ifte(AND(IsMovingLeft,IsDanger2StepsAhead),ifte(IsFoodAhead,ifte(IsBodyLeft,Ahead,TurnLeft),ifte(IsDanger2StepsAhead,Ahead,TurnLeft)),TurnLeft),TurnRight)),TurnLeft),ifte(IsBodyRight,TurnLeft,ifte(OR(IsFoodLeft,AND(IsBodyRight,IsDangerAhead)),ifte(IsFoodLeft,ifte(IsFoodLeft,ifte(IsWallLeft,ifte(IsObstacles,TurnLeft,TurnLeft),ifte(IsMovingDown,TurnLeft,Ahead)),ifte(IsWallLeft,ifte(IsDanger2StepsAhead,TurnLeft,TurnRight),ifte(IsObstacles,TurnLeft,Ahead))),ifte(IsDangerAhead,TurnLeft,TurnRight)),ifte(AND(IsDanger2StepsAhead,IsDanger2StepsAhead),ifte(IsMovingRight,ifte(IsBodyLeft,ifte(IsFoodLeft,Ahead,TurnRight),ifte(IsFoodAhead,TurnLeft,TurnRight)),TurnRight),ifte(IsWall2StepsAhead,ifte(IsBodyRight,ifte(IsMovingUp,Ahead,Ahead),ifte(IsBodyLeft,Ahead,TurnLeft)),ifte(OR(IsBodyRight,IsMovingRight),ifte(IsWallRight,TurnRight,TurnRight),TurnLeft)))))),ifte(NOT(IsMovingLeft),ifte(AND(IsMovingLeft,IsDanger2StepsAhead),ifte(IsBodyLeft,ifte(IsDanger2StepsAhead,ifte(IsDangerAhead,Ahead,ifte(AND(IsDanger2StepsAhead,IsFoodLeft),ifte(IsFoodLeft,TurnRight,TurnRight),Ahead)),Ahead),ifte(IsBodyAhead,TurnLeft,ifte(OR(IsWall2StepsAhead,IsBodyLeft),Ahead,TurnLeft))),ifte(IsWallAhead,ifte(AND(IsWall2StepsAhead,IsWall2StepsAhead),ifte(OR(OR(IsDanger2StepsAhead,IsMovingLeft),IsWall2StepsAhead),TurnRight,ifte(IsWallAhead,ifte(IsWallRight,TurnLeft,TurnLeft),ifte(IsFoodRight,TurnRight,Ahead))),ifte(NOT(AND(IsBodyLeft,IsWall2StepsAhead)),Ahead,ifte(IsWallLeft,ifte(IsWallLeft,TurnRight,Ahead),ifte(IsMovingRight,Ahead,Ahead)))),ifte(OR(IsMovingUp,NOT(IsFoodRight)),ifte(AND(IsDanger2StepsAhead,IsDanger2StepsAhead),ifte(IsMovingDown,ifte(IsMovingRight,TurnRight,TurnRight),TurnLeft),ifte(IsBodyRight,ifte(IsWallAhead,Ahead,TurnRight),ifte(IsDangerAhead,TurnRight,Ahead))),Ahead))),ifte(IsWallAhead,ifte(IsBodyRight,ifte(IsFoodLeft,ifte(IsWall2StepsAhead,ifte(IsMovingDown,ifte(IsMovingRight,Ahead,TurnRight),ifte(IsWallAhead,Ahead,TurnLeft)),ifte(NOT(IsWallRight),ifte(IsMovingRight,TurnRight,Ahead),ifte(IsDangerAhead,TurnRight,Ahead))),TurnRight),TurnRight),ifte(OR(OR(IsFoodLeft,IsFoodAhead),IsWallAhead),ifte(IsMovingUp,ifte(AND(OR(IsDanger2StepsAhead,IsMovingLeft),AND(IsMovingUp,IsWallAhead)),Ahead,ifte(IsMovingDown,Ahead,ifte(IsObstacles,TurnLeft,Ahead))),ifte(IsWall2StepsAhead,ifte(IsFoodLeft,ifte(IsObstacles,TurnLeft,Ahead),ifte(IsFoodLeft,Ahead,TurnLeft)),TurnLeft)),TurnRight)))),TurnLeft),ifte(IsObstacles,ifte(IsBodyLeft,TurnRight,ifte(IsFoodRight,TurnRight,TurnLeft)),ifte(IsObstacles,ifte(IsWallRight,ifte(NOT(IsMovingRight),Ahead,ifte(IsWallLeft,Ahead,Ahead)),ifte(NOT(IsBodyLeft),ifte(AND(AND(AND(IsBodyAhead,NOT(IsBodyRight)),IsMovingRight),IsDanger2StepsAhead),ifte(IsWallRight,ifte(IsFoodRight,ifte(NOT(IsFoodLeft),ifte(IsFoodLeft,Ahead,TurnLeft),Ahead),Ahead),ifte(IsWallLeft,ifte(IsDanger2StepsAhead,ifte(IsObstacles,TurnLeft,Ahead),ifte(IsMovingDown,Ahead,TurnLeft)),ifte(NOT(IsMovingUp),ifte(IsDangerAhead,TurnLeft,Ahead),ifte(IsMovingDown,Ahead,Ahead)))),ifte(IsFoodRight,Ahead,Ahead)),ifte(IsMovingRight,ifte(IsWallRight,TurnRight,ifte(IsDangerAhead,TurnLeft,ifte(NOT(IsMovingRight),Ahead,Ahead))),TurnLeft))),ifte(IsBodyAhead,ifte(IsBodyAhead,TurnLeft,ifte(IsFoodRight,ifte(IsDangerAhead,ifte(IsFoodLeft,ifte(NOT(IsFoodAhead),TurnLeft,ifte(IsWallRight,TurnLeft,TurnLeft)),ifte(AND(IsMovingLeft,IsWallLeft),ifte(IsObstacles,TurnRight,Ahead),ifte(IsWall2StepsAhead,Ahead,Ahead))),ifte(NOT(IsBodyRight),ifte(NOT(IsObstacles),ifte(IsObstacles,TurnLeft,TurnLeft),ifte(IsWallRight,TurnLeft,TurnRight)),TurnLeft)),ifte(IsBodyLeft,Ahead,ifte(IsMovingDown,ifte(IsBodyLeft,ifte(IsBodyAhead,TurnLeft,TurnLeft),ifte(IsMovingRight,TurnLeft,Ahead)),ifte(OR(IsWall2StepsAhead,IsMovingRight),ifte(IsFoodRight,Ahead,TurnLeft),ifte(IsMovingDown,Ahead,Ahead)))))),ifte(NOT(IsWall2StepsAhead),ifte(IsWallAhead,ifte(IsMovingUp,ifte(IsWall2StepsAhead,Ahead,ifte(IsBodyLeft,TurnRight,ifte(IsFoodRight,TurnRight,TurnLeft))),ifte(IsWallLeft,ifte(IsWallAhead,TurnLeft,ifte(IsWallAhead,Ahead,Ahead)),ifte(OR(IsWallLeft,IsMovingUp),ifte(IsWallRight,TurnLeft,TurnLeft),ifte(IsWall2StepsAhead,TurnRight,TurnRight)))),ifte(IsFoodLeft,ifte(IsFoodLeft,ifte(AND(IsBodyAhead,IsMovingUp),ifte(IsBodyRight,Ahead,Ahead),ifte(IsMovingUp,TurnRight,Ahead)),ifte(AND(IsObstacles,IsBodyRight),ifte(IsObstacles,Ahead,Ahead),TurnLeft)),Ahead)),Ahead)))))"
//        );
//        final ScoredType<Node> st = new ScoredType<>(createTree(sTree));
//
//        int players = 1;//?
//        try {
//            for (int i = 0; i < players; i++) {
////                new Thread() {
////                    @Override
////                    public void run() {
//                new SnakeGPRunTest(/*!true, !true, true,*/20, false, false, false, st).run();
////                    }
////
////                }.start();
////                new Snake3(st).run();
//            }
//            st.fitness = st.ss.mean();
//            st.bestScore = (int) st.ss.max();
//            st.ticks = st.ss.ticks();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        System.out.println("average = " + st.fitness);
//    }
}
