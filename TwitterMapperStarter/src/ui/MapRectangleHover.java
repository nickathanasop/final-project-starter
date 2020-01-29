package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

import java.awt.*;

public class MapRectangleHover extends MapObjectImpl implements MapRectangle {
    private Coordinate topLeft;
    private Coordinate bottomRight;

    public MapRectangleHover(Coordinate topLeft, Coordinate bottomRight) {
        this((Layer) null, (String) null, (Style) null, topLeft, bottomRight);
    }

    public MapRectangleHover(String name, Coordinate topLeft, Coordinate bottomRight) {
        this((Layer) null, name, (Style) null, topLeft, bottomRight);
    }

    public MapRectangleHover(Layer layer, Coordinate topLeft, Coordinate bottomRight) {
        this(layer, (String) null, (Style) null, topLeft, bottomRight);
    }

    public MapRectangleHover(Layer layer, String name, Coordinate topLeft, Coordinate bottomRight) {
        this(layer, name, (Style) null, topLeft, bottomRight);
    }

    public MapRectangleHover(Layer layer, String name, Style style, Coordinate topLeft, Coordinate bottomRight) {
        super(layer, name, style);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }


    @Override
    public Coordinate getTopLeft() {
        return topLeft;
    }

    @Override
    public Coordinate getBottomRight() {
        return bottomRight;
    }

    @Override
    public void paint(Graphics g, Point topLeft, Point bottomright) {
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillRect(topLeft.x, topLeft.y, topLeft.x - bottomright.x, topLeft.y - bottomright.y);
            g2.setComposite(oldComposite);
        }

        g.setColor(this.getColor());
        g.drawRect(topLeft.x, topLeft.y, topLeft.x - bottomright.x, topLeft.y - bottomright.y);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, topLeft);
        }
    }

}
