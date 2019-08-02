package com.stackroute;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.service.TrackService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListen implements ApplicationListener<ContextRefreshedEvent> {
    TrackService trackService;
    public ApplicationListen(TrackService trackService){
        this.trackService=trackService;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try
        {
            Track track=new Track(1,"bekhaliya","from singh");
            trackService.saveTrack(track);
            for(Track track1:trackService.getAllTracks())
            {
                System.out.println(trackService.getAllTracks());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
