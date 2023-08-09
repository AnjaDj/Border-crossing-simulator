package vozila;

public class EmptyCollectionException extends Exception{

    public EmptyCollectionException(String message){
        super(message);
    }
    public EmptyCollectionException(){
        super();
    }

}
