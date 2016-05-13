package com.vlonjatg.sample.progressactivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;

import com.malinskiy.materialicons.IconDrawable;
import com.vlonjatg.progressactivity.ProgressActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.reflect.Whitebox.setInternalState;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DetailsActivity.class})
public class DetailsActivityOnCreateTest {

    private DetailsActivity detailsActivity;

    private Bundle bundle;
    private Intent intent;
    private ProgressActivity progressActivity;


    @Before
    public void setUp() throws Exception {
        detailsActivity = spy(new DetailsActivity());

        bundle = mock(Bundle.class);
        intent = mock(Intent.class);
        IconDrawable iconDrawable = mock(IconDrawable.class);
        LayoutInflater layoutInflater = mock(LayoutInflater.class);
        progressActivity = mock(ProgressActivity.class);
        AppCompatDelegate appCompatDelegate = mock(AppCompatDelegate.class);

        setInternalState(detailsActivity, appCompatDelegate);

        doReturn(intent).when(detailsActivity).getIntent();
        doReturn(progressActivity).when(detailsActivity).findViewById(R.id.progress);
        doReturn(layoutInflater).when(detailsActivity).getLayoutInflater();

        whenNew(IconDrawable.class).withAnyArguments().thenReturn(iconDrawable);
    }

    @Test
    public void testOnCreateStateLoading() throws Exception {
        // given
        doReturn("LOADING").when(intent).getStringExtra("STATE");

        // when
        detailsActivity.onCreate(bundle);

        // then
        verify(progressActivity).showLoading(anyList());
    }

    @Test
    public void testOnCreateStateEmpty() throws Exception {
        // given
        doReturn("EMPTY").when(intent).getStringExtra("STATE");

        // when
        detailsActivity.onCreate(bundle);

        // then
        verify(progressActivity).showEmpty((Drawable) anyObject(), anyString(), anyString(), anyList());
    }

    @Test
    public void testOnCreateStateError() throws Exception {
        // given
        doReturn("ERROR").when(intent).getStringExtra("STATE");

        // when
        detailsActivity.onCreate(bundle);

        // then
        verify(progressActivity).showError((Drawable) anyObject(), anyString(), anyString(), anyString(), (View.OnClickListener) anyObject(), anyList());
    }
}