package orm.models;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;
import orm.interfaces.DbContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.stream.IntStream;

public class EntityManager<E> implements DbContext<E> {
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s";
    private static final String SELECT_ALL_WITH_WHERE = "SELECT * FROM %s WHERE %s";
    private static final String DELETE_FROM_TABLE_WITH_WHERE = "DELETE FROM %s WHERE %s";

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int) value <= 0) {
            return this.doInsert(entity);
        }

        return this.doUpdate(entity, primary);
    }

    public Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return this.find(table, null);
    }

    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = table.getAnnotation(Entity.class).name();
//        String tableName = table.getSimpleName().toLowerCase().concat("s"); // w/o using annotations
        String query = String.format(SELECT_ALL_WITH_WHERE,
                tableName,
                where != null ? where : "1 = 1");

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            E current = this.mapResultToEntity(resultSet, table);
            result.add(current);
        }

        return result;
    }

    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table, null);
    }

    public E findFirst(Class<E> table, String where) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return this.find(table, where != null ? where : "1 = 1" + " LIMIT 1").iterator().next();
    }

    @Override
    public boolean delete(Class<E> table) throws SQLException {
        return this.delete(table, null);
    }

    @Override
    public boolean delete(Class<E> table, String where) throws SQLException {
        String tableName = table.getAnnotation(Entity.class).name();

        String query = String.format(DELETE_FROM_TABLE_WITH_WHERE,
                tableName,
                where);

        return connection.prepareStatement(query).execute();
    }

    private E mapResultToEntity(ResultSet resultSet, Class<E> table) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        E entity = table.getDeclaredConstructor().newInstance();

        Arrays.stream(table.getDeclaredFields())
                .forEach(f -> {
                    f.setAccessible(true);
                    String name = f.getAnnotation(Column.class).name();
//                    String name = getNormalizedName(f.getName()); // w/o using annotations
                    Object value = null;

                    try {
                        value = this.getFieldValueFromResultSet(resultSet, name, f.getType());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        f.set(entity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });


//        IF WE WANT TO INSTANTIATE THE ENTITY WITH PARAMETERS AND DELETE THE EMPTY CONSTRUCTOR
//        Field[] field = new Field[1];
//
//        Object[] args = Arrays.stream(table.getDeclaredFields())
//                .filter(f -> {
//                    if (f.isAnnotationPresent(Id.class)) {
//                        field[0] = f;
//                    }
//                    return !f.isAnnotationPresent(Id.class);
//                })
//                .map(f -> {
//                    f.setAccessible(true);
//                    String name = getNormalizedName(f.getName());
//                    Object value = null;
//
//                    try {
//                        value = this.getFieldValueFromResultSet(resultSet, name, f.getType());
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    return value;
//                })
//                .toArray();
//
//        Class[] arguments = new Class[args.length];
//        for (int i = 0; i < arguments.length; i++) {
//            arguments[i] = args[i].getClass();
//        }
//
//        Constructor<E> constructor = table.getDeclaredConstructor(arguments);
//        E entity = constructor.newInstance(args);
//
//        if (field[0] != null) {
//            Field primary = field[0];
//            primary.setAccessible(true);
//            Object value = this.getFieldValueFromResultSet(resultSet, primary.getName(), primary.getType());
//            primary.set(entity, value);
//        }

        return entity;
    }

    private Object getFieldValueFromResultSet(ResultSet resultSet, String name, Class<?> type) throws SQLException {
        Object result = null;

        if (type == int.class || type == Integer.class) {
            result = resultSet.getInt(name);
        } else if (type == double.class || type == Double.class) {
            result = resultSet.getDouble(name);
        } else if (type == long.class || type == Long.class) {
            result = resultSet.getLong(name);
        } else if (type == boolean.class || type == Boolean.class) {
            result = resultSet.getBoolean(name);
        } else if (type == Date.class) {
            result = resultSet.getDate(name);
        } else if (type == String.class) {
            result = resultSet.getString(name);
        }

        return result;
    }

    private boolean doInsert(E entity) throws SQLException {
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
        String tableName = entity.getClass().getAnnotation(Entity.class).name();
//        String tableName = entity.getClass().getSimpleName().toLowerCase().concat("s"); // w/o using annotations

        String[] tableFields = getTableFields(entity);
        String[] tableValues = getTableValues(entity);

        String[] tableFieldValuePairs = IntStream.range(0, tableFields.length)
                .mapToObj(i -> "`" + tableFields[i] + "`=" + tableValues[i])
                .toArray(String[]::new);

//        same as the above - IntStream.range
//        String[] tableFieldValuePairs = new String[tableFields.length];
//        for (int i = 0; i < tableFields.length; i++) {
//            tableFieldValuePairs[i] = "`" + tableFields[i] + "`=" + tableValues[i];
//        }

        String whereId = "`" + primary.getName() + "`='" + primary.get(entity) + "'";

        String query = String.format(UPDATE_QUERY,
                tableName,
                String.join(", ", tableFieldValuePairs),
                whereId);

        return this.connection.prepareStatement(query).execute();
    }

    private String[] getTableFields(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .map(f -> {
                    f.setAccessible(true);
                    return f.getAnnotation(Column.class).name();
//                    return getNormalizedName(f.getName()); // w/o using annotations
                })
                .toArray(String[]::new);
    }

    private String[] getTableValues(E entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
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

//    To use if no annotations
//    private String getNormalizedName(String name) {
//        name = name.replaceAll("([A-Z])", "_$1").toLowerCase();
//        name = name.replaceAll("([0-9])", "_$1");
//        return name;
//    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key!"));
    }
}
