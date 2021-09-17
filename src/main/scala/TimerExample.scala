object TimerExample extends App {
  val max = 10000

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  var slowList: List[Int] = time {
    List.range(1, max, 1)
  }

  var fastList: List[Int] = time {
    1 to max by 1 toList
  }


  println("slowList ")
  listWalk(slowList)

  println("----------- ")
  println("fastList ")
  listWalk(fastList)

  def listWalk(l: List[Int]): Unit = {
    println(" for loop ")
    time(
      for (i <- 0 until max - 1) {
        val a = l(i) + 1
        if (a < 0) print(a)
      }
    )

    println(" stream mode ")
    time(
      l.foreach(
        x => {
          val b = x + 1
          if (b < 0) print(b)
        }
      )
    )
  }
}
