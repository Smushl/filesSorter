

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * filesSorter
 * Created by roman on 05.03.16.
 */
class FolderChoose extends JFrame {
    private JFileChooser fileOpenDialog;
    private String directory;
    private List<File> files;
    private final String DEFAULT_PATH = "/media/roman/EOS_DIGITAL/DCIM/100CANON";

    FolderChoose() {
        super("Choose Folder To Files Sort");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        files = new LinkedList<>();

        fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileOpenDialog.setAcceptAllFileFilterUsed(false);

        JButton btnChoose = new JButton("Open");
        btnChoose.addActionListener(e -> {
            fileOpenDialog.setCurrentDirectory(new File(DEFAULT_PATH));
            if (fileOpenDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                directory = fileOpenDialog.getSelectedFile().getPath();
                System.out.println(fileOpenDialog.getSelectedFile().getPath());
                Collections.addAll(files, fileOpenDialog.getSelectedFile().listFiles(pathname -> (pathname.getName().contains(".CR2")) || (pathname.getName().contains(".MOV")) || (pathname.getName().contains(".jpg")) || (pathname.getName().contains(".mp4"))));
                //------------------------------------------------
                for (File file : files) {
                    try {
                        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                        String dateFolderName = attr.creationTime().toString().substring(0, 10);
                        File dateFolder = new File(directory + "/" + dateFolderName);
                        System.out.println("Creating " + dateFolder.getName() + " " + dateFolder.mkdir());
                        System.out.println("Mooving file " + file.getName() + " " + file.renameTo(new File(directory + "/" + dateFolderName + "/" + file.getName())));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                //-----------------------------------
                System.out.println("Files moved:  " + files.size());
            }
            System.exit(0);
        });

        JPanel pane1 = new JPanel(new BorderLayout());
        pane1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane1.add(btnChoose, BorderLayout.CENTER);
        setContentPane(pane1);

        setLocation(400, 250);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new FolderChoose();
    }
}

