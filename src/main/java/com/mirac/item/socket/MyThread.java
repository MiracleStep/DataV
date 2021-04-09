package com.mirac.item.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

@Component
public class MyThread extends Thread{


    public static ServerSocket serverSocket =null;

    public MyThread() throws IOException {
    }

    public static ArrayList<String> arrayList = new ArrayList<>();
    @Override
    public void run() {
        try {
            if(serverSocket == null){
                serverSocket = new ServerSocket(6090);
                System.out.println("TCPSERVER_STATR");
                Socket accept = serverSocket.accept();
                new SocketThread(accept).start();
                System.out.println("connection--ok");
            }else {
                System.out.println("已经存在");
            }
        } catch (SocketException s) {
            s.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
