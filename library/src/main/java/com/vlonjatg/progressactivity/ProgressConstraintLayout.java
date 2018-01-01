package com.vlonjatg.progressactivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

public class ProgressConstraintLayout extends FrameLayout {

    private static final String TAG_LOADING = "ProgressActivity.TAG_LOADING";
    private static final String TAG_EMPTY = "ProgressActivity.TAG_EMPTY";
    private static final String TAG_ERROR = "ProgressActivity.TAG_ERROR";

    final String CONTENT = "type_content";
    final String LOADING = "type_loading";
    final String EMPTY = "type_empty";
    final String ERROR = "type_error";

    LayoutInflater inflater;
    View view;
    LayoutParams layoutParams;
    Drawable defaultBackground;

    List<View> contentViews = new ArrayList<>();

    ConstraintLayout loadingStateConstraintLayout;
    ProgressBar loadingStateProgressBar;

    ConstraintLayout emptyStateConstraintLayout;
    ImageView emptyStateImageView;
    TextView emptyStateTitleTextView;
    TextView emptyStateContentTextView;

    ConstraintLayout errorStateConstraintLayout;
    ImageView errorStateImageView;
    TextView errorStateTitleTextView;
    TextView errorStateContentTextView;
    Button errorStateButton;

    int loadingStateProgressBarWidth;
    int loadingStateProgressBarHeight;
    int loadingStateProgressBarColor;
    int loadingStateBackgroundColor;

    int emptyStateImageWidth;
    int emptyStateImageHeight;
    int emptyStateTitleTextSize;
    int emptyStateContentTextSize;
    int emptyStateTitleTextColor;
    int emptyStateContentTextColor;
    int emptyStateBackgroundColor;

    int errorStateImageWidth;
    int errorStateImageHeight;
    int errorStateTitleTextSize;
    int errorStateContentTextSize;
    int errorStateTitleTextColor;
    int errorStateContentTextColor;
    int errorStateButtonTextColor;
    int errorStateButtonBackgroundColor;
    int errorStateBackgroundColor;

    private String state = CONTENT;

    public ProgressConstraintLayout(Context context) {
        super(context);
    }

    public ProgressConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProgressConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressActivity);

        //Loading state attrs
        loadingStateProgressBarWidth =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_loadingProgressBarWidth, 108);

        loadingStateProgressBarHeight =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_loadingProgressBarHeight, 108);

        loadingStateProgressBarColor =
                typedArray.getColor(R.styleable.ProgressActivity_loadingProgressBarColor, Color.RED);

        loadingStateBackgroundColor =
                typedArray.getColor(R.styleable.ProgressActivity_loadingBackgroundColor, Color.TRANSPARENT);

        //Empty state attrs
        emptyStateImageWidth =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_emptyImageWidth, 308);

        emptyStateImageHeight =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_emptyImageHeight, 308);

        emptyStateTitleTextSize =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_emptyTitleTextSize, 15);

        emptyStateContentTextSize =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_emptyContentTextSize, 14);

        emptyStateTitleTextColor =
                typedArray.getColor(R.styleable.ProgressActivity_emptyTitleTextColor, Color.BLACK);

        emptyStateContentTextColor =
                typedArray.getColor(R.styleable.ProgressActivity_emptyContentTextColor, Color.BLACK);

        emptyStateBackgroundColor =
                typedArray.getColor(R.styleable.ProgressActivity_emptyBackgroundColor, Color.TRANSPARENT);

        //Error state attrs
        errorStateImageWidth =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_errorImageWidth, 308);

        errorStateImageHeight =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_errorImageHeight, 308);

        errorStateTitleTextSize =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_errorTitleTextSize, 15);

        errorStateContentTextSize =
                typedArray.getDimensionPixelSize(R.styleable.ProgressActivity_errorContentTextSize, 14);

        errorStateTitleTextColor =
                typedArray.getColor(R.styleable.ProgressActivity_errorTitleTextColor, Color.BLACK);

        errorStateContentTextColor =
                typedArray.getColor(R.styleable.ProgressActivity_errorContentTextColor, Color.BLACK);

        errorStateButtonTextColor =
                typedArray.getColor(R.styleable.ProgressActivity_errorButtonTextColor, Color.BLACK);

        errorStateButtonBackgroundColor =
                typedArray.getColor(R.styleable.ProgressActivity_errorButtonBackgroundColor, Color.WHITE);

        errorStateBackgroundColor =
                typedArray.getColor(R.styleable.ProgressActivity_errorBackgroundColor, Color.TRANSPARENT);

        typedArray.recycle();

        defaultBackground = this.getBackground();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || (!child.getTag().equals(TAG_LOADING) &&
                !child.getTag().equals(TAG_EMPTY) && !child.getTag().equals(TAG_ERROR))) {

            contentViews.add(child);
        }
    }

    /**
     * Hide all other states and show content
     */
    public void showContent() {
        switchState(CONTENT, 0, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Hide all other states and show content
     *
     * @param skipIds Ids of views not to show
     */
    public void showContent(List<Integer> skipIds) {
        switchState(CONTENT, 0, null, null, null, null, skipIds);
    }

    /**
     * Hide content and show the progress bar
     */
    public void showLoading() {
        switchState(LOADING, 0, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Hide content and show the progress bar
     *
     * @param skipIds Ids of views to not hide
     */
    public void showLoading(List<Integer> skipIds) {
        switchState(LOADING, 0, null, null, null, null, skipIds);
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     */
    public void showEmpty(int emptyImageDrawable, String emptyTextTitle, String emptyTextContent) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     */
    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     * @param skipIds            Ids of views to not hide
     */
    public void showEmpty(int emptyImageDrawable, String emptyTextTitle, String emptyTextContent, List<Integer> skipIds) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, skipIds);
    }

    /**
     * Show empty view when there are not data to show
     *
     * @param emptyImageDrawable Drawable to show
     * @param emptyTextTitle     Title of the empty view to show
     * @param emptyTextContent   Content of the empty view to show
     * @param skipIds            Ids of views to not hide
     */
    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent, List<Integer> skipIds) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, skipIds);
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     */
    public void showError(int errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, Collections.<Integer>emptyList());
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     */
    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, Collections.<Integer>emptyList());
    }


    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     * @param skipIds            Ids of views to not hide
     */
    public void showError(int errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds);
    }

    /**
     * Show error view with a button when something goes wrong and prompting the user to try again
     *
     * @param errorImageDrawable Drawable to show
     * @param errorTextTitle     Title of the error view to show
     * @param errorTextContent   Content of the error view to show
     * @param errorButtonText    Text on the error view button to show
     * @param onClickListener    Listener of the error view button
     * @param skipIds            Ids of views to not hide
     */
    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds);
    }

    /**
     * Get which state is set
     *
     * @return State
     */
    public String getState() {
        return state;
    }

    /**
     * Check if content is shown
     *
     * @return boolean
     */
    public boolean isContent() {
        return state.equals(CONTENT);
    }

    /**
     * Check if loading state is shown
     *
     * @return boolean
     */
    public boolean isLoading() {
        return state.equals(LOADING);
    }

    /**
     * Check if empty state is shown
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return state.equals(EMPTY);
    }

    /**
     * Check if error state is shown
     *
     * @return boolean
     */
    public boolean isError() {
        return state.equals(ERROR);
    }

    private void switchState(String state, Drawable drawable, String errorText, String errorTextContent,
                             String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        this.state = state;

        hideAllStates();

        switch (state) {
            case CONTENT:
                //Hide all state views to display content
                setContentVisibility(true, skipIds);
                break;
            case LOADING:
                setLoadingView();
                setContentVisibility(false, skipIds);
                break;
            case EMPTY:
                setEmptyView();
                setContentVisibility(false, skipIds);

                emptyStateImageView.setImageDrawable(drawable);
                emptyStateTitleTextView.setText(errorText);
                emptyStateContentTextView.setText(errorTextContent);
                break;
            case ERROR:
                setErrorView();
                setContentVisibility(false, skipIds);

                errorStateImageView.setImageDrawable(drawable);
                errorStateTitleTextView.setText(errorText);
                errorStateContentTextView.setText(errorTextContent);
                errorStateButton.setText(errorButtonText);
                errorStateButton.setOnClickListener(onClickListener);
                break;
        }
    }

    private void switchState(String state, int drawable, String errorText, String errorTextContent,
                             String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        this.state = state;

        hideAllStates();

        switch (state) {
            case CONTENT:
                //Hide all state views to display content
                setContentVisibility(true, skipIds);
                break;
            case LOADING:
                setLoadingView();
                setContentVisibility(false, skipIds);
                break;
            case EMPTY:
                setEmptyView();
                setContentVisibility(false, skipIds);

                emptyStateImageView.setImageResource(drawable);
                emptyStateTitleTextView.setText(errorText);
                emptyStateContentTextView.setText(errorTextContent);
                break;
            case ERROR:
                setErrorView();
                setContentVisibility(false, skipIds);

                errorStateImageView.setImageResource(drawable);
                errorStateTitleTextView.setText(errorText);
                errorStateContentTextView.setText(errorTextContent);
                errorStateButton.setText(errorButtonText);
                errorStateButton.setOnClickListener(onClickListener);
                break;
        }
    }

    private void hideAllStates() {
        hideLoadingView();
        hideEmptyView();
        hideErrorView();
    }

    private void setLoadingView() {
        if (loadingStateConstraintLayout == null) {
            view = inflater.inflate(R.layout.progress_frame_layout_loading_view, null);
            loadingStateConstraintLayout = view.findViewById(R.id.constraint_layout_loading);
            loadingStateConstraintLayout.setTag(TAG_LOADING);

            // Setup ProgressBar
            loadingStateProgressBar = view.findViewById(R.id.progress_bar_loading);
            loadingStateProgressBar.getLayoutParams().width = loadingStateProgressBarWidth;
            loadingStateProgressBar.getLayoutParams().height = loadingStateProgressBarHeight;
            loadingStateProgressBar.getIndeterminateDrawable()
                    .setColorFilter(loadingStateProgressBarColor, PorterDuff.Mode.SRC_IN);
            loadingStateProgressBar.requestLayout();

            //Set background color if not TRANSPARENT
            if (loadingStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(loadingStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = CENTER_IN_PARENT;

            addView(loadingStateConstraintLayout, layoutParams);
        } else {
            loadingStateConstraintLayout.setVisibility(VISIBLE);
        }
    }

    private void setEmptyView() {
        if (emptyStateConstraintLayout == null) {
            view = inflater.inflate(R.layout.progress_frame_layout_empty_view, null);
            emptyStateConstraintLayout = view.findViewById(R.id.constraint_layout_empty);
            emptyStateConstraintLayout.setTag(TAG_EMPTY);

            emptyStateImageView = view.findViewById(R.id.image_icon);
            emptyStateTitleTextView = view.findViewById(R.id.text_title);
            emptyStateContentTextView = view.findViewById(R.id.text_content);

            //Set empty state image width and height
            emptyStateImageView.getLayoutParams().width = emptyStateImageWidth;
            emptyStateImageView.getLayoutParams().height = emptyStateImageHeight;
            emptyStateImageView.requestLayout();

            emptyStateTitleTextView.setTextSize(emptyStateTitleTextSize);
            emptyStateContentTextView.setTextSize(emptyStateContentTextSize);
            emptyStateTitleTextView.setTextColor(emptyStateTitleTextColor);
            emptyStateContentTextView.setTextColor(emptyStateContentTextColor);

            //Set background color if not TRANSPARENT
            if (emptyStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(emptyStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = CENTER_IN_PARENT;

            addView(emptyStateConstraintLayout, layoutParams);
        } else {
            emptyStateConstraintLayout.setVisibility(VISIBLE);
        }
    }

    private void setErrorView() {
        if (errorStateConstraintLayout == null) {
            view = inflater.inflate(R.layout.progress_frame_layout_error_view, null);
            errorStateConstraintLayout = view.findViewById(R.id.constraint_layout_error);
            errorStateConstraintLayout.setTag(TAG_ERROR);

            errorStateImageView = view.findViewById(R.id.image_icon);
            errorStateTitleTextView = view.findViewById(R.id.text_title);
            errorStateContentTextView = view.findViewById(R.id.text_content);
            errorStateButton = view.findViewById(R.id.button_retry);

            //Set error state image width and height
            errorStateImageView.getLayoutParams().width = errorStateImageWidth;
            errorStateImageView.getLayoutParams().height = errorStateImageHeight;
            errorStateImageView.requestLayout();

            errorStateTitleTextView.setTextSize(errorStateTitleTextSize);
            errorStateContentTextView.setTextSize(errorStateContentTextSize);
            errorStateTitleTextView.setTextColor(errorStateTitleTextColor);
            errorStateContentTextView.setTextColor(errorStateContentTextColor);
            errorStateButton.setTextColor(errorStateButtonTextColor);
            errorStateButton.getBackground().setColorFilter(new LightingColorFilter(1, errorStateButtonBackgroundColor));

            //Set background color if not TRANSPARENT
            if (errorStateBackgroundColor != Color.TRANSPARENT) {
                this.setBackgroundColor(errorStateBackgroundColor);
            }

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = CENTER_IN_PARENT;

            addView(errorStateConstraintLayout, layoutParams);
        } else {
            errorStateConstraintLayout.setVisibility(VISIBLE);
        }
    }

    /**
     * Helpers
     */

    private void setContentVisibility(boolean visible, List<Integer> skipIds) {
        for (View v : contentViews) {
            if (!skipIds.contains(v.getId())) {
                v.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }
    }

    private void hideLoadingView() {
        if (loadingStateConstraintLayout != null) {
            loadingStateConstraintLayout.setVisibility(GONE);
            restoreDefaultBackground();
        }
    }

    private void hideEmptyView() {
        if (emptyStateConstraintLayout != null) {
            emptyStateConstraintLayout.setVisibility(GONE);
            restoreDefaultBackground();
        }
    }

    private void hideErrorView() {
        if (errorStateConstraintLayout != null) {
            errorStateConstraintLayout.setVisibility(GONE);
            restoreDefaultBackground();
        }
    }

    private void restoreDefaultBackground() {
        this.setBackgroundDrawable(defaultBackground);
    }
}
