package Days.Day6

case class Puzzle(input: List[String]) {

  private val (count, lastGroup) = input.foldLeft(0, Set.empty[Char]) {
    case ((count, group), person) =>
      person.toCharArray match {
        case arr: Array[Char] if arr.isEmpty => (count + group.size, Set.empty[Char])
        case arr: Array[Char]                => (count, group ++ arr.toSet)
      }
  }

  val answerPart1: Int = count + lastGroup.size

}
