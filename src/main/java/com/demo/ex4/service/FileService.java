package com.demo.ex4.service;

import org.springframework.stereotype.Service;


@Service
public class FileService {
    public String getUrl(String filename){
        String road = System.getProperty("user.dir") + "\\"+"uploadfiles"+"\\";
        return road+filename;
    }
}
