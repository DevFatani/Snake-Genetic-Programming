/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPWorkStation;

import java.util.Random;

/**
 *
 * @author DevM0o0D
 */
public class GPParameter {

    public int popSize;
    
    public int nGens;
    
    public int NRuns;
    
    public int depth;
    
    public double mutProb;
    
    public double gMutProb;
    
    public int nParents = popSize;
    
    public int tournRate;
    
    public int reproductionRate = 1;

    Random r = new Random();
   
    static int repfreq = 1;
    
    int bestNodesSize = popSize;
    
    public static boolean scenario = false;
    
    public static boolean escape = false;
    
    public static int testTime = 200;
    
    public static int noOfScenarios = 30;
    
    public static boolean clearPills = true;
    
    public static boolean clearPowers = true;
    
    public static boolean setEdibleScore = false;
    
    public static int keyScenario = 20;

    public int X;
    
    public int Y;

    public int SIZE_OF_OBSTACLES;
    
    
    public int NUM_OBSTACLES;
}
