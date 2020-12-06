import Days.Day3
import Days.Day6

import scala.io.Source

object Main extends App {

  val day3Input: List[String] = Source.fromResource("puzzle3.txt").getLines.toList
  val day3 = Day3.Puzzle(day3Input)
  println("Solution for day 3 part 1 is")
  println(day3.answerPart1)
  println("")
  println("Solution for day 3 part 2 is")
  println(day3.answerPart2)
  println("")

  val day6Input: List[String] = Source.fromResource("puzzle6.txt").getLines().toList
  val day6 = Day6.Puzzle(day6Input)

  println("Solution for day 6 part 1 is")
  println(day6.answerPart1)

}
