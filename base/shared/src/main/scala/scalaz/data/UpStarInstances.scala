package scalaz
package data

trait UpStarInstances {
  implicit def upstarProfunctor[F[_]](implicit F: Functor[F]): Profunctor[UpStar[F, ?, ?]] = new Profunctor[UpStar[F, ?, ?]] {
    override def lmap[A, B, C](fab: UpStar[F, A, B])(ca: C => A): UpStar[F, C, B] =
      UpStar(c => fab.run(ca(c)))

    override def rmap[A, B, C](fab: UpStar[F, A, B])(bc: B => C): UpStar[F, A, C] =
      UpStar(a => F.map(fab.run(a))(bc))

    override def dimap[A, B, C, D](fab: UpStar[F, A, B])(ca: C => A)(bd: B => D): UpStar[F, C, D] =
      UpStar(c => F.map(fab.run(ca(c)))(bd))
  }
}
