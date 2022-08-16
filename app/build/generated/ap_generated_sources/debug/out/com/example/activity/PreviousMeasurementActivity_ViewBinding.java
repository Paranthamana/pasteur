// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.R;
import com.mlsdev.animatedrv.AnimatedRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PreviousMeasurementActivity_ViewBinding implements Unbinder {
  private PreviousMeasurementActivity target;

  @UiThread
  public PreviousMeasurementActivity_ViewBinding(PreviousMeasurementActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PreviousMeasurementActivity_ViewBinding(PreviousMeasurementActivity target, View source) {
    this.target = target;

    target.ivPreviousBack = Utils.findRequiredViewAsType(source, R.id.ivPreviousBack, "field 'ivPreviousBack'", ImageView.class);
    target.tvMeasurement = Utils.findRequiredViewAsType(source, R.id.tvMeasurement, "field 'tvMeasurement'", TextView.class);
    target.ivDotView = Utils.findRequiredViewAsType(source, R.id.ivDotView, "field 'ivDotView'", ImageView.class);
    target.llPreviousToolbar = Utils.findRequiredViewAsType(source, R.id.llPreviousToolbar, "field 'llPreviousToolbar'", LinearLayout.class);
    target.rvPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.rvPreviousMeasurement, "field 'rvPreviousMeasurement'", AnimatedRecyclerView.class);
    target.tvHelp = Utils.findRequiredViewAsType(source, R.id.tvHelp, "field 'tvHelp'", TextView.class);
    target.llHelp = Utils.findRequiredViewAsType(source, R.id.llHelp, "field 'llHelp'", LinearLayout.class);
    target.ivSupport = Utils.findRequiredViewAsType(source, R.id.ivSupport, "field 'ivSupport'", ImageView.class);
    target.tvCustomerSupport = Utils.findRequiredViewAsType(source, R.id.tvCustomerSupport, "field 'tvCustomerSupport'", TextView.class);
    target.llListBottomBar = Utils.findRequiredViewAsType(source, R.id.llListBottomBar, "field 'llListBottomBar'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PreviousMeasurementActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPreviousBack = null;
    target.tvMeasurement = null;
    target.ivDotView = null;
    target.llPreviousToolbar = null;
    target.rvPreviousMeasurement = null;
    target.tvHelp = null;
    target.llHelp = null;
    target.ivSupport = null;
    target.tvCustomerSupport = null;
    target.llListBottomBar = null;
  }
}
