package com.jw.minio;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * minio分布式文件系统配置
 */
@Configuration
public class MinioTemplate {
    /**
     * url
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * 端口号
     */
    @Value("${minio.port}")
    private int port;

    /**
     * 是否是https请求，默认true
     */
    @Value("${minio.secure}")
    private boolean secure;

    /**
     * minio账号
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMinioClient(){
        return MinioClient.builder().endpoint(endpoint, port, secure).credentials(accessKey, secretKey).build();
    }

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     */
    public String upload(MultipartFile multipartFile) throws InvalidBucketNameException, InsufficientDataException, IOException, NoSuchAlgorithmException, InvalidKeyException, XmlParserException, InternalException, RegionConflictException, ErrorResponseException, InvalidResponseException, ServerException {
        PutObjectOptions putObjectOptions = new PutObjectOptions(multipartFile.getSize(), PutObjectOptions.MIN_MULTIPART_SIZE);
        putObjectOptions.setContentType(multipartFile.getContentType());
        DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter MM = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dd = DateTimeFormatter.ofPattern("dd");
        LocalDate localDate = LocalDate.now();//获取当前时间
        String bucketName = localDate.format(yyyy);//以年为存储桶
        String path = localDate.format(MM) + "/" + localDate.format(dd) + "/";//在年存储桶里面创建月日文件夹
        boolean bucketExists = getMinioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!bucketExists) {//判断存储桶yyyy是否存在，不存在则创建
            getMinioClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            //设置读写权限
            String read_write = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}";
            getMinioClient().setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(read_write).build());
        }
        //更换文件名称
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String fileName = path + UUID.randomUUID().toString().replaceAll("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
        getMinioClient().putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                        multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType())
                .build());
        return bucketName + "/" + fileName;
    }

    /**
     * 文件删除
     *
     * @param bucketName 存储桶名称
     * @param fileName   文件名称
     */
    public void delete(String bucketName, String fileName) throws InvalidPortException, InvalidEndpointException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ServerException {
        getMinioClient().removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
    }
}
