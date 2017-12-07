package pz5;

/**
 * Created by panov on 10.12.15.
 */
public enum Command {

    EXIT(0);

    private int code;

    Command(int code){
            this.code = code;
        }

    public int getCode(){
        return code;
    }

}
