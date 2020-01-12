package orm.models;

import orm.annotations.Column;
import orm.annotations.Id;
import orm.interfaces.DbContext;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class EntityManager<E> implements DbContext<E> {
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s";

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int) value <= 0) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    private boolean doInsert(E entity, Field primary) throws SQLException {
        String tableName = entity.getClass().getSimpleName().toLowerCase().concat("s");

        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String query = String.format(INSERT_QUERY,
                tableName,
                String.join(", ", tableFields),
                String.join(", ", tableValues));

        return this.connection.prepareStatement(query).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = entity.getClass().getSimpleName().toLowerCase().concat("s");

        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String[] tableFieldValuePairs = IntStream.range(0, tableFields.length)
                .mapToObj(i -> "`" + tableFields[i] + "`=" + tableValues[i])
                .toArray(String[]::new);

        String whereId = "`" + primary.getName() + "`='" + primary.get(entity) + "'";

        String query = String.format(UPDATE_QUERY,
                tableName,
                String.join(", ", tableFieldValuePairs),
                whereId);

        return this.connection.prepareStatement(query).execute();
    }

    private String[] getTableFields(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> {
                    f.setAccessible(true);
                    return f.getName();
                })
                .toArray(String[]::new);
    }

    private String[] getTableValues(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
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

    public Iterable<E> find(Class<E> table) {
        return null;
    }

    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    public E findFirst(Class<E> table) {
        return null;
    }

    public E findFirst(Class<E> table, String where) {
        return null;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key!"));
    }
}
