package orm.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;
    Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
    Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    boolean delete(Class<E> table) throws SQLException;
    boolean delete(Class<E> table, String where) throws SQLException;
}
