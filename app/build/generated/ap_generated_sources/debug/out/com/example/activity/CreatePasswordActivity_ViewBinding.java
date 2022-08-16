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

public class CreatePasswordActivity_ViewBinding implements Unbinder {
  private CreatePasswordActivity target;

  private View view7f0800f5;

  @UiThread
  public CreatePasswordActivity_ViewBinding(CreatePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreatePasswordActivity_ViewBinding(final CreatePasswordActivity target, View source) {
    this.target = target;

    View view;
    target.ivLogo = Utils.findRequiredViewAsType(source, R.id.ivLogo, "field 'ivLogo'", ImageView.class);
    target.tvForgotpasswordView = Utils.findRequiredViewAsType(source, R.id.tvForgotpasswordView, "field 'tvForgotpasswordView'", TextView.class);
    target.tvPleaseenternewpassword = Utils.findRequiredViewAsType(source, R.id.tvPleaseenternewpassword, "field 'tvPleaseenternewpassword'", TextView.class);
    target.tvMobileNumber = Utils.findRequiredViewAsType(source, R.id.tvMobileNumber, "field 'tvMobileNumber'", TextView.class);
    target.edtMobileNo = Utils.findRequiredViewAsType(source, R.id.edtMobileNo, "field 'edtMobileNo'", EditText.class);
    target.tvConfirmpassword = Utils.findRequiredViewAsType(source, R.id.tvConfirmpassword, "field 'tvConfirmpassword'", TextView.class);
    target.edtConfirmPassword = Utils.findRequiredViewAsType(source, R.id.edtConfirmPassword, "field 'edtConfirmPassword'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    view = Utils.findRequiredView(source, R.id.llCreatePasswordSubmit, "field 'llCreatePasswordSubmit' and method 'onViewClicked'");
    target.llCreatePasswordSubmit = Utils.castView(view, R.id.llCreatePasswordSubmit, "field 'llCreatePasswordSubmit'", LinearLayout.class);
    view7f0800f5 = view;
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
    CreatePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivLogo = null;
    target.tvForgotpasswordView = null;
    target.tvPleaseenternewpassword = null;
    target.tvMobileNumber = null;
    target.edtMobileNo = null;
    target.tvConfirmpassword = null;
    target.edtConfirmPassword = null;
    target.tvSubmit = null;
    target.llCreatePasswordSubmit = null;

    view7f0800f5.setOnClickListener(null);
    view7f0800f5 = null;
  }
}
