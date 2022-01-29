package com.example.mill.machine;

import com.example.mill.bean.Flour;
import com.example.mill.bean.Millet;
import com.example.mill.engine.Engine;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class MillStone extends Machine {
    private final Logger logger = LoggerFactory.getLogger(MillStone.class);
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private final Engine engine;
    private final Queue <Millet> mallets;
    private final Queue <Flour> floursQueue;

    public MillStone(Engine engine, Queue<Millet> mallets, Queue<Flour> floursQueue) {
        this.engine = engine;
        this.mallets = mallets;
        this.floursQueue = floursQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            if(engine.getPower()>0){

                try {
                    engine.decPower(1);
                    this.executor.submit(() ->{
                        Millet millet = mallets.poll();
                        if (millet !=null ){
                            floursQueue.offer(new Flour());
                            logger.info("Flour: " + floursQueue.size());
                        }
                        logger.info("Power left" + engine.getPower());
                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                  logger.error(e.getMessage());
                }
            }

        }
    }
}
