package GPWorkStation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

import GPWorkStation.Node;
import GPWorkStation.utilities.JEasyFrame;

public class TreeView extends JComponent {
    Node root;
    int nw = 30;
    int nh = 20;
    int inset = 20;
    int minWidth = 300;
    int heightPerLevel = 80;
    Color fg = Color.black;
    Color bg = Color.cyan;
    Color nodeBg = Color.white;
    Color highlighted = Color.red;
    Set<Node> high;

    public TreeView(Node root) {
        this.root = root;
        high = new HashSet<>();
    }

    public TreeView(Node root, Set<Node> high) {
        this.root = root;
        this.high = high;
    }

    public TreeView(Node root, Node h) {
        this.root = root;
        this.high = new HashSet<>();
        high.add(h);
    }

    // now to render it

    public void paintComponent(Graphics gg) {
        // Font font =
        // g.setFont();
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = inset;
        int x = getWidth() / 2;
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
        draw(g, root, x, y, (int) (1.1 * getWidth()) - inset * 0);
    }

    private void draw(Graphics2D g, Node cur, int x, int y, int wFac) {
        // draw this one, then it's children

        for (int i=0; i<cur.arity(); i++) {
            int xx = (int) ((i+1.0) * wFac / (cur.arity() + 1) + (x - wFac/2));
            int yy = y + heightPerLevel;
            g.setColor(fg);
            g.drawLine(x, y, xx, yy);
            draw(g, cur.na[i], xx, yy, wFac / cur.arity());
        }
        drawNode(g, cur, x, y);
    }

    private void drawNode(Graphics2D g, Node node, int x, int y) {
        String s = node.name();
        g.setColor(nodeBg);
        if (high.contains(node)) g.setColor(highlighted);
        g.fillOval(x-nw/2, y-nh/2, nw, nh);
        g.setColor(fg);
        g.drawOval(x-nw/2, y-nh/2, nw, nh);
        g.setColor(fg);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(s, g);
        g.drawString(s, x - (int) (rect.getWidth()/2), (int) (y+rect.getHeight()/2));
    }

    public Dimension getPreferredSize() {
        return new Dimension(minWidth, heightPerLevel * (root.depth()-1) + inset * 2);  
    }

    public TreeView showTree(String title) {
        new JEasyFrame(this, title, true);
        return this;
    }
}
