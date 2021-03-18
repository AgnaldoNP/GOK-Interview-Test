package agnaldo.test.certificationtrainingapp.data.source.remote.helper.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTHORIZATION_HEADER = "Authorization"
private const val AUTHORIZATION_BEARER = "Bearer %s"

private const val ACCEPT_HEADER = "Accept"
private const val ACCEPT_TYPE = "application/json"

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "TODO get token"
        return chain.proceed(
            chain.request().newBuilder().header(
                AUTHORIZATION_HEADER, AUTHORIZATION_BEARER.format(token)
            ).header(
                ACCEPT_HEADER, ACCEPT_TYPE
            ).build()
        )
    }
}
