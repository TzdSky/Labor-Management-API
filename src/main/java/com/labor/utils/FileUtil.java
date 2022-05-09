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
    public static boolean deleteDataFile(String filePath,String fileName){
        boolean flag = false;
        StringBuilder fileUrl = new StringBuilder();
        fileUrl.append(filePath).append(File.separator).append(fileName);
        File file = new File(fileUrl.toString());
        // 路径存在则进行删除
        if (file.exists()) {
            file.delete();
            flag = true;
            logger.info("--文件清除--");
        }
        return flag;

    }
    //param folderPath 文件夹完整绝对路径
    public static Boolean delFolder(String folderPath) {
        File file = new File(folderPath);
        try {
            // 路径存在则进行删除
            if (file.exists()) {
                file.delete();
                logger.info("--文件清除成功--");
                return true;
            }
        } catch (Exception e) {
            logger.info("--文件清除失败--");
            e.printStackTrace();
        }
        return false;
    }

    //删除指定文件夹下所有文件
    //param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
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
