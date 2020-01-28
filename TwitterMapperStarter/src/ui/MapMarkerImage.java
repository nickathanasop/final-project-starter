package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;

public class MapMarkerImage extends MapMarkerCircle {
    public static final double defaultMarkerSize = 100.0;
    private Color color;

    public MapMarkerImage(Layer layer, Coordinate coord, Color color, Image image) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        setColor(Color.BLACK);
        setBackColor(color);
        Point p = new Point(10,10);
        paint(image.getGraphics(), p , 30);
    }
}
