package liuzongzong.util;

/**
 * Created by liuyz on 2016/12/25.
 */
public class ResponseEntity<T> {
    public String flag;
    public String msg;
    public T data;

    public static class Builder<T>{
        private String flag;
        private String msg;
        private T data;

        public Builder flag(String flag){
            this.flag=flag;
            return this;
        }

        public Builder msg(String msg){
            this.msg=msg;
            return this;
        }

        public Builder data(T data){
            this.data=data;
            return this;
        }

        public Builder success(){
            this.flag="1";
            this.msg="SUCCESS";
            return this;
        }
        public Builder failed(){
            this.flag="0";
            this.msg="FAILED";
            return this;
        }

        public ResponseEntity<T> build(){
            return new ResponseEntity<T>(this);
        }

    }

    public ResponseEntity(Builder<T> builder){
        this.flag=builder.flag;
        this.msg=builder.msg;
        this.data=builder.data;
    }
}
