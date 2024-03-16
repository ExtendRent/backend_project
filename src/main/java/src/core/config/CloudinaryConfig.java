package src.core.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        String CLOUD_NAME = "dtnjuaxaa";
        config.put("cloud_name", CLOUD_NAME);
        String API_KEY = "636629149633282";
        config.put("api_key", API_KEY);
        String API_SECRET = "Hm05tc_JHUTTJJSoD5eyQNU_zTA";
        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }
}