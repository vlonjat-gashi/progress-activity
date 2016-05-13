package com.vlonjatg.sample.progressactivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.MenuItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DetailsActivity.class})
public class DetailsActivityOnOptionsItemSelectedTest {

    private DetailsActivity detailsActivity;
    private MenuItem menuItem;

    @Before
    public void setUp() throws Exception {
        detailsActivity = spy(new DetailsActivity());
        menuItem = mock(MenuItem.class);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Test
    public void testOnOptionsItemSelectedHome() {
        // given
        doReturn(android.R.id.home).when(menuItem).getItemId();

        // when
        boolean result = detailsActivity.onOptionsItemSelected(menuItem);

        // then
        assertTrue(result);
    }

    @Test
    public void testOnOptionsItemSelectedDefault() {
        // given
        doReturn(android.R.id.background).when(menuItem).getItemId();

        // when
        boolean result = detailsActivity.onOptionsItemSelected(menuItem);

        // then
        assertFalse(result);
    }
}