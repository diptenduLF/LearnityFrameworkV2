package learnityInit.util;

import java.io.*;

public class Cleaner
{
    public boolean deleteDirectory(final File path) {
        if (path.exists() && path.isDirectory()) {
            final File[] files = path.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    this.deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }
}
