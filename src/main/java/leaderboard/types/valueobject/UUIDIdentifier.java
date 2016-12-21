package leaderboard.types.valueobject;

import java.util.UUID;

public class UUIDIdentifier extends Identifier {
    private final static String CLASS_NAME = UUIDIdentifier.class.getSimpleName();

    public UUIDIdentifier(String id) {
        super(id);

        guard(id);
    }

    private void guard(String id) {
        if (!isValid(id)) {
            throw new IllegalArgumentException(
                    String.format("[%s] does not allow a non-UUID [%s]", CLASS_NAME, id));
        }
    }

    private boolean isValid(String id) {
        try{
            UUID.fromString(id);
        } catch (IllegalArgumentException exception){
            return false;
        }

        return true;
    }
}
