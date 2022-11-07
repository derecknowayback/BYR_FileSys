import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;


public class FileUploader {
    public static void main(String[] args) throws Exception {
        MinioClient minioClient = MinioClient.builder().endpoint("http://10.28.152.84:9000").credentials("minioadmin", "minioadmin").build();
        FileInputStream stream = new FileInputStream("D:\\Desktop\\12.jpg");
        minioClient.putObject(PutObjectArgs.builder().bucket("byr-file-sys").object("It's ok!.jpg").stream(stream, stream.available(), -1).contentType("image/jpeg").build());
        System.out.println("OOOOOOOOOOOOOOOOHHHHHHHHHHHHHHHHHH!!!!!!!!!!!!!!!!!!!!!");
    }
}
