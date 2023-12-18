package source_files.core.results;

public class SuccessDataResult<T> extends DataResult<T> {

// birden fazla veri türüyle çalışabilmemiz için generic tipler ile çalışırırz.


    public SuccessDataResult(T data, String message) {

        super(data, true, message); //Üst klasın constructor ı.
    }

    public SuccessDataResult(T data) {

        super(data, true);
    }

    public SuccessDataResult(String message) {

        super(null, true, message);
    }


    public SuccessDataResult() {
        super(null, true);
    }


}
