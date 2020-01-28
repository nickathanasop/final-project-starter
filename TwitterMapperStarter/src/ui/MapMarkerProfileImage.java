package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import twitter4j.Status;
import util.ImageCache;

import java.awt.*;

public class MapMarkerProfileImage extends MapMarkerCircle {
    public static final double defaultMarkerSize = 20.0;
    public static final int defaultProfileImageSize = 20;
    private Status tweet;

    public MapMarkerProfileImage(Layer layer, Coordinate coord, Color color, Status tweet) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        this.tweet = tweet;
        setColor(Color.BLACK);
        setBackColor(color);
    }

    public Status getTweet() {
        return tweet;
    }

    public void paint(Graphics g, Point position, int radius) {
        super.paint(g, position, radius);
        Image profileImage = ImageCache.getInstance().getImage(tweet.getUser().getProfileImageURL());
        g.drawImage(profileImage, position.x - (int) defaultProfileImageSize/2, position.y - (int) defaultProfileImageSize/2, defaultProfileImageSize, defaultProfileImageSize,null,null);

    }
}
