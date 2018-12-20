package com.ikempf
package spotify
import java.net.URI

import com.softwaremill.sttp.Uri
import com.softwaremill.sttp.quick.sttp
import io.circe.parser.parse
import com.softwaremill.sttp.quick._
import cats.implicits._

class Spotify(bearerToken: String) {

  private val startUri = uri"https://api.spotify.com/v1/me/following?limit=50&type=artist&market=from_token"

  private def getArtists(uri: Uri) = {
    val resp =
      sttp
        .headers("authorization" -> s"Bearer $bearerToken")
        .get(uri)
        .send()
        .body
        .getR

    parse(resp)
      .getR
      .hcursor
      .downField("artists")
      .as[SpotifyArtists]
      .getR
  }

  private def listArtists(uri: Uri): List[SpotifyArtist] = {
    println(s"Crawling spotify $uri")
    val artists = getArtists(uri)
    artists.items ++ artists.next.foldMap(n => listArtists(Uri(URI.create(n))))
  }

  def listArtists(): List[SpotifyArtist] = listArtists(startUri)

}
