package orm;

import orm.annotations.Column;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.IntStream;

public class EntityManager<E> implements DBContext<E> {
    private static final String INSERT_STATEMENT = "INSERT INTO `%s` (%s) VALUES (%s)";
    private static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE %s";
    private static final String SELECT_FIRST_WITH_WHERE = "SELECT * FROM %s WHERE 1 %s LIMIT 1";

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field idField = getId(entity.getClass());
        idField.setAccessible(true);

        Object idValue = idField.get(entity);

        if (idValue == null || (int) idValue <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, idField);
    }

    public Iterator<E> find(Class<E> table) {
        return find(table, "1 = 1");
    }

    public Iterator<E> find(Class<E> table, String where) {
        return null;
    }

    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return findFirst(table, null);
    }

    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String tableName = table.getSimpleName() + "s";
        Statement statement = connection.createStatement();
        String query = String.format(SELECT_FIRST_WITH_WHERE,
                tableName,
                where == null ? "" : "AND " + where);

        ResultSet rs = statement.executeQuery(query);
        rs.next();

        return this.mapResultToEntity(rs, table);
    }

    private E mapResultToEntity(ResultSet rs, Class<E> table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        E entity = table.getDeclaredConstructor().newInstance();

        Arrays.stream(table.getDeclaredFields())
                .forEach(f -> {
                    f.setAccessible(true);
                    String name = f.getName();
                    Object value = null;

                    try {
                        value = this.getFieldValueFromResultSet(rs, name, f.getType());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        f.set(entity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        return entity;
    }

    private Object getFieldValueFromResultSet(ResultSet rs, String name, Class<?> type) throws SQLException {
        Object result = null;

        if (type == int.class || type == Integer.class) {
            result = rs.getInt(name);
        } else if (type == double.class || type == Double.class) {
            result = rs.getDouble(name);
        } else if (type == long.class || type == Long.class) {
            result = rs.getLong(name);
        } else if (type == boolean.class || type == Boolean.class) {
            result = rs.getBoolean(name);
        } else if (type == Date.class) {
            result = rs.getDate(name);
        } else if (type == String.class) {
            result = rs.getString(name);
        }

        return result;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() ->
                        new UnsupportedOperationException("Entity does not have primary key")
                );
    }

    private boolean doInsert(E entity) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String query = String.format(INSERT_STATEMENT,
                tableName,
                String.join(", ", tableFields),
                String.join(", ", tableValues)
        );

        return this.connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String[] fieldValuePairs = IntStream.range(0, tableFields.length)
                .mapToObj(i -> "`" + tableFields[i] + "`=" + tableValues[i])
                .toArray(String[]::new);

        String whereId = "`" + primary.getName() + "`='" + primary.get(entity) + "'";

        String query = String.format(UPDATE_STATEMENT,
                tableName,
                String.join(", ", fieldValuePairs),
                whereId
        );

        return this.connection.prepareStatement(query).execute();
    }

    private String[] getTableFields(E entity) {
        return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    return f.getName();
                })
                .toArray(String[]::new);
    }

    private String[] getTableValues(E entity) {
        return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    try {
                        Object value = f.get(entity);
                        return "'" + value.toString() + "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .toArray(String[]::new);
    }

    private String getTableName(Class aClass) {
        return aClass.getSimpleName().toLowerCase().concat("s");
    }
}
