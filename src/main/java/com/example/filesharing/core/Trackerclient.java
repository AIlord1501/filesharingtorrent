package com.example.filesharing.core;
import com.example.filesharing.model.peerinfo;
import java.net.*;
import java.util.*;


public class Trackerclient {
    private static final String TRACKER_URL = "http://tracker-ip:port";

    public void registerPeer(String ip,int port,List<String> files)
    {

    }
    public List<peerinfo> getPeerForFile(String filed)
    {
        return new ArrayList<>();
    }
}
