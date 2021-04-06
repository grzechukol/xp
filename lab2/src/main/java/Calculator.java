public class Calculator {
 public int Add(String numbers) {
  var splitNumbers = numbers.split(",");

  if (splitNumbers.length == 0){
   return 0;
  }

  if (splitNumbers.length == 1){
   if(splitNumbers[0].length() == 0) {
    return 0;
   }

   return Integer.parseInt(splitNumbers[0]);
  }

  return Integer.parseInt(splitNumbers[0]) + Integer.parseInt(splitNumbers[1]);
 }
}
