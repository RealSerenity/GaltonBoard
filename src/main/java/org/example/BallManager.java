package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallManager {
    Random rand = new Random();
    int [] cells;
    int numThread;

    BallManager(int numThread, int[] cells){
        this.numThread = numThread;
        this.cells = cells;
    }

    public void startGaltonBoard(){
        List<Ball> balls = new ArrayList<>();
        for (int i=0; i<16;i++){
            balls.add(new Ball(cells, rand));
        }
        for (Ball ball : balls) {
            ball.start();
        }
        for (int i=0; i<(numThread/16)-1;i++) {
            for (Ball ball : balls) {
                ball.run();
            }
        }
        if(numThread % 16 != 0){
            for (int i=0; i<numThread % 16;i++){
                balls.get(i).run();
            }
        }
    }

}
