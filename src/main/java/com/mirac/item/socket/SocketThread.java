package com.mirac.item.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketThread extends Thread{
    private Socket socket;
    private boolean allDone = false;

    public static OutputStream outputStream = null;
    public SocketThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;

        try {
            outputStream = socket.getOutputStream();
            outputStream.write(1);
            inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            
            while (!allDone){
                int len = inputStream.read(b);
                String str = "";
                for (int i=0;i<len;i++){
                    str += ((char)b[i]+"");
                }
                String rpstr = str.replace(" ", ",");
                String[] split = rpstr.split(",");
                for (int i=0;i<split.length;i++){
                    MyThread.arrayList.add(split[i]);
                    System.out.println("数据添加:"+split[i]);
                    if("".equals(split[i])){
                        allDone = true;
                        break;
                    }
                }
                if (socket.isClosed()){
                    return;
                }
            }
        }catch (SocketException s) {
            System.out.println("tcp客户端退出");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
                inputStream.close();
                outputStream.close();
                MyThread.serverSocket.close();
                MyThread.serverSocket=null;
                new MyThread().start();
                allDone=false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
