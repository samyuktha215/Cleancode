

public class SimpleWindowUI implements IText {
    private SimpleWindow gw;

    public SimpleWindowUI() {
       gw=new SimpleWindow("MOO");

    }
    @Override
    public void addString(String message) {
        gw.addString(message);

    }

    @Override
    public String getString() {
        return gw.getString();

    }

    @Override
    public boolean YesNo(String message) {
        return gw.yesNo(message);
    }

    @Override
    public void clear() {
        gw.clear();
    }

    @Override
    public void exit() {
        gw.exit();
    }
}
