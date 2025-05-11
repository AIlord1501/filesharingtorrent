package com.example.filesharing.model;

import java.util.List;

public class peerinfo {
    private String ip;
    private int port;
    private List<String> availableFiles;// list of  file hashes

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getAvailableFiles() {
        return availableFiles;
    }

    public void setAvailableFiles(List<String> availableFiles) {
        this.availableFiles = availableFiles;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
