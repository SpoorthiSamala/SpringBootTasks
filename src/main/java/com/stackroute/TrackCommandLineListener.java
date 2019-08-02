package com.stackroute;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TrackCommandLineListener implements CommandLineRunner {
    @Autowired
    TrackService trackService;
    @Override
    public void run(String... args) throws Exception {
        Logger logger= LoggerFactory.getLogger((TrackCommandLineListener.class));
        try
        {
            Track track=new Track(2,"Perfect","By Ed Sheeran");
            trackService.saveTrack(track);
            for(Track track1:trackService.getAllTracks())
            {
                logger.info(track1.toString());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
