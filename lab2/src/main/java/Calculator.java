import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
 public int add(String numbers) throws Exception {
  if (numbers == "") {
   return 0;
  }

  var delimiters = ",|\n";

  if (numbers.startsWith("//")) {
   var splitOnFirstNewLine = numbers.split("\n", 2);
   var customDelimiter = splitOnFirstNewLine[0].replace("//", "");
   delimiters += "|" + customDelimiter;
   numbers = splitOnFirstNewLine[1];
  }

  var splitedNumbers = Arrays.stream(numbers.split(delimiters, -1))
          .filter(item -> !item.isEmpty())
          .mapToInt(Integer::parseInt)
          .toArray();

  List<Integer> negatives = new ArrayList<Integer>();
  for (var number : splitedNumbers){
   if (number < 0) negatives.add(number);
  }

  if (!negatives.isEmpty()) {
   throw new Exception("Negatives not allowed: " + String.join(",", negatives.toString()));
  }

  return Arrays.stream(splitedNumbers).sum();
 }
}
