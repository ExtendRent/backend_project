package src.service.license.model;

public enum DefaultCarDrivingLicenseType {


    //-----------Cars----------
    NONE("Yok"),   //Yok
    B("Otomobil ve Kamyonet"),   //Otomobil ve Kamyonet
    BE("Römorklu Otomobil ve Kamyonet"),  //Römorklu Otomobil ve Kamyonet
    C1("7500 kg'a kadar Kamyon ve Çekici"),  //7500 kg'a kadar Kamyon ve Çekici
    C1E("12000 kg'a kadar Kamyon ve Çekici"), //12000 kg'a kadar Kamyon ve Çekici
    C("Kamyon ve Çekici"),   //Kamyon ve Çekici
    CE("Römorklu Kamyon ve Çekici"),  //Römorklu Kamyon ve Çekici
    D1("Minibüs"),  //Minibüs
    D1E("Römorklu Minibüs"), //Römorklu Minibüs
    D("Minibüs ve Otobüs"),   //Minibüs ve Otobüs
    E("Römorklu Minibüs ve Otobüs"),   //Römorklu Minibüs ve Otobüs
    F("Lastik Tekerlekli Traktör"),   //Lastik Tekerlekli Traktör
    G("İş Makinası");    //İş Makinası
    private final String label;

    DefaultCarDrivingLicenseType(String label) {
        this.label = label;
    }

    public static DefaultCarDrivingLicenseType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

}
