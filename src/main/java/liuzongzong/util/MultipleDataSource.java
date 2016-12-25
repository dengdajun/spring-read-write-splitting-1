package liuzongzong.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by liuyz on 2016/12/25.
 */
public class MultipleDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }

    public static class DataSourceHolder{
        public static final String master="master";
        public static final String slave="slave";
        private static final ThreadLocal<String> holder=new ThreadLocal<>();
        public static final void put(String key){
            holder.set(key);
        }
        public static final String get(){
            return holder.get();
        }
        public static final void remove(){
            holder.remove();
        }
    }
}
