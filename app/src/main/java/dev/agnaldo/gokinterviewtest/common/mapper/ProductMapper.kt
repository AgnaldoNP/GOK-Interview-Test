package dev.agnaldo.gokinterviewtest.common.mapper

import dev.agnaldo.gokinterviewtest.data.source.local.entity.ProductDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse
import dev.agnaldo.gokinterviewtest.domian.entity.Product

fun ProductsResponse.ProductResponse.toDBEntity() =
    ProductDBEntity(
        name = this.name,
        imageURL = this.imageURL,
        description = this.description
    )

fun List<ProductsResponse.ProductResponse>.toDBEntities() = this.map {
    it.toDBEntity()
}

fun ProductDBEntity.toEntity() =
    Product(
        name = this.name,
        imageURL = this.imageURL,
        description = this.description
    )

fun List<ProductDBEntity>.toEntities() = this.map {
    it.toEntity()
}
