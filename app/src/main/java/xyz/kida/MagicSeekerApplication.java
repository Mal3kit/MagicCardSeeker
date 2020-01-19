package xyz.kida;

import android.app.Application;
import xyz.kida.magiccardseeker.data.di.FakeDI;

public class MagicSeekerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FakeDI.setAppContext(this);
    }
}
