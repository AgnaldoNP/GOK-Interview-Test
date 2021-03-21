package dev.agnaldo.gokinterviewtest.common.mapper

import dev.agnaldo.gokinterviewtest.data.source.local.entity.SpotlightDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight

fun ProductsResponse.SpotlightResponse.toDBEntity() =
    SpotlightDBEntity(
        name = this.name,
        bannerURL = this.bannerURL,
        description = this.description
    )

fun List<ProductsResponse.SpotlightResponse>.toDBEntities() = this.map {
    it.toDBEntity()
}


fun SpotlightDBEntity.toEntity() =
    Spotlight(
        name = this.name,
        bannerURL = this.bannerURL,
        description = this.description
    )

fun List<SpotlightDBEntity>.toEntities() = this.map {
    it.toEntity()
}
