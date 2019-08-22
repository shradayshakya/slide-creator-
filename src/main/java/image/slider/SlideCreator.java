package image.slider;

import io.micronaut.http.exceptions.UriSyntaxException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class SlideCreator {
    public void create(){
        Logger logger = Logger.getLogger("File Creator");
        XMLSlideShow slideShow = new XMLSlideShow();
        SlideCreator slideCreator = new SlideCreator();
        try (OutputStream file = new FileOutputStream("slideshow.pptx")){
            Title title1 = new Title("ONE FLOWER", new Rectangle(150, 50, 400, 50));
            List<Image> imagesData1 = new LinkedList<>();
            imagesData1.add(new Image(new File("Pink_flower.jpg"), new Rectangle(100, 150, 500, 300)));
            slideCreator.createTitleImageSlide(slideShow, title1 ,imagesData1);

            List<Image> imagesData2 = new LinkedList<>();
            Title title2 = new Title("TWO FLOWERS", new Rectangle(150, 50, 400, 50));
            imagesData2.add(new Image(new File("Pink_flower.jpg"), new Rectangle(50, 200, 300, 200)));
            imagesData2.add(new Image(new File("lotus.jpg"), new Rectangle(400, 200, 300, 200)));
            slideCreator.createTitleImageSlide(slideShow, title2 , imagesData2);

            List<Image> imagesData3 = new LinkedList<>();
            Title title3 = new Title("THREE FLOWERS", new Rectangle(125, 50, 500, 50));
            imagesData3.add(new Image(new File("Pink_flower.jpg"), new Rectangle(100, 150, 250, 150)));
            imagesData3.add(new Image(new File("lotus.jpg"), new Rectangle(400, 150, 250, 150)));
            imagesData3.add(new Image(new File("daisy.jpg"), new Rectangle(250, 350, 250, 150)));
            slideCreator.createTitleImageSlide(slideShow, title3, imagesData3);

            List<Image> imagesData4 = new LinkedList<>();
            Title title4 = new Title("FOUR FLOWERS", new Rectangle(125, 50, 500, 50));
            imagesData4.add(new Image(new File("Pink_flower.jpg"), new Rectangle(100, 150, 250, 150)));
            imagesData4.add(new Image(new File("lotus.jpg"), new Rectangle(400, 150, 250, 150)));
            imagesData4.add(new Image(new File("daisy.jpg"), new Rectangle(100, 350, 250, 150)));
            imagesData4.add(new Image(new File("blue_rose.jpg"), new Rectangle(400, 350, 250, 150)));
            slideCreator.createTitleImageSlide(slideShow, title4 ,imagesData4);



            slideShow.write(file);
        } catch (IOException io) {
            logger.severe(io.getMessage());
        }
    }


    public XSLFSlide createTitleImageSlide(XMLSlideShow slideShow, Title title, List<Image> imagesData) throws IOException {

        XSLFSlideMaster slideMaster = slideShow.getSlideMasters()[0];
        XSLFSlideLayout layout = slideMaster.getLayout(SlideLayout.TITLE_ONLY);

        XSLFSlide slide = slideShow.createSlide(layout);

        XSLFTextShape titleShape = slide.getPlaceholder(0);
        titleShape.setText(title.getText());
        titleShape.setAnchor(title.getDimension());
        titleShape.setLineColor(Color.BLUE);
        titleShape.setLineWidth(10);
        titleShape.setFillColor(Color.GREEN);
        titleShape.setBottomInset(30);
        titleShape.setTopInset(30);
        titleShape.setLeftInset(30);
        titleShape.setRightInset(30);

        for (Image imageData : imagesData) {
            byte[] pictureData = IOUtils.toByteArray(new FileInputStream(imageData.getPath()));
            int pictureIndex = slideShow.addPicture(pictureData, XSLFPictureData.PICTURE_TYPE_JPEG);

            XSLFPictureShape picture = slide.createPicture(pictureIndex);
            picture.setAnchor(imageData.getDimension());
        }
        return slide;
    }
}

