package com.stackroute.muzixapp.Exception;

public class TrackNotFound extends Exception {
    private String message;
    public TrackNotFound()
    {

    }
    public TrackNotFound(String message)
    {
        super(message);
        this.message=message;
    }
}