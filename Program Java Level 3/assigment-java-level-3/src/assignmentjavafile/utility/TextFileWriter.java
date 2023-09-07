package assignmentjavafile.utility;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.List;

public class TextFileWriter {
    private TextFileWriter() {
        throw new IllegalStateException("Utility class");
    }
    public static void writeMultipleLines(List<String> linesOfData, String fileLocation) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileLocation, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            FileLock fileLock;
            try {
                fileLock = fileChannel.tryLock();

                linesOfData.forEach( data -> {
                    try {
                        randomAccessFile.writeBytes(data);
                        randomAccessFile.writeBytes(System.getProperty("line.separator"));
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException.getCause());
                    }
                });

                fileLock.release();
                randomAccessFile.close();
                fileChannel.close();

                System.out.println("Success write to " + fileLocation);
            } catch (OverlappingFileLockException overlappingFileLockException) {
                randomAccessFile.close();
                fileChannel.close();
            } catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
