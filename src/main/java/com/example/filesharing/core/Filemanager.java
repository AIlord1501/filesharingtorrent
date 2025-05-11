package com.example.filesharing.core;
import com.example.filesharing.model.FileChunk;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.*;
public class Filemanager {
    private static final int CHUNK_SIZE = 256*1024;

    public static List<String> splitFile(File file) throws IOException{
        List<String> chunks = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(file)){
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesread;
            int chunksid = 0;
            while((bytesread = fis.read(buffer))>0){
                byte[] chunkdata = Arrays.copyOf(buffer,bytesread);
                String checksum = org.springframework.util.DigestUtils.sha256Hex(chunkdata);
                chunks.add(new FileChunk(
                        org.springframework.util.DigestUtils.sha256Hex(file.getName()),
                        chunksid++ ;
                        chunkdata ;
                        checksum;


                )
                );
            }


        }
        return chunks;
    }
    public static void mergechunks(List<FileChunk> chunks,String outputPath) throws IOException{
        try(FileOutputStream fos =  new FileOutputStream(outputPath)){
            for (FileChunk chunk : chunks.stream()
                    .sorted(Camparator.comparing(FileChunk::getCheckid))
                    .toList()
            )
            {
                fos.write(chunk.getData());
            }
        }
    }
}
