package src.service.external.model;

public enum CloudinaryConstants {

    USE_FILENAME("use_filename"),
    UNIQUE_FILENAME("unique_filename"),
    FILE_UPLOADED_SUCCESSFULLY("file uploaded successfully : "),
    FILE_UPLOADED_FAIL("file uploaded Fail "),
    OVERWRITE("overwrite"),
    PUBLIC_ID("public_id"),
    URL("url"),

    BASE_PUBLIC_ID_CAR("extendRent/carImages/"),

    BASE_PUBLIC_ID_USER("extendRent/userImages/"),

    BASE_PUBLIC_ID_BRAND("extendRent/brandImages/");

    private final String value;

    CloudinaryConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
