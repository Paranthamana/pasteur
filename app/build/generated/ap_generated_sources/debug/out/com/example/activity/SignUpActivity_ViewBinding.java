// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.EditText;
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

public class SignUpActivity_ViewBinding implements Unbinder {
  private SignUpActivity target;

  private View view7f08008c;

  private View view7f0801af;

  private View view7f08010e;

  @UiThread
  public SignUpActivity_ViewBinding(SignUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignUpActivity_ViewBinding(final SignUpActivity target, View source) {
    this.target = target;

    View view;
    target.ivLogo = Utils.findRequiredViewAsType(source, R.id.ivLogo, "field 'ivLogo'", ImageView.class);
    target.tvSignUp = Utils.findRequiredViewAsType(source, R.id.tvSignup, "field 'tvSignUp'", TextView.class);
    target.tvEnterMobileToContinue = Utils.findRequiredViewAsType(source, R.id.tvEntermobileToContinue, "field 'tvEnterMobileToContinue'", TextView.class);
    view = Utils.findRequiredView(source, R.id.edtMobileNumber, "field 'edtMobileNumber' and method 'onViewClicked'");
    target.edtMobileNumber = Utils.castView(view, R.id.edtMobileNumber, "field 'edtMobileNumber'", EditText.class);
    view7f08008c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvHaveAccount, "field 'tvHaveAccount' and method 'onViewClicked'");
    target.tvHaveAccount = Utils.castView(view, R.id.tvHaveAccount, "field 'tvHaveAccount'", TextView.class);
    view7f0801af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMobileNumberView = Utils.findRequiredViewAsType(source, R.id.tvMobileNumberView, "field 'tvMobileNumberView'", TextView.class);
    target.tvSendView = Utils.findRequiredViewAsType(source, R.id.tvSendView, "field 'tvSendView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.llSend, "field 'llSend' and method 'onViewClicked'");
    target.llSend = Utils.castView(view, R.id.llSend, "field 'llSend'", LinearLayout.class);
    view7f08010e = view;
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
    SignUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivLogo = null;
    target.tvSignUp = null;
    target.tvEnterMobileToContinue = null;
    target.edtMobileNumber = null;
    target.tvHaveAccount = null;
    target.tvMobileNumberView = null;
    target.tvSendView = null;
    target.llSend = null;

    view7f08008c.setOnClickListener(null);
    view7f08008c = null;
    view7f0801af.setOnClickListener(null);
    view7f0801af = null;
    view7f08010e.setOnClickListener(null);
    view7f08010e = null;
  }
}
