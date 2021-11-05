import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
public class keygen {
    public keygen() {}
    public static String generateKey() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) { sb.append(String.format("%02x", b)); }
        return sb.toString().toUpperCase();
    }
    public static String generateHMAC(String key, int compIndex) { return Hashing.sha256().hashString(
            key+compIndex, StandardCharsets.UTF_8).toString().toUpperCase();
    }
    public static void printKey(String key) { System.out.println("HMAC key: "+key); }
}
