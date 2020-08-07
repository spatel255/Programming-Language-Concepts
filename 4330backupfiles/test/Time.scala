object Time {
  case class Time(hour: Int, minute: Int, second: Int, ampm: String)
  def next(t1: Time): Time = {
    var s = 0;
    var mins = 0;
    var hr = 0;
    var ampm = "";
    if (t1.second + 1 >= 60) {
      s = 0;
      if (t1.minute + 1 >= 60) {
        mins = 0;
        if (t1.hour + 1 >= 12) {
          //change to am or pm
          if (t1.ampm == "AM") {
            ampm = "PM";
          } else if (t1.ampm == "PM") {
            ampm = "AM";
          }
          if (t1.hour + 1 > 12) {
            hr = 1;
          } else {
            hr = t1.hour + 1;
          }
        } else {
          hr = t1.hour + 1;
        }
      } else {
        mins = t1.minute + 1;
      }
      new Time(hr, mins, s, ampm);
    } else {
      new Time(t1.hour, t1.minute, t1.second + 1, t1.ampm);
    }
  }
  def add(t1: Time, n: Int): Time = {
    if (n == 1) next(t1);
    else {
      val t = next(t1);
      add(t, n - 1);
    }

  }
  def lag(t: Time): Int = {
    t.hour * 3600 + t.minute * 60 + t.second;
  }
  def secondsBetween(t1: Time, t2: Time): Int = {
    (lag(t1) - lag(t2)).abs
  }
}
