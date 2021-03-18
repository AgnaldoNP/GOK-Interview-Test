package dev.agnaldo.gokinterviewtest.common.mapper

import dev.agnaldo.gokinterviewtest.data.source.local.entity.ProductDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse

fun ProductsResponse.ProductResponse.toDBEntity() =
    ProductDBEntity(
        name = this.name,
        imageURL = this.imageURL,
        description = this.description
    )
