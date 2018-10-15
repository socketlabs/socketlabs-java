import models.Attachment;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Attachment attachment = new Attachment("/Users/ross.brazuk/Desktop/photos/pics/images.jpg");

        System.out.println(attachment.getMimeType());


    }
}
