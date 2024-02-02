package dev.tonimatas.packetlib;

import java.util.ArrayList;
import java.util.List;

public class Splitter {
    public static List<byte[]> splitPayloadPacket(int readableBytes, byte[] originalBytes, int realMaxSize) {
        int maxSize = realMaxSize-60;
        int parts = readableBytes / maxSize + 1;
        int currentPart = 0;
        int currentByte = 0;

        byte[] packetFixerBytes = ("packet" + currentPart + "fixer").getBytes();

        List<byte[]> bytes = new ArrayList<>();
        
        while (parts > 0) {
            byte[] partBytes = new byte[maxSize];

            while (currentByte < readableBytes) {
                partBytes[currentByte] = originalBytes[currentByte];
                currentByte++;
            }

            byte[] resultBytes = new byte[packetFixerBytes.length + partBytes.length];

            System.arraycopy(packetFixerBytes, 0, resultBytes, 0, packetFixerBytes.length);
            System.arraycopy(partBytes, 0, resultBytes, packetFixerBytes.length, partBytes.length);
            
            bytes.add(resultBytes);
            currentPart++;
            parts--;
        }
        
        return bytes;    }
}
