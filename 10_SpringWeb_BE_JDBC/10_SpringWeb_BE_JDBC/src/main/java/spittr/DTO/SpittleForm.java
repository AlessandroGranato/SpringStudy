package spittr.DTO;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.*;
import java.util.Date;

public class SpittleForm {

    @NotNull
    @Size(min=1, max=140,  message="{message.size}")
    private String message;

    @Min(value=-180, message="{latitude.size}")
    @Max(value=180, message="{latitude.size}")
    private Double latitude;

    @Min(value=-90,  message="{longitude.size}")
    @Max(value=90,  message="{longitude.size}")
    private Double longitude;

    public SpittleForm() {

    }

    public SpittleForm(String message, Double latitude, Double longitude) {
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SpittleForm{" +
                "message='" + message + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
