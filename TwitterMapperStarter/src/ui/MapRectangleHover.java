package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapObjectImpl;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;
import util.ImageCache;

import java.awt.*;

public class MapRectangleHover extends MapObjectImpl implements MapRectangle {
    private Coordinate topLeft;
    private Coordinate bottomRight;
    private MapMarkerProfileImage currentMapMarker;

    public MapRectangleHover(MapMarkerProfileImage currentMapMarker) {
        super(currentMapMarker.getLayer(), (String) null, currentMapMarker.getStyle());
        topLeft = currentMapMarker.getCoordinate();
        bottomRight = currentMapMarker.getCoordinate();
        setColor(currentMapMarker.getColor());
        setBackColor(currentMapMarker.getBackColor());
        this.currentMapMarker = currentMapMarker;
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
    public void paint(Graphics g, Point topLeft, Point bottomRight) {
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            //Composite oldComposite = g2.getComposite();
            //g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillRect(topLeft.x - 20, topLeft.y + 30, 1000, 100);
           // g2.setComposite(oldComposite);
        }

        g.setColor(this.getColor());
        g.drawRect(topLeft.x - 20, topLeft.y + 30, 1000, 100);
        Image profileImage = ImageCache.getInstance().getImage(currentMapMarker.getTweet().getUser().getProfileImageURL());
        g.drawImage(profileImage, topLeft.x - 15, topLeft.y + 35, 90, 90,null,null);
        g.drawString(currentMapMarker.getTweet().getText(), topLeft.x + 80, topLeft.y + 120);
        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, topLeft);
        }
    }

}
