package dev.agnaldo.gokinterviewtest.domian.entity

import java.io.Serializable

data class Spotlight(
    val name: String,
    val bannerURL: String,
    val description: String
) : Serializable
