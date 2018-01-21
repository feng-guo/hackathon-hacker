package game;

import display.Display;

import javax.swing.*;
import java.awt.*;

public class Game implements Runnable{
    Display display;
    JFrame frame;
    Canvas canvas;

    public Game() {
        display = new Display("Hackathon Hackers", 600, 400);
    }

    @Override
    public void run() {

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
