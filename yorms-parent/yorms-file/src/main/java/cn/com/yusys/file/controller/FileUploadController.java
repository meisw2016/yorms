package cn.com.yusys.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 单机版的WEB应用文件服务器
 * @author Administrator
 *
 */
@Controller
@Api(description = "文件上传操作")
public class FileUploadController {
	 /*
     * 获取file.html页面
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }
    
    /**
     * 实现文件上传
     * */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.PATCH)
    @ResponseBody 
    @ApiOperation(value = "/fileUpload",notes = "文件上传")
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        
        String path = "F:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    /*
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile(){
        return "/multifile";
    }
    
    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="multifileUpload",method=RequestMethod.PATCH) 
    @ApiOperation(value = "/multifileUpload",notes = "多文件上传")
    /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    public @ResponseBody String multifileUpload(HttpServletRequest request){
        
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        
        if(files.isEmpty()){
            return "false";
        }

        String path = "F:/test" ;
        
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            
            if(file.isEmpty()){
                return "false";
            }else{        
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                } 
            }
        }
        return "true";
    }
}
