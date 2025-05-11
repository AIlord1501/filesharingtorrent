package com.example.filesharing.core;
import com.example.filesharing.model.FileChunk;
import java.io.*;
import java.net.*;

public class Networkhandler {
    private ServerSocket serverSocket;
    public void startServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        new Thread(()->{
            while (true){
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        }).start();
    }
    public void handleClient(Socket socket){
        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            String request = (String) ois.readObject();
            if(request.startsWith("GET_CHUNK"))
            {
                String[] parts = request.split(" ");
                FileChunk chunk = getChunkFromLocalStorage(parts[1],Integer.parseInt(parts[2]));
                oos.writeObject(chunk);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public FileChunk downlaodChunk(String peerlp,int peerport ,String field, int chunkid) throws IOException{
        try(Socket socket = new Socket(peerlp,peerport);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()))
        {
            oos.writeObject("GET_CHUNK"+field+ " "+chunkid);
            return (FileChunk) ois.readObject();
        }

    }
}
