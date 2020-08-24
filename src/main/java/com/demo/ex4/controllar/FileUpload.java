package com.demo.ex4.controllar;

import com.demo.ex4.dao.fileListDao;
import com.demo.ex4.entity.FilelistEntity;
import com.demo.ex4.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;


@Controller
public class FileUpload {

    FileService fileService = new FileService();


    private com.demo.ex4.dao.fileListDao fileListDao;
    @Autowired
    public void setFileListDao(fileListDao fileListDao){
        this.fileListDao = fileListDao;
    }


    @RequestMapping("/")
    public String index(Model model){
        List<FilelistEntity> fileList = fileListDao.findAll();
        model.addAttribute("fileList",fileList);
        return "upload";
    }

    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    @ResponseBody
    public String file(@RequestParam("file") MultipartFile multipartFile) {
        //前端WebUploader上传队列。每上传一个文件会触发一次
        if (multipartFile.isEmpty()) {
            System.out.println("error");
            return "error";
        }
        String fileName = multipartFile.getOriginalFilename();
        String road = fileService.getUrl(fileName);
        //System.getProperty("user.dir") + "\\"+"uploadfiles"+"\\";
        //road = road + fileName;
        FileOutputStream fos = null;
        FilelistEntity f = new FilelistEntity();
        try{
            fos = new FileOutputStream(road);
            fos.write(multipartFile.getBytes());
            fos.close();
            f.setFname(fileName);
            f.setUrl(road);
            fileListDao.save(f);
        }
        catch (Exception e){
            System.out.println(e);
            return "error";
        }

        return "ok";
    }
}
