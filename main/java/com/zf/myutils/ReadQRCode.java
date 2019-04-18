package com.zf.myutils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

//读取二维码信息
public class ReadQRCode {

    public static void main(String[] args) throws Exception {
        MultiFormatReader formatReader = new MultiFormatReader();
        File file = new File("d:/img.png");

        BufferedImage image = ImageIO.read(file);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

        //定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        Result result = formatReader.decode(binaryBitmap, hints);

        System.out.println("二维码解析结果：" + result.toString());
        System.out.println("二维码的格式：" + result.getBarcodeFormat());
        System.out.println("二维码的文本内容：" + result.getText());
        System.out.println(sendGetRequest("https://www.baidu.com"));
    }

    public static String sendGetRequest(String getUrl)
    {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
