package LookupMethodInjection;

/**
 * Created by Vladimir on 03.02.2017.
 */
public abstract class AbstractLookupDemoBean implements DemoBeanInterface{
    public abstract MyHelper getMyHelper();

    @Override
    public void someOperation() {
        getMyHelper().doSmthUseful();
    }
}
