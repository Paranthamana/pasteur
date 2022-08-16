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
import butterknife.internal.Utils;
import com.example.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPassword_ViewBinding implements Unbinder {
  private ForgotPassword target;

  @UiThread
  public ForgotPassword_ViewBinding(ForgotPassword target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotPassword_ViewBinding(ForgotPassword target, View source) {
    this.target = target;

    target.ivLogo = Utils.findRequiredViewAsType(source, R.id.ivLogo, "field 'ivLogo'", ImageView.class);
    target.tvForgotpassword = Utils.findRequiredViewAsType(source, R.id.tvForgotpassword, "field 'tvForgotpassword'", TextView.class);
    target.tvSigintocontinue = Utils.findRequiredViewAsType(source, R.id.tvSigintocontinue, "field 'tvSigintocontinue'", TextView.class);
    target.tvMobileNumber = Utils.findRequiredViewAsType(source, R.id.tvMobileNumber, "field 'tvMobileNumber'", TextView.class);
    target.edtMobileNo = Utils.findRequiredViewAsType(source, R.id.edtMobileNo, "field 'edtMobileNo'", EditText.class);
    target.tvConfirmpassword = Utils.findRequiredViewAsType(source, R.id.tvConfirmpassword, "field 'tvConfirmpassword'", TextView.class);
    target.edtConfirmPassword = Utils.findRequiredViewAsType(source, R.id.edtConfirmPassword, "field 'edtConfirmPassword'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    target.llSubmit = Utils.findRequiredViewAsType(source, R.id.llSubmit, "field 'llSubmit'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPassword target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivLogo = null;
    target.tvForgotpassword = null;
    target.tvSigintocontinue = null;
    target.tvMobileNumber = null;
    target.edtMobileNo = null;
    target.tvConfirmpassword = null;
    target.edtConfirmPassword = null;
    target.tvSubmit = null;
    target.llSubmit = null;
  }
}
