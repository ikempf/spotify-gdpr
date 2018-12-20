package com.ikempf
import com.ikempf.deezer.Deezer
import com.ikempf.spotify.Spotify
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Observable

object Migrate extends App {

  val spotifyBearer  = ""
  val deezerApiToken = ""
  val deezerUserId   = ""


  println("Getting spotify artists")
  val spotify = new Spotify(spotifyBearer)
  val artists = spotify.listArtists()
  println(s"Found ${artists.size} artists")
  println(artists.map(_.name).mkString("[", "' ", "]"))

  println("Importing artists into deezer")
  val deezer = new Deezer(deezerUserId, deezerApiToken)
  val added =
    Observable
      .apply(artists: _*)
      .map(artist => {
        val res = deezer.addToFavorites(artist)
        Thread.sleep(500) // no throttle in monix oO
        res
      })
      .toListL
      .runSyncUnsafe()

  println(s"Imported ${added.count(identity)}/${added.size} artists to Deezer")

}
