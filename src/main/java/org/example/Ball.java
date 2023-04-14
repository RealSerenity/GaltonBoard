package org.example;

import java.util.Random;

class Ball extends Thread{
    int [] cells;
    Random random;

    public Ball(int [] cells, Random random){
        this.random = random;
        this.cells=cells;
    }

    @Override
    public void run() {
            int cellIndex = calculateAllTheWay();
            synchronized (cells){
                cells[cellIndex]++;
           }
    }

    private int leftOrRight(){
        Random random = new Random();
        int i = random.nextInt(2);
        return i==1 ? -1 : 1;
    }

    private  int calculateAllTheWay(){
        int cellIndex = cells.length-1;
        for (int i =0; i< cells.length-1;i++){
            cellIndex += leftOrRight();
        }
        return cellIndex/2;
    }
}
