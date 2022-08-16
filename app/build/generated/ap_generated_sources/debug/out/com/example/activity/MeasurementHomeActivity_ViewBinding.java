// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeasurementHomeActivity_ViewBinding implements Unbinder {
  private MeasurementHomeActivity target;

  @UiThread
  public MeasurementHomeActivity_ViewBinding(MeasurementHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeasurementHomeActivity_ViewBinding(MeasurementHomeActivity target, View source) {
    this.target = target;

    target.ivProfileBack = Utils.findRequiredViewAsType(source, R.id.ivProfileBack, "field 'ivProfileBack'", ImageView.class);
    target.ivDotView = Utils.findRequiredViewAsType(source, R.id.ivDotView, "field 'ivDotView'", ImageView.class);
    target.ivKeyPad = Utils.findRequiredViewAsType(source, R.id.ivKeyPad, "field 'ivKeyPad'", ImageView.class);
    target.edtQrCodeResult = Utils.findRequiredViewAsType(source, R.id.edtQrCodeResult, "field 'edtQrCodeResult'", EditText.class);
    target.ivQR = Utils.findRequiredViewAsType(source, R.id.ivQR, "field 'ivQR'", ImageView.class);
    target.llQrHeader = Utils.findRequiredViewAsType(source, R.id.llQrHeader, "field 'llQrHeader'", LinearLayout.class);
    target.llCamera = Utils.findRequiredViewAsType(source, R.id.llCamera, "field 'llCamera'", LinearLayout.class);
    target.llVideo = Utils.findRequiredViewAsType(source, R.id.llVideo, "field 'llVideo'", LinearLayout.class);
    target.edtProfileName = Utils.findRequiredViewAsType(source, R.id.edtProfileName, "field 'edtProfileName'", EditText.class);
    target.edtAgeValue = Utils.findRequiredViewAsType(source, R.id.edtAgeValue, "field 'edtAgeValue'", EditText.class);
    target.ivCalender = Utils.findRequiredViewAsType(source, R.id.ivCalender, "field 'ivCalender'", ImageView.class);
    target.llInnerView = Utils.findRequiredViewAsType(source, R.id.llInnerView, "field 'llInnerView'", LinearLayout.class);
    target.tvAge = Utils.findRequiredViewAsType(source, R.id.tvAge, "field 'tvAge'", TextView.class);
    target.llAge = Utils.findRequiredViewAsType(source, R.id.llAge, "field 'llAge'", CardView.class);
    target.llNext = Utils.findRequiredViewAsType(source, R.id.llNext, "field 'llNext'", LinearLayout.class);
    target.ivGallery = Utils.findRequiredViewAsType(source, R.id.ivGallery, "field 'ivGallery'", ImageView.class);
    target.edtHeightValue = Utils.findRequiredViewAsType(source, R.id.edtHeightValue, "field 'edtHeightValue'", EditText.class);
    target.ivHeight = Utils.findRequiredViewAsType(source, R.id.ivHeight, "field 'ivHeight'", ImageView.class);
    target.tvHeight = Utils.findRequiredViewAsType(source, R.id.tvHeight, "field 'tvHeight'", TextView.class);
    target.llHeight = Utils.findRequiredViewAsType(source, R.id.llHeight, "field 'llHeight'", CardView.class);
    target.edtWeightValue = Utils.findRequiredViewAsType(source, R.id.edtWeightValue, "field 'edtWeightValue'", EditText.class);
    target.ivWeight = Utils.findRequiredViewAsType(source, R.id.ivWeight, "field 'ivWeight'", ImageView.class);
    target.tvWeight = Utils.findRequiredViewAsType(source, R.id.tvWeight, "field 'tvWeight'", TextView.class);
    target.llWeight = Utils.findRequiredViewAsType(source, R.id.llWeight, "field 'llWeight'", CardView.class);
    target.ivVideo = Utils.findRequiredViewAsType(source, R.id.ivVideo, "field 'ivVideo'", ImageView.class);
    target.llHelp = Utils.findRequiredViewAsType(source, R.id.llHelp, "field 'llHelp'", LinearLayout.class);
    target.tvToolbarMeasurement = Utils.findRequiredViewAsType(source, R.id.tvToolbarMeasurement, "field 'tvToolbarMeasurement'", TextView.class);
    target.tvPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.tvPreviousMeasurement, "field 'tvPreviousMeasurement'", TextView.class);
    target.tvHelp = Utils.findRequiredViewAsType(source, R.id.tvHelp, "field 'tvHelp'", TextView.class);
    target.tvCustomerSupport = Utils.findRequiredViewAsType(source, R.id.tvCustomerSupport, "field 'tvCustomerSupport'", TextView.class);
    target.tvNext = Utils.findRequiredViewAsType(source, R.id.tvNext, "field 'tvNext'", TextView.class);
    target.btnClose = Utils.findRequiredViewAsType(source, R.id.btnClose, "field 'btnClose'", Button.class);
    target.flQRCodeValue = Utils.findRequiredViewAsType(source, R.id.flQRCodeValue, "field 'flQRCodeValue'", FrameLayout.class);
    target.llPreviousMeasurement = Utils.findRequiredViewAsType(source, R.id.llPreviousMeasurement, "field 'llPreviousMeasurement'", LinearLayout.class);
    target.Toolbar = Utils.findRequiredViewAsType(source, R.id.Toolbar, "field 'Toolbar'", Toolbar.class);
    target.rlScanView = Utils.findRequiredViewAsType(source, R.id.rlScanView, "field 'rlScanView'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeasurementHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivProfileBack = null;
    target.ivDotView = null;
    target.ivKeyPad = null;
    target.edtQrCodeResult = null;
    target.ivQR = null;
    target.llQrHeader = null;
    target.llCamera = null;
    target.llVideo = null;
    target.edtProfileName = null;
    target.edtAgeValue = null;
    target.ivCalender = null;
    target.llInnerView = null;
    target.tvAge = null;
    target.llAge = null;
    target.llNext = null;
    target.ivGallery = null;
    target.edtHeightValue = null;
    target.ivHeight = null;
    target.tvHeight = null;
    target.llHeight = null;
    target.edtWeightValue = null;
    target.ivWeight = null;
    target.tvWeight = null;
    target.llWeight = null;
    target.ivVideo = null;
    target.llHelp = null;
    target.tvToolbarMeasurement = null;
    target.tvPreviousMeasurement = null;
    target.tvHelp = null;
    target.tvCustomerSupport = null;
    target.tvNext = null;
    target.btnClose = null;
    target.flQRCodeValue = null;
    target.llPreviousMeasurement = null;
    target.Toolbar = null;
    target.rlScanView = null;
  }
}
