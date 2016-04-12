import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

/**
 * filesSorter
 * Created by Некрасов on 12.04.2016.
 */
public class ExifViewer {
    public static void main(String[] args) throws ImageProcessingException, IOException {
        File file = new File("1.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        //перебор всех тэгов
/*
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
*/
        //ИЛИ
        System.out.println("++++++++ExifSubIFDDirectory++++++++");
        ExifSubIFDDirectory exifDirectory1 = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        System.out.println(exifDirectory1);
        for (Tag tag : exifDirectory1.getTags()) {
            System.out.println(tag);
        }
        //ИЛИ
        System.out.println("++++++++ExifIFD0Directory++++++++");
        ExifIFD0Directory exifDirectory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        System.out.println(exifDirectory);
        for (Tag tag : exifDirectory.getTags()) {
            System.out.println(tag);
        }
        Date date = exifDirectory.getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL, TimeZone.getDefault());
        System.out.println(date == null);
//        System.out.println(date.getTime());
        assert date != null; //и чо с этим делать? Надо почитать, как это применяется
        java.sql.Date d = new java.sql.Date(date.getTime());
        System.out.println(d);

    }
}
