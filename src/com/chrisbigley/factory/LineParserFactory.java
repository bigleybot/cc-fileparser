package com.chrisbigley.factory;

import com.chrisbigley.ILineParser;
import com.chrisbigley.TabDelimitedLineParser;

import java.io.File;

/**
 * Author: Christopher Bigley
 * Circa: 4/11/18
 */

public class LineParserFactory {
    public ILineParser getLineParser(File file) throws Exception {
        //Determine file type by analyzing file extension
        if (file.getName().endsWith(".tab")) {
            return new TabDelimitedLineParser(file.getAbsolutePath());
        } else {
            throw new Exception("Unknown file type. Factory cannot create a line parser");
        }
    }
}
