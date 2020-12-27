package com.mycompany.test;

import com.mycompany.config.TrackCounterConfig;
import com.mycompany.soundsystem.CompactDisc;
import com.mycompany.soundsystem.TrackCounter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= TrackCounterConfig.class)
public class TrackCounterTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc cd;

    @Autowired
    private TrackCounter counter;

    @Test
    public void testTrackCounter() {
        cd.playTrack(1);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(4);
        cd.playTrack(4);
        assertEquals(1, counter.getPlayCount(1));
        assertEquals(0, counter.getPlayCount(2));
        assertEquals(4, counter.getPlayCount(3));
        assertEquals(2, counter.getPlayCount(4));
    }
}