package com.store.o2o.util;



import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr){
        String realFileName= getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr= targetAddr+realFileName+extension;

        logger.debug("current relative Addr is: "+relativeAddr);
        logger.debug("current complete Addr is: "+PathUTil.getImgBasePath()+relativeAddr);

        File dest=new File(PathUTil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"/watermark.png")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     *
     * @param commonsMultipartFile
     * @return
     */
    private static String getFileExtension(File commonsMultipartFile){
        String originalFilename=commonsMultipartFile.getName();
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    /**
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath=PathUTil.getImgBasePath()+targetAddr;
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


}
