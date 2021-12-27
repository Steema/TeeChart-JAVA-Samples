/*
 * GalleryDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.gallery;
import com.steema.teechart.editors.gallery.GalleryPanel;
import features.BasicSamplePanel;

/**
 *
 * @author tom
 */
public class GalleryDemo extends BasicSamplePanel {

    protected GalleryPanel myGallery;
    //protected ChartGallery myGallery;

    /** Creates a new instance of GalleryDemo */
    public GalleryDemo() {
        super();
        hideButtonPane();
        //setSamplePane(myGallery);
    }

    protected void initGallery() {
        myGallery = new GalleryPanel();
        myGallery.setVisible(true);
        setSamplePane(myGallery);
        //myGallery = new GalleryPanel();
        //myGallery.setBorder(new BevelBorder(BevelBorder.LOWERED));
        //myGallery.setBounds(30, 30, 300, 300);
        //myGallery.createGallerySeries(com.steema.teechart.styles.Shape.class);
        //myGallery.createGalleryPage("Standard");
        //myGallery.setVisible(true);
        //JFrame gframe = new JFrame("Gallery");
        //gframe.getContentPane().add(g);
        //gframe.getContentPane().setSize(g.getWidth(),g.getHeight());
        //gframe.setSize(g.getWidth(),g.getHeight());
        //gframe.setLocation(this.getLocation());
        //gframe.show();
    }

    protected void initComponents() {
        super.initComponents();
        initGallery();
    }

    protected void initGUI() {
        super.initGUI();
    }
}
