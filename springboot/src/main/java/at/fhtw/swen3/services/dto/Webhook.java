package at.fhtw.swen3.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonTypeName("webhook")
public class Webhook {

    @JsonProperty("parcelTrackingId")
    private String parcelTrackingId;

    @JsonProperty("subscriberType")
    private String subscriberType;

    @JsonProperty("subscriberId")
    private Long subscriberId;

    public Webhook parcelTrackingId(String parcelTrackingId) {
        this.parcelTrackingId = parcelTrackingId;
        return this;
    }

    /**
     * Get parcelTrackingId
     * @return parcelTrackingId
     */
    @NotNull
    @Schema(name = "parcelTrackingId", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getParcelTrackingId() {
        return parcelTrackingId;
    }


    public void setParcelTrackingId(String parcelTrackingId) {
        this.parcelTrackingId = parcelTrackingId;
    }

    public Webhook subscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
        return this;
    }

    /**
     * Get subscriberType
     * @return subscriberType
     */
    @NotNull
    @Schema(name = "subscriberType", requiredMode = Schema.RequiredMode.REQUIRED)
    public String getSubscriberType() {
        return subscriberType;
    }


    public void setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
    }

    public Webhook subscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
        return this;
    }

    /**
     * Get subscriberType
     * @return subscriberType
     */
    @NotNull
    @Schema(name = "subscriberId", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long getSubscriberId() {
        return subscriberId;
    }


    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Webhook webhook = (Webhook) o;
        return Objects.equals(this.parcelTrackingId, webhook.parcelTrackingId) &&
                Objects.equals(this.subscriberType, webhook.subscriberType) &&
                Objects.equals(this.subscriberId, webhook.subscriberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parcelTrackingId, subscriberType, subscriberId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Webhook {\n");
        sb.append("    parcelTrackingId: ").append(toIndentedString(parcelTrackingId)).append("\n");
        sb.append("    subscriberType: ").append(toIndentedString(subscriberType)).append("\n");
        sb.append("    subscriberId: ").append(toIndentedString(subscriberId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
