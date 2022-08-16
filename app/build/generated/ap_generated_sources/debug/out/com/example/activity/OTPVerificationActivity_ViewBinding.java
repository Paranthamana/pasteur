// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OTPVerificationActivity_ViewBinding implements Unbinder {
  private OTPVerificationActivity target;

  private View view7f080106;

  @UiThread
  public OTPVerificationActivity_ViewBinding(OTPVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OTPVerificationActivity_ViewBinding(final OTPVerificationActivity target, View source) {
    this.target = target;

    View view;
    target.tvEnterOtp = Utils.findRequiredViewAsType(source, R.id.tvEnterOtp, "field 'tvEnterOtp'", TextView.class);
    target.ivOTP = Utils.findRequiredViewAsType(source, R.id.ivOTP, "field 'ivOTP'", ImageView.class);
    target.tvWehaveSendAccessCode = Utils.findRequiredViewAsType(source, R.id.tvWehaveSendAccessCode, "field 'tvWehaveSendAccessCode'", TextView.class);
    target.tvPleaseEnterDigitCode = Utils.findRequiredViewAsType(source, R.id.tvPleaseEnterDigitCode, "field 'tvPleaseEnterDigitCode'", TextView.class);
    view = Utils.findRequiredView(source, R.id.llOtpSubmit, "field 'llOtpSubmit' and method 'onViewClicked'");
    target.llOtpSubmit = Utils.castView(view, R.id.llOtpSubmit, "field 'llOtpSubmit'", LinearLayout.class);
    view7f080106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OTPVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvEnterOtp = null;
    target.ivOTP = null;
    target.tvWehaveSendAccessCode = null;
    target.tvPleaseEnterDigitCode = null;
    target.llOtpSubmit = null;

    view7f080106.setOnClickListener(null);
    view7f080106 = null;
  }
}
