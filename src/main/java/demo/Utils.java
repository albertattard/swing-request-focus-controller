package demo;

public final class Utils {

    public static String getClassSimpleName(final Object object) {
        return object == null
                ? "NULL"
                : object.getClass().getSimpleName();
    }

    private Utils() {}
}
