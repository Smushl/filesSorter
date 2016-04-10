import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by Roman on 05.03.2016.
 */
public class FileIterator implements Iterator<String>{
    private Queue<File> files = new LinkedList<File>();
    public FileIterator(String path){
        files.add(new File(path));
    }
    public boolean hasNext(){
        return !files.isEmpty();
    }

    public String next(){
        File file = files.peek();
        if (file.isDirectory()){
            Collections.addAll(files, file.listFiles());
            files.remove();
        }
        return files.poll().getAbsolutePath();
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super String> action) {

    }
}
