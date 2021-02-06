package old;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ExFileReadWrite
 */
public class ExFileReadWrite {

    public static void main(String[] args) {
        try {
            // 파일로부터 읽어들이기 위한 입력스트립 생성
            FileInputStream fileInputStream = new FileInputStream(args[0]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[1]);

            int data = 0;

            while ((data = fileInputStream.read()) != -1) {
                fileOutputStream.write((byte)data);
            }

            fileInputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}