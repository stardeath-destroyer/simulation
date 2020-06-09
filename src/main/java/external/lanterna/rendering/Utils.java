package external.lanterna.rendering;

public class Utils {

  public static boolean inBounds(int value, int includedMin, int excludedMax) {
    return value >= includedMin && value < excludedMax;
  }
}
