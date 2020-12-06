package Days.Day1

import scala.annotation.tailrec

case class Puzzle(input: List[String]) {

  // input must be min length 3
  // input must be sorted
  // sum must be possible to find in the data
  private def findAddends(sum: Int, input: List[Int]): (Int, Int) = {
    @tailrec
    def go(head: Int, tail: Int, list: List[Int]): (Int, Int) = {
      head + tail match {
        case res: Int if res == sum => (head, tail)
        case res: Int if res > sum  => go(head, list.last, list.dropRight(1))
        case res: Int if res < sum  => go(list.head, tail, list.drop(1))
      }
    }
    go(input.head, input.last, input.drop(1).dropRight(1))

  }
  private val (smallAddend, bigAddend) = findAddends(2020, input.map(_.toInt).sorted)
  val answerPart1: Int = smallAddend * bigAddend

}
