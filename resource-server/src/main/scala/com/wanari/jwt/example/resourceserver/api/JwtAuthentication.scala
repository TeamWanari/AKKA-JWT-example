package com.wanari.jwt.example.resourceserver.api

import akka.http.scaladsl.server.Directive1
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.Credentials
import com.wanari.jwt.example.resourceserver.api.JwtAuthentication.JwtPayload
import com.wanari.jwt.example.resourceserver.config.Config.JwtConf
import pdi.jwt.{JwtAlgorithm, JwtSprayJson}
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

class JwtAuthentication(jwtConf: JwtConf) {

  def authenticate: Directive1[JwtPayload] = {
    authenticateOAuth2[JwtPayload](realm = "", validateCredentials)
  }

  private def validateCredentials(credentials: Credentials): Option[JwtPayload] = {
    credentials match {
      case Credentials.Provided(token) =>
        decode(token)
      case Credentials.Missing =>
        None
    }
  }

  private def decode(jwt: String): Option[JwtPayload] = {
    JwtSprayJson
      .decodeJson(jwt, jwtConf.publicKey, Seq(JwtAlgorithm.RS256))
      .map(_.convertTo[JwtPayload])
      .toOption
  }
}

object JwtAuthentication {
  implicit val jwtPayloadFormatter: RootJsonFormat[JwtPayload] = jsonFormat1(JwtPayload)
  final case class JwtPayload(
      username: String,
  )
}
