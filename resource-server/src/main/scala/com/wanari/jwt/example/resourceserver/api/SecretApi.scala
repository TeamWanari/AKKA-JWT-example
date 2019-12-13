package com.wanari.jwt.example.resourceserver.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class SecretApi {
  val getSecret: Route = path("secret") {
    complete("SH! This is a secret! You can detonate Printers!")
  }
}
