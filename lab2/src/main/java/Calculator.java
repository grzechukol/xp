import java.util.Arrays;

public class Calculator {
 public int add(String numbers) throws Exception {
  var splitRegEx = ",|\n";

  if (numbers.startsWith("//")) {
   var splitOnFirstNewLine = numbers.split("\n", 2);
   var customDelimiter = splitOnFirstNewLine[0].replace("//", "");
   splitRegEx += "|" + customDelimiter;
   numbers = splitOnFirstNewLine[1];
  }

  var splitedNumbers = Arrays.stream(numbers.split(splitRegEx, -1))
          .filter(item -> !item.isEmpty())
          .mapToInt(Integer::parseInt)
          .toArray();

  var negatives = Arrays.stream(splitedNumbers).filter(x -> x < 0)
          .mapToObj(String::valueOf)
          .toArray(String[]::new);

  if (Arrays.stream(negatives).findAny().isPresent()) {
   throw new Exception("Negatives not allowed: " + String.join(",", negatives));
  }

  return Arrays.stream(splitedNumbers).reduce(0, Integer::sum);
 }
}
