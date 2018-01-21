package producers;

import game.Game;

import java.awt.image.BufferedImage;

public abstract class Producer {
    private String name;
    private int BASE_PRICE;
    private double PRICE_RATE;
    private int BASE_PRODUCTION_RATE;
    private int numberOfUnits;
    private int currentPrice;
    BufferedImage sprite;

    public Producer(String name, int BASE_PRICE, double PRICE_RATE, int BASE_PRODUCTION_RATE) {
        this.name = name;
        this.BASE_PRICE = BASE_PRICE;
        this.PRICE_RATE = PRICE_RATE;
        this.BASE_PRODUCTION_RATE = BASE_PRODUCTION_RATE;
        this.numberOfUnits = 0;
        this.currentPrice = BASE_PRICE;
    }

    public int getBASE_PRICE() {
        return BASE_PRICE;
    }

    public int getBASE_PRODUCTION_RATE() {
        return BASE_PRODUCTION_RATE;
    }

    public void buildProducer() {
        if (Game.money >= currentPrice) {
            numberOfUnits++;
            Game.money -= currentPrice;
            currentPrice = (int) (currentPrice*PRICE_RATE);
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getProductionRate() {
        return numberOfUnits*BASE_PRODUCTION_RATE;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }
}
