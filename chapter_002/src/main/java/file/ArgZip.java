package file;

public class ArgZip {

    private final ArgsName args;

    public ArgZip(String[] args) {
        this.args = ArgsName.of(args);
    }

    public boolean valid() {
        return !(directory().isEmpty() || exclude().isEmpty() || output().isEmpty());
    }

    public String directory() {
        return args.get("d");
    }

    public String exclude() {
        return args.get("e");
    }

    public String output() {
        return args.get("o");
    }
}
