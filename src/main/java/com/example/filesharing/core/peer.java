package com.example.filesharing.core;

import com.example.filesharing.model.FileChunk;
import com.example.filesharing.model.peerinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class peer {
    private String peerid;
    private int port;
    private Map<String, List<FileChunk>> localfiles = new ConcurrentHashMap<>();
    private ExecutorService downloadExecutor = Executor.newCachedThreadPool();

    public void start() throws IOException{
        new Networkhandler().startServer(port);
        new Trackerclient().registerPeer("localhost",port , getSharedFields());
    }
    public void downloadFile(String field)
    {
        List<peerinfo> peers = new Trackerclient().getPeerForFile(field);
        List<Future<FileChunk>> futures = new ArrayList<>();

        for(int chunkid = 0;chunkid<getTotalChunks(field);chunkid++) {
            Peerinfo peer = pickRandomPeer(peers);
            futures.add(downloadExecutor.submit(() -> new Networkhandler().downlaodChunk(peer.getIp(), peer.getPort(), field, chunkid)));

        }
        List<FileChunk> chunks = futures.stream()
                .map(f->{
                    try {
                        return f.get();
                    }
                    catch (Exception e){
                        return null;
                    }
                })
                .filter(objects::nonNull)
                .toList();

        Filemanager.mergechunks(chunks,"downloaded_"+field);
    }
}
