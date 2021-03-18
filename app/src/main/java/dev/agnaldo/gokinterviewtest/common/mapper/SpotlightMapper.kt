package dev.agnaldo.gokinterviewtest.common.mapper

import dev.agnaldo.gokinterviewtest.data.source.local.entity.SpotlightDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse

fun ProductsResponse.SpotlightResponse.toDBEntity() =
    SpotlightDBEntity(
        name = this.name,
        bannerURL = this.bannerURL,
        description = this.description
    )
