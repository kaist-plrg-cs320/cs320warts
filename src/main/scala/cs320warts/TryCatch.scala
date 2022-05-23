package cs320warts

import org.wartremover.{WartTraverser, WartUniverse}

object TryCatch extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    new u.Traverser {
      override def traverse(tree: Tree): Unit = tree match {
        // Ignore trees marked by SuppressWarnings
        case t if hasWartAnnotation(u)(t) =>
        case Try(_, _, _) => error(u)(tree.pos, s"try-catch is disabled")
        case t => super.traverse(t)
      }
    }
  }
}
