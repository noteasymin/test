package distove.chat.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import distove.chat.enumerate.MessageType;
import distove.chat.exception.DistoveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static distove.chat.exception.ErrorCode.FILE_UPLOAD_ERROR;

@Slf4j
@RequiredArgsConstructor
@Service
public class StorageService {

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.ceph.url}")
    private String url;

    public String uploadToS3(MultipartFile multipartFile, MessageType type) {
        String fileName = multipartFile.getOriginalFilename();
        ObjectMetadata objectMetadata = getObjectMetadata(multipartFile, type);
        try {
            InputStream inputStream = multipartFile.getInputStream();
            objectMetadata.setContentLength(inputStream.available());
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new DistoveException(FILE_UPLOAD_ERROR);
        }
        return url + fileName;
    }

    public void deleteFile(String originImgUrl) {
        if (originImgUrl == null) return;
        try {
            amazonS3Client.deleteObject(bucket, originImgUrl.split("/")[4]);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }
    }

    private static ObjectMetadata getObjectMetadata(MultipartFile multipartFile, MessageType type) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        if (type == MessageType.IMAGE) objectMetadata.setContentType(multipartFile.getContentType());
        return objectMetadata;
    }

}