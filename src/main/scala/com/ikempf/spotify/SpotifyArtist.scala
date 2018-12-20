package com.ikempf.spotify

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class SpotifyArtists(items: List[SpotifyArtist], next: Option[String], total: Int)

object SpotifyArtists {
  implicit val decoder: Decoder[SpotifyArtists] = deriveDecoder
}

case class SpotifyArtist(name: String)

object SpotifyArtist {
  implicit val decoder: Decoder[SpotifyArtist] = deriveDecoder
}
