package source_files.core.results;

public class DataResult<T> extends Result {

    private final T data;

    // birden fazla veri türüyle çalışabilmemiz için generic tipler ile çalışırırz.
    public DataResult(T data, boolean success, String message) {
        super(success, message); //super - base sınıfının constructor çalıştıran.
        this.data = data;
    }


    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }


}
