package com.example.filesharing;

import com.example.filesharing.core.Filemanager;
import com.example.filesharing.core.peer;
import com.example.filesharing.model.FileChunk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class main {

	public static void main(String...args) throws IOException{
		peer Peer = new peer();
		Peer.start();

		List<FileChunk> chunks = Filemanager.splitFile(new File("myfile.txt"));
		Peer.localFiles.put("file123",chunks);



	}

}
