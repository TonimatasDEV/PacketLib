package dev.tonimatas.packetlib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Merger {
    public static byte[] bytes = null;

    public static byte[] mergePayloadPacket(byte[] originalBytes) {
        Pattern pattern = Pattern.compile("packet\\\\d+lib");
        String stringBytes = new String(originalBytes);
        Matcher matcher = pattern.matcher(stringBytes);

        if (matcher.find()) {
            byte[] bytes = stringBytes.split("fixer")[1].getBytes();

            if (Merger.bytes == null) {
                Merger.bytes = bytes;
            } else {
                System.arraycopy(bytes, 0, Merger.bytes, Merger.bytes.length, Merger.bytes.length + bytes.length);
            }
        }

        if (stringBytes.equalsIgnoreCase("finish")) {
            byte[] resultBytes = Merger.bytes;
            Merger.bytes = null;
            return resultBytes;
        }

        return null;
    }
}
