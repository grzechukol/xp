import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorBinary {
 public int noOfBits1(String numbers) throws Exception{
  var splitRegEx = ";|\\s+";
  
  List<Integer> splitNumbers = new ArrayList<>();
  String[] numbersList = Arrays.stream(
          numbers.split(splitRegEx, -1))
          .filter(item -> !item.isEmpty())
          .toArray(String[]::new);

  try {
   for (String number: numbersList) {
     splitNumbers.add(Integer.parseInt(number));
   }
  } catch (Exception e) {
   throw new Exception("Invalid number passed!");
  }

  for (int number: splitNumbers) {
   if (number < 0 || number > 255){
    throw new Exception("Number out of range!");
   }
  }

  var counter = 0;
  for (var number: splitNumbers) {
   var binary = Integer.toBinaryString(number);
   counter += (int)binary.chars().filter(c -> c == '1').count();
  }

  return counter;
 }
}


