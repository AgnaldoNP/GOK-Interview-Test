package dev.agnaldo.gokinterviewtest.domian.entity

data class Cash(
    val title: String,
    val bannerURL: String,
    val description: String
){

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
