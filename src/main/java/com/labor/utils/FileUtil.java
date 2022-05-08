package com.labor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author Bocong
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param fileBytes 文件流
     */
    public static void upload(String filePath, String fileName, byte[] fileBytes){
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream output = null;
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(fileBytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
                logger.info("创建文件夹，{}", file);
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            // 实例化OutputString 对象
            output = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                output.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            output.flush();
        } catch (Exception e) {
            logger.error("输出文件流时抛异常，filePath={}", filePath, e);
        } finally {
            try {
                bis.close();
                fos.close();
                output.close();
            } catch (IOException e0) {
                logger.error("文件处理失败，filePath={}", filePath, e0);
            }
        }
    }


    // 文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    // 文件删除工具类服务方法
    /**
     * 删除数据文件
     *
     * @param filePath
     * @throws IOException
     */
    public static boolean deleteDataFile(String filePath){
        boolean flag = false;
        File file = new File(filePath);
        // 路径存在则进行删除
        if (file.exists()) {
            file.delete();
            flag = true;
            logger.info("--文件清除--");
        }
        return flag;

    }


    public static void main(String[] args) {
        String filePath = "D:\\home\\test";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();  //多层目录需要调用mkdirs
            System.out.println("执行了");
        }
    }

}
