package com.sixfingers.bp.testcallbak;

import com.sixfingers.bp.callback.Action;

public class SampleAction {

    public enum FlipPageAction {
        A, B;
    }

    public static class TestAction implements Action<FlipPageAction> {
        @Override
        public void act(FlipPageAction flipPageAction, Object... args) {
            switch (flipPageAction) {
                case A:
                    return;
                case B:
                    return;
            }
        }
    }

}
