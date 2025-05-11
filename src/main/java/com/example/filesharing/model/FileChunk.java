package com.example.filesharing.model;

import java.util.Arrays;

public class FileChunk {
    private String field; //unique file hash
    private int  chunkid; //  number id of the bloack os chunks
    private byte[] data; // array of the binary chunk data
    private String checksum ; //verifying that chunk  is not corrupted

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public int getChunkid() {
        return chunkid;
    }

    public void setChunkid(int chunkid) {
        this.chunkid = chunkid;
    }

    @Override
    public String toString() {
        return "FileChunk{" +
                "field=" + field +
                ", chunkid=" + chunkid +
                ", data=" + Arrays.toString(data) +
                ", checksum='" + checksum + '\'' +
                '}';
    }
}
