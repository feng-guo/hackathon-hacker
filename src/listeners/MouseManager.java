package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftClicked = false;
    public boolean checkMouseClick;
    int mouseX, mouseY;

    public MouseManager() {
        checkMouseClick = false;
    }

    public boolean getCheckMouseClick() {
        return checkMouseClick;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (!leftClicked) {
                checkMouseClick = true;
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
            }
            leftClicked = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // TODO Auto-generated method stub
//        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
//            if (!leftClicked) {
//                checkMouseClick = true;
//            }
//            leftClicked = true;
//        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            leftClicked = false;
            checkMouseClick = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
