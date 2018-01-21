package game;

import display.Display;
import graphics.Assets;
import listeners.MouseManager;
import producers.*;

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

    private Producer badIdea;
    private Producer brokenAPI;
    private Producer faultyHardware;
    private Producer slowInternet;
    private Producer tiredHacker;

    private double middleVariable;

    public static int money;

    public Game() {
        display = new Display("Hackathon Hackers", width, height);
        init();
    }


    private void init() {
        Assets.init();
        money = 0;
        middleVariable = 0;
        mouseManager = new MouseManager();
        badIdea = new BadIdea();
        brokenAPI = new BrokenAPI();
        faultyHardware = new FaultyHardware();
        slowInternet = new SlowInternet();
        tiredHacker = new TiredHacker();
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseListener(mouseManager);
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
        middleVariable += Math.ceil((brokenAPI.getProductionRate() + badIdea.getProductionRate() + faultyHardware.getProductionRate() + slowInternet.getProductionRate() + tiredHacker.getProductionRate())/60);
        if (middleVariable > 1) {
            money += ((int) middleVariable);
            middleVariable -= Math.floor(middleVariable);
        }

        if (mouseManager.checkMouseClick) {
            if (mouseManager.getMouseX() < 300) {
                money++;
                mouseManager.checkMouseClick = false;
            } else if (mouseManager.getMouseX() > 300) {
                if (mouseManager.getMouseY() > 332) {
                    faultyHardware.buildProducer();
                } else if (mouseManager.getMouseY() > 264) {
                    brokenAPI.buildProducer();
                } else if (mouseManager.getMouseY() > 196) {
                    tiredHacker.buildProducer();
                } else if (mouseManager.getMouseY() > 128) {
                    badIdea.buildProducer();
                } else if (mouseManager.getMouseY() > 60) {
                    slowInternet.buildProducer();
                }
                mouseManager.checkMouseClick = false;
            }
        }
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
        g.drawImage(Assets.backgroundImage, 0, 0, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Tears produced: " + money, 30, 350);
        g.drawString(slowInternet.getCurrentPrice()+"", 400, 110);
        g.drawString(badIdea.getCurrentPrice()+"", 400, 178);
        g.drawString(tiredHacker.getCurrentPrice()+"", 400, 246);
        g.drawString(brokenAPI.getCurrentPrice()+"", 400, 314);
        g.drawString(faultyHardware.getCurrentPrice()+"", 400, 382);

        g.drawString(slowInternet.getNumberOfUnits()+"", 550, 110);
        g.drawString(badIdea.getNumberOfUnits()+"", 550, 178);
        g.drawString(tiredHacker.getNumberOfUnits()+"", 550, 246);
        g.drawString(brokenAPI.getNumberOfUnits()+"", 550, 314);
        g.drawString(faultyHardware.getNumberOfUnits()+"", 550, 382);

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
