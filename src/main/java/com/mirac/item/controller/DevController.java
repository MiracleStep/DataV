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

    @GetMapping("/control")
    public ResponseEntity<Void> controlBydevId(@RequestParam("devId1")Boolean devId1,@RequestParam("devId2")Boolean devId2) throws IOException {
        if(devId1==null || devId2==null || SocketThread.outputStream == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if(devId1){
            SocketThread.outputStream.write("2ON ".getBytes(StandardCharsets.UTF_8));
        }else {
            SocketThread.outputStream.write("2OFF ".getBytes(StandardCharsets.UTF_8));
        }


        if(devId2){
            SocketThread.outputStream.write("2ON ".getBytes(StandardCharsets.UTF_8));
        }else {
            SocketThread.outputStream.write("2OFF ".getBytes(StandardCharsets.UTF_8));
        }

        return ResponseEntity.ok().build();
    }
}
