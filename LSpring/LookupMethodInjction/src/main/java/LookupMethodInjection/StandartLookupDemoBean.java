package LookupMethodInjection;

/**
 * Created by Vladimir on 03.02.2017.
 */
public class StandartLookupDemoBean implements DemoBeanInterface{
    private MyHelper myHelper;

    public void setMyHelper(MyHelper myHelper) {
        this.myHelper = myHelper;
    }

    @Override
    public MyHelper getMyHelper() {
        return this.myHelper;
    }

    @Override
    public void someOperation() {
        myHelper.doSmthUseful();
    }
}
