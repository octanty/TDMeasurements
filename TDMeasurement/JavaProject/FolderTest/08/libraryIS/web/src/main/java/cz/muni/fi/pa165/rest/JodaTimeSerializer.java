package cz.muni.fi.pa165.rest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.joda.time.LocalDate;

/**
 *
 * @author Mjartan
 */
class JodaTimeSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate src, Type srcType, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("y", src.getYear());
        obj.addProperty("m", src.getMonthOfYear());
        obj.addProperty("d", src.getDayOfMonth());
        return obj;
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        int y = obj.get("y").getAsInt();
        int m = obj.get("m").getAsInt();
        int d = obj.get("d").getAsInt();
        return new LocalDate(y, m, d);
    }
}
