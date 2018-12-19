package com.sixfingers.bp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.sixfingers.bp.player.BackHomeControlPowerBookDecorator;
import com.sixfingers.bp.player.PlayPauseControlPowerBookDecorator;
import com.sixfingers.bp.player.PortraitControlPowerBookDecorator;
import com.sixfingers.bp.player.PowerEbookDecorator;
import com.sixfingers.bp.player.TestImagePowerEbook;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PowerEbookDecorator view = new PlayPauseControlPowerBookDecorator(
//                new BackHomeControlPowerBookDecorator(
        PowerEbookDecorator view = new PlayPauseControlPowerBookDecorator(new PortraitControlPowerBookDecorator(new BackHomeControlPowerBookDecorator(
                new TestImagePowerEbook(this, R.drawable.test))));
        addContentView(view.frameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
