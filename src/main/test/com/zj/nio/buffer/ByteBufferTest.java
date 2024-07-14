package com.zj.nio.buffer;

import static com.zj.nio.utils.ByteBufferUtil.debugAll;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author junzhou
 * @date 2022/8/13 9:20
 * @since 1.8
 */
@Slf4j
public class ByteBufferTest {
    public static void main(String[] args) {
//        testByteBuffer();
//        testSplitBuffer();
        String fromPath = "/Users/zhoujun09/IdeaProjects/leetcode/src/main/resources/files/data.txt";
        String toPath = "/Users/zhoujun09/IdeaProjects/leetcode/src/main/resources/files/to.txt";
        transferToFile(fromPath, toPath);

    }

    /**
     * 两个 Channel 传输数据
     */
    private static void transferToFile(String fromPath, String toPath) {
        try (
                FileInputStream fromStream = new FileInputStream(fromPath);
                FileOutputStream toStream = new FileOutputStream(toPath);
        ) {
            FileChannel from = fromStream.getChannel();
            FileChannel to = toStream.getChannel();
            // 效率高，底层会利用操作系统的零拷贝进行优化
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testSplitBuffer() {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void testByteBuffer() {
        try (RandomAccessFile file = new RandomAccessFile("/Users/zhoujun09/IdeaProjects/leetcode/src/main/resources/files/data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do {
                // 向 buffer 写入
                int readByteLen = channel.read(buffer);
                log.info("读到字节数为：{}", readByteLen);
                if (readByteLen == -1) {
                    break;
                }
                // 切换 buffer 读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    log.info("{}", (char) buffer.get());
                }
                // 切换为 buffer 写模式
                buffer.clear();

            } while (true);

        } catch (IOException e) {
            log.error("出错啦！", e);
        }
    }

    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            // 找到一条完整的信息
            if (source.get(i) == '\n') {
                System.out.println(i);
                // 把这条完整的信息写入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                // 从 source 读，向 target 写
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }
}
