package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;

public interface DBContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterator<E> find (Class<E> table);

    Iterator<E> find (Class<E> table, String where);

    E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;
}
