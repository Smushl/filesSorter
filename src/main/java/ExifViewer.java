import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * filesSorter
 * Created by Некрасов on 12.04.2016.
 */
public class ExifViewer {
    public static void main(String[] args) throws ImageProcessingException, IOException {
        File file = new File("valve.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);
/*        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
                System.out.println();
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }*/
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
//        metadata.getFirstDirectoryOfType("Exif SubIFD");
        ExifSubIFDDirectory exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        Date date = exifDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        System.out.println(date);

/*
        Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
        ExifSubIFDDescriptor descriptor = new ExifSubIFDDescriptor((ExifSubIFDDirectory) directory);
        String program = descriptor.getExposureProgramDescription();
        System.out.println(program);
*/
    }
}
