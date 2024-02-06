package source_files.data.types.itemTypes;

public enum DefaultDrivingLicenseType {

    //-----------Cars----------
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
    G("İş Makinası"),    //İş Makinası
    //-----------Motorcycles----------
    M("Motorlu Bisiklet"),   //Motorlu Bisiklet
    A1("125 cc'ye kadar Motosiklet"),  //125 cc'ye kadar Motosiklet
    A2("35 kw yi geçmeyen Motosiklet"),  //35 kw yi geçmeyen Motosiklet
    A("35 kw yi geçen Motosiklet"),   //35 kw yi geçen Motosiklet
    B1("4 tekerli Motosiklet");  //4 tekerli Motosiklet

    private final String label;

    DefaultDrivingLicenseType(String label) {
        this.label = label;
    }

    public static DefaultDrivingLicenseType[] getAll() {
        return values();
    }

    public String getLabel() {
        return label;
    }

}
