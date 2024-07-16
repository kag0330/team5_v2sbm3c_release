package dev.mvc.team5.util;


import org.springframework.stereotype.Component;

@Component(value = "ThymeleafUtils")
public class ThymeleafUtils {
  public String format(String format, Object... args) {
    return String.format(format, args);
  }
  
  public String formatnbsp(String format, Object... args) {
    String formattedString = String.format(format, args);
    return formattedString.replace(" ", "&nbsp;");
  }
  
  public static synchronized String formatByteStr(long size) {
    String str = "";

    if (size < 1024) { // 1 KB 이하, 1024 byte 미만이면
      str = size + " Byte";
    } else if (size < 1024 * 1024) { // 1 MB 이하, 1048576 byte 미만이면 KB
      str = (int) (Math.ceil(size / 1024.0)) + " KB";
    } else if (size < 1024 * 1024 * 1024) { // 1 GB 이하, 1073741824 byte 미만
      str = (int) (Math.ceil(size / 1024.0 / 1024.0)) + " MB";
    } else if (size < 1024L * 1024 * 1024 * 1024) { // 1 TB 이하, 큰 정수 표현을 위해 int -> long형식으로 변환
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0)) + " GB";
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024) { // 1 PT 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " TB";
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 EX 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " PT";
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 ZB 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " EX";
    } else if (size < 1024L * 1024 * 1024 * 1024 * 1024 * 1024 * 1024 * 1024) { // 1 YB 이하
      str = (int) (Math.ceil(size / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0 / 1024.0)) + " ZB"; // Google이 사용 중인 단위
    }

    return str;
  }
}

