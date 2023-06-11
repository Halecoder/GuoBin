package com.hl.travel.util;


import com.backblaze.b2.client.exceptions.B2Exception;

import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.util.api.B2;
import com.sun.tools.javadoc.resources.javadoc;

import java.io.*;
import java.util.*;

/**
 * BlackBlaze B2云存储工具类
 */
public class B2Utils {


    public static void uploadFile(String Command, String bucketName, String filePath, String fileName) throws B2Exception, IOException, B2JsonException {


//        组织文件命令
        String[] args = new String[6];

        args = new String[]{Command, bucketName, filePath, fileName};


        try (B2 b2 = new B2()) {
            if (args.length == 0) {
                b2.usageAndExit("you must specify which command you want to run.");
            }
            final String command = args[0];
            final String[] remainingArgs = Arrays.copyOfRange(args, 1, args.length);
            if ("b2.upload_file".equals(command)) {
                b2.upload_file(remainingArgs, false);
            } else if ("b2.upload_large_file".equals(command)) {
                b2.upload_file(remainingArgs, true);
            } else {
                b2.usageAndExit("unsupported command '" + command + "'");
            }
        }

    }


    public static void deleteFile(String Command, String bucketName, String fileName) throws B2Exception, IOException, B2JsonException {


        //        根据文件名获取文件id

        String fileId = getFileVersion(bucketName, fileName).getFileId();

        //        组织文件命令
        String[] args = new String[6];

        args = new String[]{Command, fileName, fileId};
        try (B2 b2 = new B2()) {
            if (args.length == 0) {
                b2.usageAndExit("you must specify which command you want to run.");
            }
            final String command = args[0];
            final String[] remainingArgs = Arrays.copyOfRange(args, 1, args.length);
            if ("b2.delete_file_version".equals(command)) {
                b2.delete_file_version(remainingArgs);

            } else {
                b2.usageAndExit("unsupported command '" + command + "'");
            }
        }
    }


    public static B2FileVersion getFileVersion(String bucketName, String fileName) throws B2Exception, IOException, B2JsonException {
        //        组织文件命令
        String[] args = new String[6];

        args = new String[]{bucketName, fileName};
        try (B2 b2 = new B2()) {

            if (fileName == null || fileName.length() == 0) {
                b2.usageAndExit("you must specify which command you want to run.");
            }

            return b2.getFileVersion(args);


        }

    }


}
