package com.clipsub.csmanga.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/**
 * Various functions for disk caching and helper.
 */
public class DiskUtils {

    private static final Pattern DIR_SEPARATOR = Pattern.compile("/");

    private DiskUtils() {
        throw new AssertionError();
    }

    // http://stackoverflow.com/questions/13976982/removable-storage-external-sdcard-path-by-manufacturers
    // http://stackoverflow.com/questions/11281010/how-can-i-get-external-sd-card-path-for-android-4-0

    public static String[] getStorageDirectories(Context context) {
        // Final set of paths.
        final Set<String> storageDirectories = new HashSet<String>();
        storageDirectories.add(context.getFilesDir().getAbsolutePath());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] directories = context.getExternalFilesDirs(null);
            if (directories != null) {
                for (File storage : directories) {
                    if (storage != null) {
                        storageDirectories.add(storage.getAbsolutePath());
                    }
                }
            }
        } else {
            /**
             * Physical SD card (not emulated).
             */
            final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
            /**
             * All secondary SD card (all exclude primary), separated by ":".
             */
            final String rawSecondaryStorageStr = System.getenv("SECONDARY_STORAGE");
            // Primary emulated SD card.
            final String rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET");
            // Check the device for emulated storage.
            if (TextUtils.isEmpty(rawEmulatedStorageTarget)) {
                // Device has physical external storage, use plain path.
                if (TextUtils.isEmpty(rawExternalStorage)) {
                    // EXTERNAL_STORAGE undefined, falling back to the default.
                    storageDirectories.add("/storage/sdcard0"
                            + File.separator
                            + context.getPackageName());
                } else {
                    // EXTERNAL_STORAGE defined, get it at once.
                    storageDirectories.add(rawExternalStorage
                            + File.separator
                            + context.getPackageName());
                }
            }
            // If device has emulated storage.
            else {
                final String rawUserId;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    rawUserId = "";
                } else {
                    // Get external storage directory path.
                    final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    // Split path into folders.
                    final String[] folders = DIR_SEPARATOR.split(path);
                    // Get the last folder.
                    final String lastFolder = folders[folders.length - 1];
                    boolean isDigit = false;

                    try {
                        Integer.valueOf(lastFolder);
                        isDigit = true;
                    } catch (NumberFormatException e) {
                        // Do nothing.
                    }

                    // If the last folder represents only numbers, then rawUserId is that folder's name. Else it is empty.
                    rawUserId = isDigit ? lastFolder : "";
                }

                if (TextUtils.isEmpty(rawUserId)) {
                    storageDirectories.add(rawEmulatedStorageTarget
                            + File.separator
                            + context.getPackageName());
                } else {
                    storageDirectories.add(rawEmulatedStorageTarget
                            + File.separator
                            + rawUserId
                            + File.separator
                            + context.getPackageName());
                }
            }

            // If secondary storage is not empty.
            if (!TextUtils.isEmpty(rawSecondaryStorageStr)) {
                String[] rawSecondaryStorages = rawSecondaryStorageStr.split(File.separator);
                for (int index = 0; index < rawSecondaryStorages.length; index++) {
                    storageDirectories.add(rawSecondaryStorages[index]
                            + File.separator
                            + context.getPackageName());
                }
            }
        }

        return storageDirectories.toArray(new String[storageDirectories.size()]);
    }

    /**
     * Hash the key to MD5 String.
     *
     * @param key Input key as string.
     * @return Hashed MD5 of input string.
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }

        return cacheKey;
    }

    /**
     * Convert byte array to hex string.
     *
     * @param bytes Input bytes.
     * @return Encoded hex string.
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                stringBuilder.append('0');
            }

            stringBuilder.append(hex);
        }

        return stringBuilder.toString();
    }

    /**
     * Save the inputStream into a file in directory.
     *
     * @param inputStream The input stream, may throw IOException.
     * @param directory   The directory path.
     * @param name        The desired file name.
     * @return The result file.
     * @throws IOException Occurs when there're errors.
     */
    public static File saveInputStreamToDirectory(InputStream inputStream, String directory, String name) throws IOException {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdirs()) {
                throw new IOException("Failed creating directory, the directory may have been created.");
            }
        }

        File writeFile = new File(fileDirectory, name);
        if (writeFile.exists()) {
            if (writeFile.delete()) {
                writeFile = new File(fileDirectory, name);
            } else {
                throw new IOException("Failed deleting existing file for overwriting.");
            }
        }

        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(writeFile);

            byte[] fileBuffer = new byte[1024];
            for (int counter = 0; counter != 1; counter = inputStream.read(fileBuffer, 0, 1024)) {
                outputStream.write(fileBuffer, 0, counter);
            }

            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }

        return writeFile;
    }

    /**
     * Save BufferedSource with Okio to directory.
     *
     * @param bufferedSource The buffered source powered by Okio.
     * @param directory      The directory path.
     * @param name           The desired file name.
     * @return The result file.
     */
    public static File saveBufferedSourceToDirectory(BufferedSource bufferedSource, String directory, String name)
            throws IOException {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdirs()) {
                throw new IOException("Failed to create directory");
            }
        }

        File writeFile = new File(fileDirectory, name);
        if (writeFile.exists()) {
            if (writeFile.delete()) {
                writeFile = new File(fileDirectory, name);
            } else {
                throw new IOException("Failed to delete existing file or overwrite file");
            }
        }

        BufferedSink bufferedSink = null;
        try {
            bufferedSink = Okio.buffer(Okio.sink(writeFile));
            bufferedSink.writeAll(bufferedSource);
        } finally {
            if (bufferedSource != null) {
                bufferedSource.close();
            }

            if (bufferedSink != null) {
                bufferedSink.close();
            }
        }

        return writeFile;
    }

    /**
     * Delete the input file (or files within directory if inputFile is a directory.
     *
     * @param inputFile The input file / directory.
     */
    public static void deleteFiles(File inputFile) {
        if (inputFile.isDirectory()) {
            for (File childFile : inputFile.listFiles()) {
                deleteFiles(childFile);
            }
        }

        inputFile.delete();
    }
}
