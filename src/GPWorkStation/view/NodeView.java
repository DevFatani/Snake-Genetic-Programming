package GPWorkStation.view;

import java.awt.*;

public class NodeView {
    int x,y;
    String name;

    public NodeView(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public NodeView(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public NodeView() {
    }

    public void draw(Graphics2D g) {
        g.drawString(name, x, y);
    }
}
