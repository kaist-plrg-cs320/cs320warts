package cs320warts

import org.wartremover.{WartTraverser, WartUniverse}

object MutableDataStructures extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    val ProductIterator = TermName("productIterator")
    val disabledPackages = List(
      "scala.collection.mutable",
      "scala.collection.concurrent",
      "java.util"
    )
    val disabledTypes = List("StringBuilder", "Iterator", "Array")

    new u.Traverser {
      override def traverse(tree: Tree): Unit = tree match {
        // Ignore trees marked by SuppressWarnings
        case t if hasWartAnnotation(u)(t) =>

        // Ignore synthetic productIterator()
        case DefDef(_, ProductIterator, _, _, _, _) if isSynthetic(u)(tree) =>

        case t =>
          val tpe = t.tpe
          if (tpe != null) {
            val typ = tpe.typeConstructor.toString

            val pkgOpt = disabledPackages.find(typ.contains)
            for (pkg <- pkgOpt) error(u)(t.pos, s"$pkg package is disabled")
            if (pkgOpt.nonEmpty) return

            val typOpt = disabledTypes.find(_ == typ)
            for (typ <- typOpt) error(u)(t.pos, s"$typ is disabled")
            if (typOpt.nonEmpty) return
          }

          super.traverse(t)
      }
    }
  }
}
