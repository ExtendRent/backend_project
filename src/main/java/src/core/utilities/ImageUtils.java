package src.core.utilities;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@UtilityClass
public class ImageUtils {

    /**
     * Verilen byte dizisini sıkıştırır.
     *
     * @param data Sıkıştırılacak byte dizisi
     * @return Sıkıştırılmış byte dizisi
     */
    public static byte[] compressImage(byte[] data) {
        // Sıkıştırma için Deflater sınıfını kullan
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        // Sıkıştırılmış veriyi tutacak bir ByteArrayOutputStream oluştur
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            // Sıkıştırılmış veriyi tampona yaz
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (IOException ignored) {
            // Kapatma hatası yok sayılır
        }
        return outputStream.toByteArray();
    }

    /**
     * Verilen byte dizisini açar (sıkıştırmadan kurtarır).
     *
     * @param data Açılacak byte dizisi
     * @return Açılmış byte dizisi
     */
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
            byte[] buffer = new byte[4 * 1024];

            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }

            return outputStream.toByteArray();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace(); // Hata durumunda loglama
            return null;
        } finally {
            inflater.end();
        }
    }


    /**
     * Verilen byte dizisindeki görüntüyü belirtilen boyuta yeniden boyutlandırır.
     *
     * @param data      Yeniden boyutlandırılacak görüntünün byte dizisi
     * @param newWidth  Yeni genişlik
     * @param newHeight Yeni yükseklik
     */
    public static byte[] resizeImage(byte[] data, int newWidth, int newHeight) throws IOException {
        // Orijinal görüntüyü decompress eder
        byte[] decompressedData = decompressImage(compressImage(data));

        // Decompress edilmiş veriyi BufferedImage'a dönüştürür(bunu anlatacağım)
        assert decompressedData != null;
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(decompressedData));

        // Belirtilen boyutta yeni bir BufferedImage oluşturur
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // Orijinal görüntüyü belirtilen boyutta yeni görüntüye çizer
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        // Yeniden boyutlandırılmış görüntüyü sıkıştırır ve sonucu döndürür
        return compressImage(imageToByteArray(resizedImage));
    }

    /**
     * BufferedImage'ı byte dizisine dönüştürür.
     *
     * @param image Dönüştürülecek BufferedImage
     * @return Byte dizisine dönüştürülmüş BufferedImage
     * @throws IOException Dönüştürme hatası durumunda fırlatılır
     */
    private static byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
}
