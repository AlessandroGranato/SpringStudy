package com.mycompany.test;

import static org.junit.Assert.*;

import com.mycompany.config.CDPlayerConfig;
import com.mycompany.soundsystem.CompactDisc;
import com.mycompany.soundsystem.MediaPlayer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CDPlayerConfig.class)
public class CDPlayerTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc cd;

    @Autowired
    private MediaPlayer player;

    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

    @Test
    public void play() {
        player.play();

        //In order to pass the test, use \r\n instead of \n
        assertEquals(
                "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\r\n",
                systemOutRule.getLog());
    }
}