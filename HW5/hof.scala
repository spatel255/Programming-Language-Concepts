def compose(f: Int => Int, g: Int => Int)(x: Int): Int = {
  val first = g(x);
  val sec = f(first);
  sec;
}
def repeated(f: Int => Int, n: Int)(x: Int): Int = {
  if (n != 1) repeated(f: Int => Int, n - 1: Int)(f(x)) else f(x);
}

compose(x => x * x, x => x + 1)(6);
repeated(x => 3 * x, 4)(8);
