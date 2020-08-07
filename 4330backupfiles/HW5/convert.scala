def convertNum2Binary(x: Int): String = {
  if (x > 0) {
    val a = x % 2;
    convertNum2Binary(x / 2) + "" + a;
  } else {
    "";
  }
}
def convertFraction2Binary(x: Double): String = {
  if (x > 0) {
    val a = x % 2;
    "" + a + "" + convertFraction2Binary(x / 2);
  } else {
    "";
  }
}
convertNum2Binary(100);
convertFraction2Binary(.375)
