object MyClass {
  def wordValue(x: String): Int = {
    var len = x.length();
    if (len == 0) {
      return 0;
    } else {
      var str = x.toLowerCase();
      var first = str.charAt(0);
      var result = first.toInt - 96;
      return result + wordValue(x.slice(1, len));
    }
  };

  def main(args: Array[String]) {
    println(wordValue("Attitude"))
  }
}
