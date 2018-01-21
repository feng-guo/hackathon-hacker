package game;

import display.Display;
import listeners.MouseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    Display display;
    JFrame frame;
    Canvas canvas;
    MouseManager mouseManager;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    private int width = 600, height = 400;

    public Game() {
        display = new Display("Hackathon Hackers", width, height);
    }


    private void init() {

    }

    @Override
    public void run() {

        //init();

        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;


        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += (now - lastTime);
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public Display getDisplay() {
        return display;
    }

    public synchronized void start() {
        if(running) {
            return;
        }
        running = true;
        thread = new Thread(this);

        // this will call the run method
        thread.start();
    }

    private void tick() {

    }

    private void render() {


        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // clear
        g.clearRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 600, 400);

        bs.show();
        g.dispose();

    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public synchronized void stop() {
        if(!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
