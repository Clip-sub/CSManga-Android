package com.clipsub.csmanga.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Special ImageView class that reacts to user's gesture.
 */
public class GestureImageView extends ImageView {

    public static final String VIEW_TYPE_FIT_CENTRE = "Fit Centre";
    public static final String VIEW_TYPE_FIT_WIDTH = "Fit Width";
    public static final String VIEW_TYPE_FIT_HEIGHT = "Fit Height";

    public static final float MIN_SCALE = 1.00f;
    public static final float MAX_SCALE = 3.50f;

    private static final float ZOOM_DURATION = 200f;
    private static final long RUNNABLE_DELAY_MS = 1000 / 60;

    private String viewType;
    private Matrix baseMatrix;
    private Matrix supplementaryMatrix = new Matrix();
    private float[] matrixValues = new float[9];

    private int maximumAcceleratedWidth;
    private int maximumAcceleratedHeight;
    private float bitmapWidth;
    private float bitmapHeight;

    private FlingRunnable flingRunnable;
    private ZoomRunnable zoomRunnable;

    private ScaleGestureDetector scaleGestureDetector;

    private boolean initialized;

    public GestureImageView(Context context) {
        super(context);

        initialize();
    }

    public GestureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public GestureImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas.isHardwareAccelerated()) {
            maximumAcceleratedWidth = canvas.getMaximumBitmapWidth();
            maximumAcceleratedHeight = canvas.getMaximumBitmapHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);

        return true;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);

        if (bm != null) {
            bitmapWidth = bm.getWidth();
            bitmapHeight = bm.getHeight();
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);

        if (drawable != null) {
            bitmapWidth = drawable.getIntrinsicWidth();
            bitmapHeight = drawable.getIntrinsicHeight();
        }
    }

    /**
     * Initialize necessary components, such as getting view type from PreferenceUtils class.
     */
    private void initialize() {
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ImageViewScaleGestureListener());
        viewType = PreferenceUtils.getViewType();
    }

    /**
     * Initialize the whole view. As well as the matrix containing the image itself.
     */
    public void initializeView() {
        if (!initialized) {
            setScaleType(ScaleType.MATRIX);

            initializeBaseMatrix();
            setImageMatrix(getImageMatrix());

            initialized = true;
        }
    }

    private void initializeBaseMatrix() {
        // Reset the matrix to identity matrix.
        baseMatrix.reset();

        float widthScale = Math.min(getWidth() / bitmapWidth, 2.00f);
        float heightScale = Math.min(getHeight() / bitmapHeight, 2.00f);

        float actualScale = Math.min(widthScale, heightScale);
        if (viewType.equals(VIEW_TYPE_FIT_CENTRE)) {
            actualScale = Math.min(widthScale, heightScale);
        } else if (viewType.equals(VIEW_TYPE_FIT_WIDTH)) {
            actualScale = widthScale;
        } else if (viewType.equals(VIEW_TYPE_FIT_HEIGHT)) {
            actualScale = heightScale;
        }
    }
}
