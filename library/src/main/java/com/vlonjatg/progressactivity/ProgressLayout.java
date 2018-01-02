package com.vlonjatg.progressactivity;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.List;

public interface ProgressLayout {

    public void showContent();

    public void showContent(List<Integer> idsOfViewsNotToShow);

    public void showLoading();

    public void showLoading(List<Integer> idsOfViewsNotToHide);

    public void showEmpty(int icon, String title, String description);

    public void showEmpty(Drawable icon, String title, String description);

    public void showEmpty(int icon, String title, String description, List<Integer> idsOfViewsNotToHide);

    public void showEmpty(Drawable icon, String title, String description, List<Integer> idsOfViewsNotToHide);

    public void showError(int icon, String title, String description, String buttonText, View.OnClickListener buttonClickListener);

    public void showError(Drawable icon, String title, String description, String buttonText, View.OnClickListener buttonClickListener);

    public void showError(int icon, String title, String description, String buttonText, View.OnClickListener buttonClickListener, List<Integer> idsOfViewsNotToHide);

    public void showError(Drawable icon, String title, String description, String buttonText, View.OnClickListener buttonClickListener, List<Integer> idsOfViewsNotToHide);

    public String getCurrentState();

    public boolean isContentCurrentState();

    public boolean isLoadingCurrentState();

    public boolean isEmptyCurrentState();

    public boolean isErrorCurrentState();
}
