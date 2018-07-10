package com.yly.testboot.fastdfs;

import lombok.Data;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Data
public class FastDFSClient {
    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private static TrackerServer trackerServer;
    private static TrackerClient trackerClient;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient1;
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();;
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
//            trackerServer = trackerClient.getConnection();
//            storageServer = trackerClient.getStoreStorage(trackerServer);
//            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            /**
             * 如果将storageClient1的初始化放在static块中初始化，就是一个单例的客户端，tracker中配置的负载均衡方式将不会生效
             */
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            uploadResults = storageClient1.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults == null) {
            logger.error("upload file fail, error code:" + storageClient1.getErrorCode());
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            return storageClient1.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            byte[] fileByte = storageClient1.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        trackerServer = trackerClient.getConnection();
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient1 = new StorageClient1(trackerServer, storageServer);
        int i = storageClient1.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }

//    public static String getStorageInfo(){
//        storageServer
//    }
}
