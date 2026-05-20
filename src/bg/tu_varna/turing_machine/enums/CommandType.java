package bg.tu_varna.turing_machine.enums;

/**
 * Lists every command keyword recognised by the application.
 * Each constant holds the lowercase string the user must type.
 * The fromString method does a case-insensitive search and returns
 * the matching constant, or null if nothing matches.
 */
public enum CommandType {
    OPEN("open"),
    CLOSE("close"),
    SAVE("save"),
    SAVEAS("saveas"),
    HELP("help"),
    EXIT("exit"),
    LIST("list"),
    PRINT("print"),
    SAVETM("savetm"),
    LOADTM("loadtm"),
    NEWTM("newtm"),
    ADDSTATE("addstate"),
    SETSTART("setstart"),
    ADDACCEPT("addaccept"),
    ADDREJECT("addreject"),
    ADDTRANS("addtrans"),
    REMOVETRANS("removetrans"),
    CHECKDET("checkdet"),
    INIT("init"),
    STEP("step"),
    RUN("run"),
    STATUS("status"),
    TAPE("tape"),
    RESET("reset"),
    ACCEPTS("accepts"),
    TRACE("trace"),
    REPORT("report");

    private String key;

    CommandType(String key) {

        this.key = key;
    }

    /** Returns the lowercase keyword string the user must type to invoke this command. */
    public String getKey() {

        return key;
    }

    /** Finds a CommandType by its keyword string, ignoring case. Returns null if not found. */
    public static CommandType fromString(String key) {
        if (key == null){
            return null;
        }
        String lower = key.toLowerCase();
        for (CommandType type : values()) {
            if (type.key.equals(lower)){
                return type;
            }
        }
        return null;
    }
}
