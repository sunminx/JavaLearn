package com.xy.dp.decorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LowerCaseFileInputStream extends FileInputStream {

    public LowerCaseFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public int read(byte b[]) throws IOException {
        int n = super.read(b);
        for (int i = 0; i < n; i++) {
            b[i] = (byte) Character.toLowerCase(b[i]);
        }
        return n;
    }
}
