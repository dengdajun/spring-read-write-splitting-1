package liuzongzong.dao;

/**
 * Created by liuyz on 2016/12/25.
 */
public interface CommonDao<T> {
    Integer insert(T t);
    T select(T t);
}
