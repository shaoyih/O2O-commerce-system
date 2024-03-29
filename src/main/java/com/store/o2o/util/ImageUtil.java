package com.store.o2o.util;



import com.store.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;



public class ImageUtil {
    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);

    public static File transferCommonsMultiPartFile(CommonsMultipartFile commonsMultipartFile){
        File newFile= new File(commonsMultipartFile.getOriginalFilename());
        try{
            commonsMultipartFile.transferTo(newFile);
        } catch (IllegalStateException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }
    /**
     *process image and generate new image relative path
     * @param filename
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr){
        String realFileName= getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr= targetAddr+realFileName+extension;

        logger.debug("current relative Addr is: "+relativeAddr);
        logger.debug("current complete Addr is: "+PathUtil.getImgBasePath()+relativeAddr);

        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnail.getImage()).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/watermark.png")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.png")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("create image failed：" + e.toString());
        }
        return relativeAddr;
    }


    /**
     *
     * @param filename
     * @return
     */
    private static String getFileExtension(String filename){
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * Generate file name randomly based on datetime+5 random number
     */
    public static String getRandomFileName(){
        int ranNum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+ranNum;

    }


    public static void main(String[] args) throws IOException {

        Thumbnails.of(new File("C:\\Users\\95155\\Desktop\\image\\vets.png")).size(200,200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.png")),0.25f)
                .outputQuality(0.8f).toFile("C:\\Users\\95155\\Desktop\\image\\vetsNew.png");
    }

    /**
     * check path is dir or file and then delete
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath= new File(PathUtil.getImgBasePath()+storePath);
        if(fileOrPath.exists()){
            if(fileOrPath.isDirectory()){
                File file[]=fileOrPath.listFiles();
                for (int i=0; i<file.length;i++){
                    file[i].delete();
                }
            }
            fileOrPath.delete();
        }

    }


}
