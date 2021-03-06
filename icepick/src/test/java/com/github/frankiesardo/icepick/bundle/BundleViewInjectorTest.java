package com.github.frankiesardo.icepick.bundle;

import android.os.Parcelable;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class BundleViewInjectorTest {

    Parcelable parcelable = Mockito.mock(Parcelable.class);
    BundleViewInjector bundleInjector = new BundleViewInjector(this, parcelable, new HashMap<BundleMethodKey, Method>());

    @Test
    public void shouldRestoreFieldValueWithBundleContent() throws Exception {
        bundleInjector.inject(BundleAction.RESTORE);

        verify(parcelable).describeContents();
    }

    @Test
    public void shouldSaveFieldValueToBundle() throws Exception {
        bundleInjector.inject(BundleAction.SAVE);

        verify(parcelable).describeContents();
    }
}
