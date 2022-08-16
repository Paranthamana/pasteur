// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasurementDetailsActivity_ViewBinding implements Unbinder {
  private MeasurementDetailsActivity target;

  @UiThread
  public MeasurementDetailsActivity_ViewBinding(MeasurementDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeasurementDetailsActivity_ViewBinding(MeasurementDetailsActivity target, View source) {
    this.target = target;

    target.ivAccuracyBack = Utils.findRequiredViewAsType(source, R.id.ivAccuracyBack, "field 'ivAccuracyBack'", ImageView.class);
    target.tvAccuracyToolbarMeasurement = Utils.findRequiredViewAsType(source, R.id.tvAccuracyToolbarMeasurement, "field 'tvAccuracyToolbarMeasurement'", TextView.class);
    target.ivAccuracyDotView = Utils.findRequiredViewAsType(source, R.id.ivAccuracyDotView, "field 'ivAccuracyDotView'", ImageView.class);
    target.DetailsToolbar = Utils.findRequiredViewAsType(source, R.id.DetailsToolbar, "field 'DetailsToolbar'", Toolbar.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.vpMeasurementDetails = Utils.findRequiredViewAsType(source, R.id.vpMeasurementDetails, "field 'vpMeasurementDetails'", ViewPager.class);
    target.ivDetailGallery = Utils.findRequiredViewAsType(source, R.id.ivDetailGallery, "field 'ivDetailGallery'", ImageView.class);
    target.tvDetailPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.tvDetailPreviousMeasurement, "field 'tvDetailPreviousMeasurement'", TextView.class);
    target.llDetailPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.llDetailPreviousMeasurement, "field 'llDetailPreviousMeasurement'", LinearLayout.class);
    target.tvDetailHelp = Utils.findRequiredViewAsType(source, R.id.tvDetailHelp, "field 'tvDetailHelp'", TextView.class);
    target.llDetailHelp = Utils.findRequiredViewAsType(source, R.id.llDetailHelp, "field 'llDetailHelp'", LinearLayout.class);
    target.tvDetailCustomerSupport = Utils.findRequiredViewAsType(source, R.id.tvDetailCustomerSupport, "field 'tvDetailCustomerSupport'", TextView.class);
    target.llDetailCustomerSupport = Utils.findRequiredViewAsType(source, R.id.llDetailCustomerSupport, "field 'llDetailCustomerSupport'", LinearLayout.class);
    target.llDetailMeasurement = Utils.findRequiredViewAsType(source, R.id.llDetailMeasurement, "field 'llDetailMeasurement'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasurementDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivAccuracyBack = null;
    target.tvAccuracyToolbarMeasurement = null;
    target.ivAccuracyDotView = null;
    target.DetailsToolbar = null;
    target.tabLayout = null;
    target.vpMeasurementDetails = null;
    target.ivDetailGallery = null;
    target.tvDetailPreviousMeasurement = null;
    target.llDetailPreviousMeasurement = null;
    target.tvDetailHelp = null;
    target.llDetailHelp = null;
    target.tvDetailCustomerSupport = null;
    target.llDetailCustomerSupport = null;
    target.llDetailMeasurement = null;
  }
}
