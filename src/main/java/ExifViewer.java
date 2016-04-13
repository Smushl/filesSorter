import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
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
    public static Date getDateFromMetadata (Metadata metadata){
        try {
            Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) throws ImageProcessingException, IOException {
        File file = new File(args[0]);
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        //перебор всех тэгов
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }

//        Date date = getDateFromMetadata(metadata);
//        java.sql.Date d = new java.sql.Date(date.getTime());
//        System.out.println(d);

    }
}
