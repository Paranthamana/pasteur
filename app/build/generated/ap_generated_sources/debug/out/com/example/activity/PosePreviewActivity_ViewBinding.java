// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.R;
import com.tmall.ultraviewpager.UltraViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PosePreviewActivity_ViewBinding implements Unbinder {
  private PosePreviewActivity target;

  @UiThread
  public PosePreviewActivity_ViewBinding(PosePreviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PosePreviewActivity_ViewBinding(PosePreviewActivity target, View source) {
    this.target = target;

    target.llPosePreviewConfirm = Utils.findRequiredViewAsType(source, R.id.llPosePreviewConfirm, "field 'llPosePreviewConfirm'", LinearLayout.class);
    target.ivRetake = Utils.findRequiredViewAsType(source, R.id.ivRetake, "field 'ivRetake'", ImageView.class);
    target.tbMeasurementPose = Utils.findRequiredViewAsType(source, R.id.tbMeasurementPose, "field 'tbMeasurementPose'", Toolbar.class);
    target.tvPose = Utils.findRequiredViewAsType(source, R.id.tvPose, "field 'tvPose'", TextView.class);
    target.cvTitle = Utils.findRequiredViewAsType(source, R.id.cvTitle, "field 'cvTitle'", CardView.class);
    target.upPosePreview = Utils.findRequiredViewAsType(source, R.id.upPosePreview, "field 'upPosePreview'", UltraViewPager.class);
    target.cvUltraView = Utils.findRequiredViewAsType(source, R.id.cvUltraView, "field 'cvUltraView'", CardView.class);
    target.ivIndicator1 = Utils.findRequiredViewAsType(source, R.id.ivIndicator1, "field 'ivIndicator1'", ImageView.class);
    target.ivPmBack = Utils.findRequiredViewAsType(source, R.id.ivPmBack, "field 'ivPmBack'", ImageView.class);
    target.ivIndicator2 = Utils.findRequiredViewAsType(source, R.id.ivIndicator2, "field 'ivIndicator2'", ImageView.class);
    target.ivIndicator3 = Utils.findRequiredViewAsType(source, R.id.ivIndicator3, "field 'ivIndicator3'", ImageView.class);
    target.ivIndicator4 = Utils.findRequiredViewAsType(source, R.id.ivIndicator4, "field 'ivIndicator4'", ImageView.class);
    target.ivGallery = Utils.findRequiredViewAsType(source, R.id.ivGallery, "field 'ivGallery'", ImageView.class);
    target.tvPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.tvPreviousMeasurement, "field 'tvPreviousMeasurement'", TextView.class);
    target.llPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.llPreviousMeasurement, "field 'llPreviousMeasurement'", LinearLayout.class);
    target.tvHelp = Utils.findRequiredViewAsType(source, R.id.tvHelp, "field 'tvHelp'", TextView.class);
    target.llHelp = Utils.findRequiredViewAsType(source, R.id.llHelp, "field 'llHelp'", LinearLayout.class);
    target.tvCustomerSupport = Utils.findRequiredViewAsType(source, R.id.tvCustomerSupport, "field 'tvCustomerSupport'", TextView.class);
    target.llBottomBar = Utils.findRequiredViewAsType(source, R.id.llBottomBar, "field 'llBottomBar'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PosePreviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llPosePreviewConfirm = null;
    target.ivRetake = null;
    target.tbMeasurementPose = null;
    target.tvPose = null;
    target.cvTitle = null;
    target.upPosePreview = null;
    target.cvUltraView = null;
    target.ivIndicator1 = null;
    target.ivPmBack = null;
    target.ivIndicator2 = null;
    target.ivIndicator3 = null;
    target.ivIndicator4 = null;
    target.ivGallery = null;
    target.tvPreviousMeasurement = null;
    target.llPreviousMeasurement = null;
    target.tvHelp = null;
    target.llHelp = null;
    target.tvCustomerSupport = null;
    target.llBottomBar = null;
  }
}
