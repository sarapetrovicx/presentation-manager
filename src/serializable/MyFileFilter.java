package serializable;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MyFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".gpf"));
    }

    @Override
    public String getDescription() {
        return "GrafEditor Project Files (*.gpf)";
    }
}
