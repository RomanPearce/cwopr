package service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImageService {
    public static List<String> getNameList(List<File> files){
        List<String> nameList = new ArrayList<>();
        files.forEach(it -> nameList.add(it.getName()));

        return nameList;
    }
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

    private static List<Image> getImageList(List<File> files){
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

    public static List<Integer> getSquareList(List<File> files){
        List<Image> imageList = getImageList(files);
        List<Integer> squareList = new ArrayList<>();

        imageList.forEach(it -> {
            int w = it.getWidth(null);
            int h = it.getHeight(null);
            squareList.add(w*h);
        });
        return squareList;
    }


    public static List<Double> getWeightHeightRatio(List<File> files){
        List<String> resolutionList = getResolutionList(files);
        List<Double> ratioList = new ArrayList<>();

        resolutionList.forEach(it ->{
            String[] s = it.split("x");
            double w = Double.valueOf(s[0]);
            double h = Double.valueOf(s[1]);
            ratioList.add(h/w);
        });
        return ratioList;
    }
    private static int getAmountOfColors(BufferedImage image){
        Set<Integer> colors = new HashSet<Integer>();
        int w = image.getWidth();
        int h = image.getHeight();
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                colors.add(pixel);
            }
        }
        return colors.size();
    }

    public static List<Integer> getColorList(List<File> files){
        List<Integer> colorList = new ArrayList<>();

        files.forEach(it ->{
            try {
                BufferedImage image = ImageIO.read(it);
                colorList.add(getAmountOfColors(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return colorList;
    }


    public static List<Double> normalizeSize(List<File> files){
        List<Double> normSizeList = new ArrayList<>();
        List<Long> sizeList =  getSizeList(files);

        Long max = Collections.max(sizeList);
        Long min = Collections.min(sizeList);
        for (int i = 0; i < sizeList.size(); i++) {
            normSizeList.add((double)(sizeList.get(i) - min)/(max-min));
        }
        return normSizeList;
    }

    public static List<Double> normalizeExtension(List<File> files){
        List<String> extensionList = getExtensionList(files);
        List<Integer> intExtensionList = new ArrayList<>();
        List<Double> normExtensionList = new ArrayList<>();

        extensionList.forEach(it -> {
            if(it.equals("jpg")){
                intExtensionList.add(1);
            }else{
                intExtensionList.add(0);
            }
        });

        Integer max = Collections.max(intExtensionList);
        Integer min = Collections.min(intExtensionList);
        for (int i = 0; i < intExtensionList.size(); i++) {
            normExtensionList.add((double)(intExtensionList.get(i) - min)/(max-min));
        }

        return normExtensionList;
    }

    public static List<Double> normalizeRatio(List<File> files){
        List<Double> normSizeList = new ArrayList<>();
        List<Double> sizeList =  getWeightHeightRatio(files);

        Double max = Collections.max(sizeList);
        Double min = Collections.min(sizeList);
        for (int i = 0; i < sizeList.size(); i++) {
            normSizeList.add((sizeList.get(i) - min)/(max-min));
        }
        return normSizeList;
    }





}
