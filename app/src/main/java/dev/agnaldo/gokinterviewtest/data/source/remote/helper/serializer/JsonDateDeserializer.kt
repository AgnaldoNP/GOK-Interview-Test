package agnaldo.test.certificationtrainingapp.data.source.remote.helper.serializer

import agnaldo.test.certificationtrainingapp.data.source.remote.helper.serializer.JsonDateDeserializer.Companion.MAIN_DATE_FORMAT
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

private val LIST_DATE_FORMAT = listOf(
    MAIN_DATE_FORMAT, "dd-MM-yyyy",
    "dd-MM-yyyy HH:mm:ss", "yyyy-MM-dd",
    "dd/MM/yyyy HH:mm:ss", "dd-MM-yyyy",
    "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd",
    "yyyy-MM-dd HH:mm:ss.SSSZZZZ",
    "MMM dd, yyyy hh:mm:ss tt"
)


class JsonDateDeserializer : JsonDeserializer<Date>, JsonSerializer<Date> {
    companion object {
        const val MAIN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date {
        json?.let {
            LIST_DATE_FORMAT.forEach { dateFormat ->
                try {
                    return@deserialize SimpleDateFormat(
                        dateFormat, Locale.US
                    ).parse(it.asString)!!
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        throw Exception()
    }

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        with(SimpleDateFormat(MAIN_DATE_FORMAT, Locale.US)) {
            return JsonPrimitive(format(src!!))
        }
    }
}
