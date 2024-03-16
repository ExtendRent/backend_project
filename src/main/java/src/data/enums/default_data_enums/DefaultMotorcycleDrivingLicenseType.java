package src.data.enums.default_data_enums;

public enum DefaultMotorcycleDrivingLicenseType {

    //-----------Motorcycles----------
    NONE("Yok"),   //Yok
    M("Motorlu Bisiklet"),   //Motorlu Bisiklet
    A1("125 cc'ye kadar Motosiklet"),  //125 cc'ye kadar Motosiklet
    A2("35 kw yi geçmeyen Motosiklet"),  //35 kw yi geçmeyen Motosiklet
    A("35 kw yi geçen Motosiklet"),   //35 kw yi geçen Motosiklet
    B1("4 tekerli Motosiklet");  //4 tekerli Motosiklet

    private final String label;

    DefaultMotorcycleDrivingLicenseType(String label) {
        this.label = label;
    }

    public static DefaultMotorcycleDrivingLicenseType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

}
