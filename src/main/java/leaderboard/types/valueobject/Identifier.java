package leaderboard.types.valueobject;

import com.google.common.base.Strings;

public class Identifier extends StringValueObject {
    private final static String CLASS_NAME = Identifier.class.getSimpleName();

    public Identifier(String id) {
        super(id);

        guard(id);
    }

    private void guard(String id) {
        if (!isValid(id)) {
            throw new IllegalArgumentException(
                    String.format("[%s] does not allow null or empty identifiers [%s]", CLASS_NAME, id));
        }
    }

    private boolean isValid(String id) {
        // give room for Integers here also
        return !Strings.isNullOrEmpty(id);
    }
}
