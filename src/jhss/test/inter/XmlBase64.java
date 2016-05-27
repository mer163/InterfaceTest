package jhss.test.inter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;





public class XmlBase64 {
  private static final String TAG = "XmlBase64";

  /** 10 encode method */
  static final char[][] baseSting = {
      "789_-ABCDEFGHIJKLMNOPQRSTUVWXYZ6abcdefghijklmnopqrstuvwxyz501234".toCharArray(),
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".toCharArray(),
      "3456789_-ABCDEFGHIJKLMNOPQRSTUVWX2YZabcdefghijklmnopqr1stuvwxyz0".toCharArray(),
      "-ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz9012345678".toCharArray(),
      "_-ABCDEFGHIJKLMNOPQRSTUVWXYZ9abcdefghijklmnopqrstuvwxyz801234567".toCharArray(),
      "9_-ABCDEFGHIJKLMNOPQRSTUVWXYZ8abcdefghijklmnopqrstuvwxyz70123456".toCharArray(),
      "6789_-ABCDEFGHIJKLMNOPQRSTUVWXYZ5abcdefghijklmnopqrstuvwxyz40123".toCharArray(),
      "89_-ABCDEFGHIJKLMNOPQRSTUVWXYZ7abcdefghijklmnopqrstuvwxyz6012345".toCharArray(),
      "456789_-ABCDEFGHIJKLMNOPQRSTUVWXY3Zabcdefghijklmnopqrs2tuvwxyz01".toCharArray(),
      "56789_-ABCDEFGHIJKLMNOPQRSTUVWXYZ4abcdefghijklmnopqrstuvwxyz3012".toCharArray()};

  /** encode method index */
  static int encodeKey = 0;

  /**
   * encode byte array
   * 
   * @param data byte[]
   * @return String
   */
  public static String encode(byte[] data) {
    return encode(data, 0, data.length, null).toString();
  }

  /**
   * decode utf-8 bytes to String
   * 
   * @param bytes utf-8 bytes
   * @param start int
   * @param len int
   * @return String
   */
  public static String utf8_decode(byte[] bytes, int start, int len) {

    StringBuilder s = new StringBuilder();
    for (int i = start; i < start + len;) {
      byte b = bytes[i++];
      if ((b >> 7) == 0)
        s.append((char) b); // asicii char
      else if ((b >> 5) == (byte) 0xfe)
        s.append((char) (((b & 0x1f) << 6) | (bytes[i++] & 0x3f))); // 2 bytes char
      else if ((b >> 4) == (byte) 0xfe) // 3 bytes char
        s.append((char) (((b & 0xf) << 12) | ((bytes[i++] & 0x3f) << 6) | (bytes[i++] & 0x3f)));
    }
    return new String(s);
  }

  static Random r = new Random();

  /**
   * Encodes the part of the given byte array denoted by start and len to the Base64 format. The
   * encoded data is appended to the given StringBuilder. If no StringBuilder is given, a new one is
   * created automatically. The StringBuilder is the return value of this method.
   */
  public static StringBuilder encode(byte[] data, int start, int len, StringBuilder buf) { // 编码基础方法


    int key = r.nextInt(10);
    char[] charT = baseSting[key];

    if (buf == null) {
      buf = new StringBuilder(data.length * 3 / 2); // 自动创建字符串缓
    }

    buf.append('~');
    buf.append((char) ('0' + key));
    int end = len - 3;
    int i = start;

    while (i <= end) {
      int d =
          ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8)
              | (((int) data[i + 2]) & 0x0ff);
      buf.append(charT[(d >> 18) & 63]);
      buf.append(charT[(d >> 12) & 63]);
      buf.append(charT[(d >> 6) & 63]);
      buf.append(charT[d & 63]);

      i += 3;
    }

    if (i == start + len - 2) {
      int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);
      buf.append(charT[(d >> 18) & 63]);
      buf.append(charT[(d >> 12) & 63]);
      buf.append(charT[(d >> 6) & 63]);
      // buf.append ("=");
    } else if (i == start + len - 1) {
      int d = (((int) data[i]) & 0x0ff) << 16;
      buf.append(charT[(d >> 18) & 63]);
      buf.append(charT[(d >> 12) & 63]);
      // buf.append ("==");
    }
    return buf;
  }

  /*****
   * 解密
   * 
   * @param c 字符
   * @param key
   * @return
   */
  static int decode(char c, int key) { // 解码基础方法 //返回字符�?��字符表的位置

    char[] charT = baseSting[key];
    if (c == '*') return 0;
    for (int i = 0; i < 64; i++) {
      if (charT[i] == c) {
        return i;
      }
    }
    throw new RuntimeException("unexpected code: " + c);
  }

  /**
   * decode text
   * 
   * @param text String
   * @return String
 * @throws IOException 
   */
  public static String decode(String text) throws IOException {
    if (text == null) return null;
    if (text.length() <= 2 || text.charAt(0) != '~') {
      return text;
    }
    return new String(_decode(text), "UTF-8");
  }

  /**
   * Decodes the given Base64 encoded String to a new byte array. The byte array holding the decoded
   * data is returned.
 * @throws IOException 
   */
  private static byte[] _decode(String s) throws IOException {
    ByteArrayOutputStream bos = null;
    try {
      bos = new ByteArrayOutputStream();
      decode(s, bos);
      byte bytes[] = bos.toByteArray();
      return bytes;
    } catch (Exception e) {
      
    } finally {
      if(bos!=null){
    	  bos.close();
      }
    }
    return null;
  }

  /*** 解密 ****/
  public static void decode(String s, ByteArrayOutputStream bos) {

    int i = 0;
    int len = s.length();
    if (len <= 2 || s.charAt(0) != '~') {
      return;
    }

    int nKey = s.charAt(1) - '0';
    if (nKey < 0 || nKey > 9) {
      return;
    }

    char[] ca;
    int nAddNum = (len - 2) % 4;
    if (nAddNum > 0) {
      // padding
      nAddNum = 4 - nAddNum;
      StringBuilder sb = new StringBuilder(len + 4);
      sb.append(s);

      while (nAddNum > 0) {
        sb.append('*');
        nAddNum--;
      }
      ca = sb.toString().toCharArray();
    } else {
      ca = s.toCharArray();
    }



    i = 2;
    while (true) {
      while (i < len && ca[i] <= ' ')
        // 跳过空格位置
        i++;

      if (i == len) break;

      int tri =
          (decode(ca[i], nKey) << 18) + (decode(ca[i + 1], nKey) << 12)
              + (decode(ca[i + 2], nKey) << 6) + (decode(ca[i + 3], nKey));
      bos.write((tri >> 16) & 255);

      if (ca[i + 2] == '*') break;
      bos.write((tri >> 8) & 255);

      if (ca[i + 3] == '*') break;
      bos.write(tri & 255);
      i += 4;
    }
  }

  /**
   * return whether text needs base64 encode
   * 
   * @param text String
   * @return boolean
   */
  public static boolean needBase64(String text) {

    if (text == null || text.equals("")) return false;
    byte[] buff = null;
    try {
      buff = text.getBytes("UTF8");
      // 0x20 0x7f 英文字母的范
      for (int i = 0; i < buff.length; i++) { // 是中文返回true 否则返回false
        char b = (char) (buff[i] & 0xff);
        if (b >= 0x20 && b <= 0x7f && b != '<' && b != '>' && b != '&' && b != '\'' && b != '"')
          continue;
        return true;
      }
    } catch (UnsupportedEncodingException e) {
   
    }

    return false;

  }

}
