package com.tak.gateway.api.common.utile;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;

@Slf4j
public class FileUtil {
    private static final long    twepoch       = 1288834974657L;
    private static final long    sequenceBits  = 17;
    private static final long    sequenceMax   = 65536;
    private static volatile long lastTimestamp = -1L;
    private static volatile long sequence      = 0L;

    public static Long getByteSize(Path path) {
        return getByteSize(path.toFile());
    }

    public static Long getByteSize(File file) {
        try {
            if (file.exists() && file.isFile()) {
                return file.length();
            } else {
                log.warn(MessageFormat.format("파일 정보가 올바르지 않습니다(Not Exist/Not File).[{0}]", file));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return 0l;
    }

    public static String fileSizeFormat(File file) {
        return fileSizeFormat(getByteSize(file));
    }

    public static String fileSizeFormat(Path path) {
        return fileSizeFormat(getByteSize(path));
    }

    public static String fileSizeFormat(long size) {
        double s = (double) size;
        String unit;
        if (size != -1L) {
            int l;
            if (size < 1024L) {
                l = 0;
            } else if (size < 1024L * 1024L) {
                l = 1;
                s = (double) size / 1024L;
            } else {
                for (l = 2; size >= 1024L * 1024L; l++) {
                    size = size / 1024L;
                    if ((size / 1024L) < 1024L) {
                        s = (double) size / 1024L;
                        break;
                    }
                }
            }

            switch (l) {
                case 0:
                    unit = " Byte";
                    break;
                case 1:
                    unit = " KB";
                    break;
                case 2:
                    unit = " MB";
                    break;
                case 3:
                    unit = " GB";
                    break;
                case 4:
                    unit = " TB";
                    break;
                default:
                    unit = " ERR";
                    break;
            }

            String format = String.format("%.2f", s);
            return format + unit;
        }
        return "";
    }

    public static String getNewFileName(String fileExt) {
        StringBuilder newFileNameSb = new StringBuilder();
        newFileNameSb.append(generateLongId());
        newFileNameSb.append("_").append(RandomStringUtils.randomAlphanumeric(5).toLowerCase());
        newFileNameSb.append(".").append(fileExt);

        return newFileNameSb.toString();
    }

    private static synchronized Long generateLongId() {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << sequenceBits) | sequence;
        return id;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
