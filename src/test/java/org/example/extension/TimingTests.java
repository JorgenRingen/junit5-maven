package org.example.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TimingExtension.class)
class TimingTests {

    @Test
    void slow() throws Exception {
        Thread.sleep(150);
    }

    @Test
    void fast() throws Exception {
        Thread.sleep(10);
    }
}