package dev.agnaldo.gokinterviewtest.common.mapper

import dev.agnaldo.gokinterviewtest.data.source.local.entity.CashDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse

fun ProductsResponse.CashResponse.toDBEntity() =
    CashDBEntity(
        title = this.title,
        bannerURL = this.bannerURL,
        description = this.description
    )
