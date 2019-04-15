package classloader;

public class BytesUtils {
    /**
     * 把btye[] 从start 取len个字节，并把这个那个字节所对应的二进制转换为10进制
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static int bytes2Int(byte[] b, int start, int len){
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff; // 高24位置0，低8位保持
            n <<= (--len) * 8; // 第i个字节，代表的其实是数字中的高位
            sum = n + sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len){
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte)((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len){
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str){
       return str.getBytes();
    }

    public static  byte[] bytesReplace(byte[] originalBytes, int offset ,int len, byte[] replaceBytes){
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset+len, newBytes, offset + replaceBytes.length, originalBytes.length -offset - len);
        return newBytes;
    }

    public static void main(String[] args) {
        byte[] a = new byte[10];
        a[0] = 2;
        a[1] = 1;
        a[2] = -1;
        a[3] = 1;
        a[4] = 1;
        System.out.println(bytes2Int(a, 1,2));
    }

}
