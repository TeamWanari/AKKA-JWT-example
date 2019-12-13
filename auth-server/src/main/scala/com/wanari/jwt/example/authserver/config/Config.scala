package com.wanari.jwt.example.authserver.config

import pureconfig.generic.ProductHint
import pureconfig.{CamelCase, ConfigFieldMapping, ConfigSource}

class Config {
  import Config._
  import ConfigSource.default.at
  import pureconfig.generic.auto._

  implicit def hint[T]: ProductHint[T] = ProductHint(ConfigFieldMapping(CamelCase, CamelCase))

  implicit val jwtConf: JwtConf = at("jwt").loadOrThrow[JwtConf]
}

object Config {
  case class JwtConf(
      privateKey: String,
  )
}
