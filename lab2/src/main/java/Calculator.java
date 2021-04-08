import java.util.Arrays;

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

  var negatives = Arrays.stream(splitedNumbers)
          .filter(x -> x < 0)
          .mapToObj(String::valueOf)
          .toArray(String[]::new);

  if (negatives.length > 0) {
   throw new Exception("Negatives not allowed: " + String.join(",", negatives));
  }

  return Arrays.stream(splitedNumbers).sum();
 }
}
