package com.wanari.jwt.example.resourceserver.config

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
      publicKey: String,
  )
}
