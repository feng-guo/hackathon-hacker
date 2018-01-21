package game;

import display.Display;
import listeners.MouseManager;

public class Handler {
    Game game;

    public Handler (Game game) {
        this.game = game;
    }

    public Display getDisplay() {
        return game.getDisplay();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
}
