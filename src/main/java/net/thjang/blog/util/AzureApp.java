// MIT License
// Copyright (c) Microsoft Corporation. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE

package net.thjang.blog.util;


import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import lombok.extern.java.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

/* *************************************************************************************************************************
 * Summary: This application demonstrates how to use the Blob Storage service.
 * It does so by creating a container, creating a file, then uploading that file, listing all files in a container,
 * and downloading the file. Then it deletes all the resources it created
 *
 * Documentation References:
 * Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
 * What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
 * Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
 * Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx
 * Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
 * *************************************************************************************************************************
 */
@Log
public class AzureApp
{
    /* *************************************************************************************************************************
     * Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
     * *************************************************************************************************************************
     */
    public static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=patstorage20181122;" +
                    "AccountKey=kMPpj1T4oKy6Ez2dxuJ3WH6TJnbvf1sESxZJStwsxSbZEVfyLWhc5I8Pt/OK2NV0+ZddAJa7+G0FZFbnes20kw==;EndpointSuffix=core.windows.net";


    public void execAzure(String url,String sourceFilePath)
    {
        File sourceFile = null, downloadedFile = null;
        String path = null;
        File deleteDir=null;
        //System.out.println("Azure Blob Storage quick start sample");

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;

        try {
            // Parse the connection string and create a blob client to interact with Blob Storage
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference("quickstartcontainer");
            // Create the container if it does not exist with public access.
            //System.out.println("Creating container: " + container.getName());
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            //Creating a sample file
            CloudBlockBlob blob = container.getBlockBlobReference(url);
            blob.uploadFromFile(sourceFilePath);
        }
        catch (StorageException ex)
        {
            System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {


            if(downloadedFile != null)
                downloadedFile.deleteOnExit();

            if(path != null){
            }



        }
    }


    public static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator+cal.get(Calendar.YEAR);
        String monthPath = yearPath + "_" +
                new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
        String datePath = monthPath + "_" +
                new DecimalFormat("00").format(cal.get(Calendar.DATE));

        //makeDir(uploadPath,yearPath,monthPath,datePath);
        File dirPath = new File(uploadPath + datePath);
        if(!dirPath.exists()){
            dirPath.mkdir();
        }
        //log.info(datePath);
        //폴더만 만들고 리턴값은 /년도/월/일 리턴
        return datePath;
    }



}
