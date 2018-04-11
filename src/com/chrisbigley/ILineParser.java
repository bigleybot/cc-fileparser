package com.chrisbigley;

import java.io.IOException;
import java.util.List;

/**
 * Author: Christopher Bigley
 * Circa: 4/11/18
 */

public interface ILineParser {
    List<String> getNextLineTokens() throws IOException;
}
