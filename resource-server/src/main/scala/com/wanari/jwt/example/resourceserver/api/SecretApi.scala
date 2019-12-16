package com.wanari.jwt.example.resourceserver.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class SecretApi(jwtAuthentication: JwtAuthentication) {
  import jwtAuthentication.authenticate

  val getSecret: Route = path("secret") {
    authenticate { _ =>
      complete("SH! This is a secret! You can detonate Printers!")
    }
  }
}
