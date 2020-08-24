package com.demo.ex4.controllar;

import com.demo.ex4.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


@Controller
public class FileDelete {
    /**
     * 实例化对象
     **/
    FileService fileService = new FileService();

    private com.demo.ex4.dao.fileListDao fileListDao;

    @Autowired
    public void setFileListDao(com.demo.ex4.dao.fileListDao fileListDao) {
        this.fileListDao = fileListDao;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("filename") String filename) {
        String url = fileService.getUrl(filename);
        File file = null;
        /** 尝试删除 file 成功返回 ok **/
        try {
            file = new File(url);
            file.delete();
            fileListDao.deleteById(filename);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }

}
