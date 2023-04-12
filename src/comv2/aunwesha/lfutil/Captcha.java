package comv2.aunwesha.lfutil;

import javax.imageio.ImageIO;

import learnityInit.Host;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class Captcha {

	String hexFilePath = Host.getAdminPath();
	private int width = 200;

    private int height = 100;

    private int codeNum = 4;

    private int lineNum = 50;

    private String code = null;

    private BufferedImage buffImg = null;

    private char[] codeQue = {'2', '3', '4', '5', '6', '7', '8', '9'};


    private Random random = new Random();

    public Captcha() {
        this.createCode();
    }

    public Captcha(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }


    public Captcha(int width, int height, int codeNum, int lineNum) {
        this.width = width;
        this.height = height;
        this.codeNum = codeNum;
        this.lineNum = lineNum;
        this.createCode();
    }

    public void createCode() {

        int codeX = 0;
        int fontHeight = 0;

        fontHeight = height - 60;
        codeX = width / (codeNum + 4);


        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();


        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);


        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);


        for (int i = 0; i < lineNum; i++) {
            int xP = getRandomNumber(width / 2);
            int yP = getRandomNumber(height);
            int xD = xP + getRandomNumber(width);
            int yD = getRandomNumber(height);
            g.setColor(getRandomColor());
            //g.drawLine(xP, yP, xD, yD);
        }

        StringBuffer randomCode = new StringBuffer();

        for (int i = 0; i < codeNum; i++) {
            String strRand = String.valueOf(codeQue[random
                    .nextInt(codeQue.length)]);

            g.setColor(getRandomColor());
            //g.drawString(strRand, (i + 1) * codeX,getRandomNumber(height / 2) + 50);

            g.drawString(strRand, (i + 2) * codeX,70);
           // g.drawString(strRand, (i + 1) * codeX,getRandomNumber(height / 2) + 50);

            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }


    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }


    private int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public String getCode() {
        return code;
    }


    class ImgFontByte {
        public Font getFont(int fontHeight) {
            try {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                        new ByteArrayInputStream(hex2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN, fontHeight);
            } catch (Exception e) {
                return new Font("Arial", Font.PLAIN, fontHeight);
            }
        }

        private byte[] hex2byte(String str) {
            if (str == null) {
                return null;
            }
            str = str.trim();
            int len = str.length();
            if (len == 0 || len % 2 == 1) {
                return null;
            }

            byte[] b = new byte[len / 2];
            try {
                for (int i = 0; i < str.length(); i += 2) {
                    b[i / 2] = (byte) Integer.decode(
                            "0x" + str.substring(i, i + 2)).intValue();
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }


        private String getFontByteStr() {
            String pathName = hexFilePath + File.separator + "HexString.txt";
            File file = new File(pathName);
            String tempStr = null;
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
                tempStr = reader.readLine();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempStr;
        }
    }
}
