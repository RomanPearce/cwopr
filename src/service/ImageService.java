package service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageService {
    public static List<Long> getSizeList(List<File> files){
        List<Long> sizeList = new ArrayList<>();

        files.forEach(it -> sizeList.add(it.length()));

        return sizeList;
    }

    public static List<String> getExtensionList(List<File> files){
        List<String> extensionList = new ArrayList<>();

        for (File it : files) {
            String[] splitedString = it.getName().split("\\.");
            extensionList.add(splitedString[1]);
        }
        return extensionList;
    }

    private static   List<Image> getImageList(List<File> files){
        List<Image> imageList = new ArrayList<>();
        files.forEach(it -> {
            try {
                imageList.add(ImageIO.read(it));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return imageList;
    }

    public static List<String> getResolutionList(List<File> files){
        List<Image> imageList = getImageList(files);
        List<String> resolutionList = new ArrayList<>();

        imageList.forEach(it -> {
            int w = it.getWidth(null);
            int h = it.getHeight(null);
            resolutionList.add(String.valueOf(w) + "x" + String.valueOf(h));
        });
        return resolutionList;
    }

    //TODO:just do it
    public static List<Double> getWeightHeightRatio(List<File> files){
        List<String> resolutionList = getResolutionList(files);
        List<Double> ratioList = new ArrayList<>();









        return ratioList;
    }

}
