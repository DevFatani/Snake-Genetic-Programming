package GPWorkStation.view;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GraphWriter extends JPanel{
    int xMax;
    int yMax;
    int gen;
    String yAxis,xAxis;
    int margins = 100;
    String data1Name;
    String data2Name;
    String title;

    ArrayList<Double> data1 = new ArrayList<>();
    ArrayList<Double> data2 = new ArrayList<>();

    public GraphWriter(int xMax, int yMax, ArrayList<Double> data1, ArrayList<Double> data2) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.data1 = data1;
        this.data2 = data2;
    }

    public GraphWriter(int xMax, int yMax,String xAxis,String yAxis) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public GraphWriter(String xAxis,String yAxis) {
        this.xMax = 500;
        this.yMax = 500;
        this.xAxis = xAxis;
        this.yAxis = yAxis;

    }

    public GraphWriter(String xAxis,String yAxis,String data1Name) {
        this.xMax = 500;
        this.yMax = 500;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.data1Name = data1Name;
        this.data2Name = "";
    }

    public GraphWriter(String xAxis,String yAxis,String data1Name,String data2Name,String title) {
        this.xMax = 500;
        this.yMax = 500;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.data1Name = data1Name;
        this.data2Name = data2Name;
        this.title = title;
    }

    public static void main(String[] args) {

        example();

//        GraphWriter gw = new GraphWriter("Generations","Fitness","Best Ind Fitness","Gen Avg Fitness","Evolution chart");
//
////        gw.data1.add(0.0);gw.data1.add(0.1);gw.data1.add(0.2);gw.data1.add(0.3);gw.data1.add(0.4);gw.data1.add(0.5);
////        gw.data1.add(0.6);gw.data1.add(0.7);gw.data1.add(0.8);gw.data1.add(0.9);gw.data1.add(1.0);
//
//        gw.data1.add(0.0);gw.data1.add(10.0);gw.data1.add(20.0);gw.data1.add(30.0);gw.data1.add(40.0);gw.data1.add(50.0);
//        gw.data1.add(60.0);gw.data1.add(70.0);gw.data1.add(80.0);gw.data1.add(90.0);gw.data1.add(100.0);
//        gw.data1.add(6.0);gw.data1.add(7.0);gw.data1.add(8.0);gw.data1.add(9.0);gw.data1.add(10.0);
//        gw.data1.add(6.0);gw.data1.add(7.0);gw.data1.add(8.0);gw.data1.add(9.0);gw.data1.add(10.0);
//
//
////        gw.data1.add(1000.0);gw.data1.add(1010.0);gw.data1.add(1200.0);gw.data1.add(1300.0);gw.data1.add(1350.0);
////        gw.data1.add(1300.0);gw.data1.add(1330.0);gw.data1.add(1440.0);gw.data1.add(1220.0);gw.data1.add(1560.0);
////        gw.data1.add(1580.0);gw.data1.add(1550.0);gw.data1.add(1600.0);gw.data1.add(1560.0);gw.data1.add(1540.0);
////        gw.data1.add(1660.0);gw.data1.add(1630.0);gw.data1.add(1670.0);gw.data1.add(1650.0);gw.data1.add(1640.0);
////        gw.data1.add(1610.0);gw.data1.add(1590.0);gw.data1.add(1640.0);gw.data1.add(1690.0);gw.data1.add(1730.0);
////        gw.data1.add(1790.0);gw.data1.add(1770.0);gw.data1.add(1810.0);gw.data1.add(1830.0);gw.data1.add(1840.0);
////        gw.data1.add(1820.0);gw.data1.add(1870.0);gw.data1.add(1890.0);gw.data1.add(1860.0);gw.data1.add(1910.0);
////        gw.data1.add(2000.0);gw.data1.add(2010.0);gw.data1.add(1990.0);gw.data1.add(1980.0);gw.data1.add(2030.0);
////        gw.data1.add(2050.0);gw.data1.add(2020.0);gw.data1.add(2010.0);gw.data1.add(2030.0);gw.data1.add(2050.0);
////        gw.data1.add(2040.0);gw.data1.add(2070.0);gw.data1.add(2100.0);gw.data1.add(2110.0);gw.data1.add(2090.0);
////        gw.data1.add(39040.0);
//
//        for(double i=1;i<=gw.data1.size();i++){
//            gw.data2.add(i);
//        }
//
//        gw.paint();
//
//        gw.printImage("test.png");
    }

    public static void example() {
        GraphWriter gw = new GraphWriter("Generations","Fitness","Best Ind Fitness","","Evolution chart");
        Random r = new Random();
        gw.paint();
        for(int i=0;i<1000;i+=10){
            gw.addData1(r.nextInt(10)+i);
            gw.repaint();
            try{
                Thread.sleep(300);
            }
            catch(Exception ex){}
        }
        gw.printImage("test.png");
    }

    public void paint(){
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.setSize(xMax+(margins*2),yMax+(margins*3));
        f.setLocation(10,10);
        f.setVisible(true);
    }

    public void setData1(ArrayList<Double> data1) {
        this.data1 = data1;
    }

    public void addData1(double d) {
        this.data1.add(d);
    }

    public void setData2(ArrayList<Double> data2) {
        this.data2 = data2;
    }



    public void addData2(double d) {
        this.data2.add(d);
    }


    protected void paintComponent(Graphics g) {
        for(int i=0;i<=100;i+=10){
            if(i >= data1.size()){
                gen = i;
                break;
            }
        }
//        if(gen < data1.size())
//            gen = data1.size();
        super.paintComponent(g);

//        this.setSize(xMax+100,yMax+100);

        g.setColor(new Color(255,255,255));
        for(int i=margins+50;i<=xMax+margins;i+=50){
            g.drawLine(i,yMax+margins,i,+margins);
        }

        for(int i=50;i<=yMax;i+=50){
            g.drawLine(margins,yMax+margins-i,margins+xMax,yMax+margins-i);
        }

        g.setColor(Color.BLACK);

        g.drawLine(margins,yMax+margins,xMax+margins,yMax+margins);
        g.drawLine(margins,margins,margins,yMax+margins);


//        g.drawLine(margins,yMax+margins,margins,yMax+margins+10);
        for(int i=margins;i<=xMax+margins;i+=50){
            g.drawLine(i,yMax+margins,i,yMax+margins+10);
        }

        for(int i=0;i<=yMax;i+=50){
            g.drawLine(margins,yMax+margins-i,margins-10,yMax+margins-i);
        }

//        this.setSize(xMax+100,yMax+100);

//        g.drawLine(50,yMax+50,xMax+50,yMax+50);
//        g.drawLine(50,50,50,yMax+50);

//        g.drawString("1",margins-5,yMax+margins+25);
//
        int xSector;
//        if(gen <= 10){
//            for(int i=margins+50,j=2;i<xMax+margins && j<=gen;i+=50,j++){
//                g.drawString(String.valueOf(j),i-5,yMax+margins+25);
//            }
//            xSector = 1;
//        }
//        else{

            xSector = gen/10;

            for(int i=margins,j=0;i<=xMax+margins;i+=50,j++){

                g.drawString(String.valueOf((xSector*j)+1),i-5,yMax+margins+25);
            }
//        }

        g.drawString(yAxis,10,margins-10);
        g.drawString(xAxis,((xMax+margins)/2)-xAxis.length()/2,yMax+margins+50);

        double maxScore=0;
        double max1 = 0;
        double max2 = 0;

        for(double t:data1){
            max1 = Math.max(t,max1);
        }

        for(double t:data2){
            max2 = Math.max(t,max2);
        }

        double max =  Math.max(max1,max2);

        if(max <= 1000){
            for(int i=1;i<=1000;i*=10){
                if(i >= max){
                    maxScore = i;
                    break;
                }
            }
        }
        else{
            for(int i=5000;i<=100000;i+=5000){
                if(i >= max){
                    maxScore = i;
                    break;
                }
            }
        }

        int maxGen1 = 0;
        int maxGen2 = 0;
        if(xSector != 0){
            maxGen1 = data1.indexOf(max1)*(50/xSector)+margins;
            maxGen2 = data2.indexOf(max2)*(50/xSector)+margins;
        }

        double ySector = maxScore/10;

        for(int i=0,j=0;i<=yMax;i+=50,j++){

            String str = String.valueOf(ySector*j);
            if(str.charAt(0) != '0'){
                str = str.substring(0,str.length()-2);
            }
            if(str.length() > 3 & str.charAt(0) == '0'){
                g.drawString(str.substring(0,3),margins-45,yMax+margins+5-i);
            }
            else
                g.drawString(str,margins-45,yMax+margins+5-i);
        }

        g.setColor(Color.PINK);
        {
            Double d1 = max1 * (10/maxScore);
            Double d2 = max1 * (10/maxScore);

            double r1 = d1-d1.intValue();
            double r2 = d2-d2.intValue();

            int x1 = margins;
            int y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins);       //((yMax - (d1/10))+margins);

            int x2 = 500+margins;
            int y2 = (int)(yMax-(d2.intValue()*50+50*r2)+margins);

//            g.drawLine(x1,y1,x2,y2);

            String str = String.valueOf(max1);
            if(str.length() > 4 & str.charAt(0) == '0'){
                str = str.substring(0,4);
            }

            String st = "MAX = "+str;
            d1 = max1 * (10/maxScore);
            r1 = d1-d1.intValue();

            x1 = maxGen1-st.length()/2;
            y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins-15);
            g.drawString(st,x1,y1);
        }

        if(data2.size() > 0){
            g.setColor(new Color(173,216,230));
            {
                Double d1 = max2 * (10/maxScore);
                Double d2 = max2 * (10/maxScore);

                double r1 = d1-d1.intValue();
                double r2 = d2-d2.intValue();

                int x1 = margins;
                int y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins);       //((yMax - (d1/10))+margins);

                int x2 = 500+margins;
                int y2 = (int)(yMax-(d2.intValue()*50+50*r2)+margins);

//                g.drawLine(x1,y1,x2,y2);

                String str = String.valueOf(max2);
                if(str.length() > 4 & str.charAt(0) == '0'){
                    str = str.substring(0,4);
                }
                String st = "MAX = "+str;
                d1 = max2 * (10/maxScore);
                r1 = d1-d1.intValue();

                x1 = maxGen2-st.length()/2;
                y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins-15);
                g.drawString(st,x1,y1);
            }
        }

        g.setColor(Color.RED);
        for(int i=0;i<data1.size()-1;i++){
            Double d1 = data1.get(i) * (10/maxScore);
            Double d2 = data1.get(i+1) * (10/maxScore);

            double r1 = d1-d1.intValue();
            double r2 = d2-d2.intValue();

            int x1 = ((i*(50/xSector))+margins);
            int y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins);       //((yMax - (d1/10))+margins);

            int x2 = (((i+1)*(50/xSector))+margins);
            int y2 = (int)(yMax-(d2.intValue()*50+50*r2)+margins);      //((yMax - (d2/10))+margins);
            g.drawLine(x1,y1,x2,y2);
            g.fillOval(x1-2,y1-2,4,4);
            g.fillOval(x2-2,y2-2,4,4);
        }

        if(data2.size() > 0){
            g.setColor(Color.BLUE);
            for(int i=0;i<data2.size()-1;i++){
                Double d1 = data2.get(i) * (10/maxScore);
                Double d2 = data2.get(i+1) * (10/maxScore);

                double r1 = d1-d1.intValue();
                double r2 = d2-d2.intValue();

                int x1 = ((i*(50/xSector))+margins);
                int y1 = (int)(yMax-(d1.intValue()*50+50*r1)+margins);       //((yMax - (d1/10))+margins);

                int x2 = (((i+1)*(50/xSector))+margins);
                int y2 = (int)(yMax-(d2.intValue()*50+50*r2)+margins);      //((yMax - (d2/10))+margins);
                g.drawLine(x1,y1,x2,y2);
                g.fillOval(x1-2,y1-2,4,4);
                g.fillOval(x2-2,y2-2,4,4);
            }
        }

        if(data1Name!= null && data1Name.length() > 0){
            g.setColor(Color.RED);
            g.drawString(data1Name,margins,yMax+margins+70);
            g.drawLine(margins ,yMax+margins+78,margins +100,yMax+margins+78);
            g.drawLine(margins ,yMax+margins+79,margins +100,yMax+margins+79);
            g.drawLine(margins ,yMax+margins+80,margins +100,yMax+margins+80);
        }

        if(data2Name!= null && data2Name.length() > 0){
            g.setColor(Color.BLUE);
            g.drawString(data2Name,margins,yMax+margins+100);
            g.drawLine(margins ,yMax+margins+108,margins + 100,yMax+margins+108);
            g.drawLine(margins ,yMax+margins+109,margins + 100,yMax+margins+109);
            g.drawLine(margins ,yMax+margins+110,margins + 100,yMax+margins+110);
        }

        if(title != null && title.length() > 0){
            g.setColor(Color.BLACK);
            g.drawString(title, ((xMax + margins) / 2) - title.length() / 2, 25);
        }

    }
    
    public void printImage(String name){
        BufferedImage bi = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        paint(g);
        g.dispose();
        try{
            ImageIO.write(bi, "png", new File(name));
        }
        catch (Exception e) {}
    }

}
