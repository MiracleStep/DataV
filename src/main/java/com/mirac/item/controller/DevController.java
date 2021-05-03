package com.mirac.item.controller;

import com.mirac.item.socket.SocketThread;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/dev")
public class DevController {

    private static Boolean dev1 = false;
    private static Boolean dev2 = false;

    @GetMapping("/control")
    public ResponseEntity<Void> controlBydevId(@RequestParam("devId1")Boolean devId1,@RequestParam("devId2")Boolean devId2) throws IOException {
        if(devId1==null || devId2==null || SocketThread.outputStream == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(devId1&&devId1!=dev1){
            SocketThread.outputStream.write("a".getBytes(StandardCharsets.UTF_8));
            dev1 = devId1;
        }else if(!devId1&&devId1!=dev1) {
            SocketThread.outputStream.write("b".getBytes(StandardCharsets.UTF_8));
            dev1 = devId1;
        }
        
        if(devId2&&devId2!=dev2){
            dev2 = devId2;
            SocketThread.outputStream.write("c".getBytes(StandardCharsets.UTF_8));
        }else if(!devId2&&devId2!=dev2){
            dev2 = devId2;
            SocketThread.outputStream.write("d".getBytes(StandardCharsets.UTF_8));
        }

        return ResponseEntity.ok().build();
    }
}
