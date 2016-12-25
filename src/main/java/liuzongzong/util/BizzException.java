package liuzongzong.util;

/**
 * Created by liuyazong on 16/4/29 上午10:25.
 */
public class BizzException extends RuntimeException {

    private String flag;

    public BizzException(String flag, String msg) {
        super(msg);
        this.flag = flag;
    }

    public String getFlag() {
        return this.flag;
    }

    @Override
    public String toString() {
        return "BizzException{" +
                "flag='" + flag + '\'' +
                '}';
    }
}
