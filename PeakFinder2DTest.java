import java.util.*;

public class PeakFinder2DTest{
  public static void main(String[] args){
    test(0, 0);
    test(1, 0);
    test(0, 1);
    test(1, 1);
    test(1, 10);
    test(10, 1);
    test(13, 15);
  }

  public static void test(int row, int column){
    System.out.println("row = " + row + ", column = " + column);
    Integer[][] doubleArray = new Integer[row][column];
    Random random = new Random();
    int range = row * column;
    for(int i = 0; i < row; i++){
      for(int j = 0; j < column; j++)
        doubleArray[i][j] = random.nextInt(range + 1);
      System.out.println(Arrays.toString(doubleArray[i]));
    }

    Integer peak = findPeak(doubleArray);
    System.out.println("peak = " + peak);
  }

  public static <E extends Comparable<? super E>> E findPeak(E[][] doubleArray){
    if(!isValidInput(doubleArray))
      return null;

    return findPeakProcess(doubleArray, 0, doubleArray.length - 1);
  }

  private static <E extends Comparable<? super E>> E findPeakProcess(E[][] doubleArray, int left, int right){
    if(left <= right){
      int mid = (right - left) / 2 + left;
      int maxIndex = findMax(doubleArray[mid]);

      E midItem = doubleArray[mid][maxIndex];
      int newRight = mid - 1;
      int newLeft = mid + 1;

      if(newRight >= left && midItem.compareTo(doubleArray[newRight][maxIndex]) < 0)
        return findPeakProcess(doubleArray, left, newRight);
      else if(newLeft <= right && midItem.compareTo(doubleArray[newLeft][maxIndex]) < 0)
        return findPeakProcess(doubleArray, newLeft, right);
      else
        return midItem;
    }

    throw new RuntimeException("you should not see me");
  }

  private static <E extends Comparable<? super E>> int findMax(E[] array){
    if(array == null || array.length == 0)
      throw new IllegalArgumentException("array is wrong");

    int maxIndex = 0;
    for(int i=1; i<array.length; i++){
      if(array[maxIndex].compareTo(array[i]) < 0)
        maxIndex = i;
    }
    return maxIndex;
  }

  private static <E extends Comparable<? super E>> boolean isValidInput(E[][] doubleArray){
    if(doubleArray == null || doubleArray.length == 0)
      return false;

    if(doubleArray[0] == null || doubleArray[0].length == 0)
      return false;

    for(int i=0; i<doubleArray.length - 2; i++)
        if( doubleArray[i] == null || doubleArray[i].length != doubleArray[i+1].length)
          return false;

    return true;
  }
}
