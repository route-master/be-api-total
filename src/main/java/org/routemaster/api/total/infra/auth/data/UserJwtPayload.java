package org.routemaster.api.total.infra.auth.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record UserJwtPayload(
    String baseUserId,
    String typeUserId,
    Set<String> authorities,
    UserType userType,
    JwtType jwtType
) {

    @Override
    public String toString() {
        return "UserJwtPayload{" +
            "baseUserId='" + baseUserId + '\'' +
            ", typeUserId='" + typeUserId + '\'' +
            ", authorities=" + authorities +
            ", userType=" + userType +
            ", jwtType=" + jwtType +
            '}';
    }

    public static class Deserializer implements JsonDeserializer<UserJwtPayload> {

        @Override
        public UserJwtPayload deserialize(JsonElement json, Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jsonObject = json.getAsJsonObject();
            final String baseUserId = jsonObject.get("baseUserId").getAsString();
            final String typeUserId = jsonObject.get("typeUserId").getAsString();
            final Set<String> authorities = context.deserialize(jsonObject.get("authorities"),
                Set.class);
            final UserType userType = UserType.valueOf(jsonObject.get("userType").getAsString());
            final JwtType jwtType = JwtType.valueOf(jsonObject.get("jwtType").getAsString());
            return new UserJwtPayload(baseUserId, typeUserId, authorities, userType, jwtType);
        }
    }

    public enum UserType {
        EMAIL_USER,
        EMAIL_USER_READY,
        SOCIAL_USER,
    }

    public enum JwtType {

        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
}
