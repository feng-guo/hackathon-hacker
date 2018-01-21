package game;

import display.Display;

public class Handler {
    Game game;

    public Handler (Game game) {
        this.game = game;
    }

    public Display getDisplay() {
        return game.getDisplay();
    }
}
