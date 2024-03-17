package src.service.image.user.model;

public enum DefaultUserImage {

    ADMIN_IMAGE("https://image.cnnturk.com/i/cnnturk/75/740x416/5e8d8a1eb57f150558c1f9ca.jpg"),
    EMPLOYEE_IMAGE("https://avatars.githubusercontent.com/u/92371744?v=4"),
    CUSTOMER_IMAGE("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800"),
    DEFAULT_USER_IMAGE("https://www.repol.copl.ulaval.ca/wp-content/uploads/2019/01/default-user-icon.jpg");

    private final String url;

    DefaultUserImage(String url) {
        this.url = url;
    }

    public static DefaultUserImage[] getAll() {
        return values();
    }

    public String getUrl() {
        return url;
    }

}
