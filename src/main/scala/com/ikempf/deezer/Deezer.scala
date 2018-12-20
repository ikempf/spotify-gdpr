package com.ikempf

package deezer

import com.ikempf.spotify.SpotifyArtist
import com.softwaremill.sttp.quick._
import io.circe.parser.parse

class Deezer(userId: String, oauthAccessToken: String) extends App {

  def addToFavorites(artist: SpotifyArtist): Boolean =
    search(artist.name).data.headOption
      .fold {
        println(s"Artist $artist not found")
        false
      }(addToFavorites)

  private def addToFavorites(artist: DeezerArtist): Boolean = {
    val addUri = uri"https://api.deezer.com/user/$userId/artists?artist_id=${artist.id}&access_token=$oauthAccessToken"

    val resp =
      sttp
        .post(addUri)
        .send()
        .body
        .getR

    val added = parse(resp).getR.as[Boolean].getR
    if (added) println(s"Added ${artist.name}") else println(s"Failed adding ${artist.name}")
    added
  }

  private def search(artist: String): DeezerArtistSearchResult = {
    val searchUri = uri"https://api.deezer.com/search/artist/?q=$artist&index=0&limit=1&output=json"

    val resp =
      sttp
        .get(searchUri)
        .send()
        .body
        .getR

    parse(resp).getR.as[DeezerArtistSearchResult].getR
  }

}
