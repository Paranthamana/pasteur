// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f080090;

  private View view7f0801ac;

  private View view7f08008d;

  private View view7f0801a3;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.tvLoginView = Utils.findRequiredViewAsType(source, R.id.tvLoginView, "field 'tvLoginView'", TextView.class);
    target.ivRememberChecked = Utils.findRequiredViewAsType(source, R.id.ivRememberChecked, "field 'ivRememberChecked'", ImageView.class);
    target.llRememberMe = Utils.findRequiredViewAsType(source, R.id.llRememberMe, "field 'llRememberMe'", LinearLayout.class);
    target.ivLogo = Utils.findRequiredViewAsType(source, R.id.ivLogo, "field 'ivLogo'", ImageView.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tvLogin, "field 'tvLogin'", TextView.class);
    target.tvSigInToContinue = Utils.findRequiredViewAsType(source, R.id.tvSigInToContinue, "field 'tvSigInToContinue'", TextView.class);
    target.tvAccountId = Utils.findRequiredViewAsType(source, R.id.tvAccountId, "field 'tvAccountId'", TextView.class);
    view = Utils.findRequiredView(source, R.id.edtUserName, "field 'edtUserName' and method 'onViewClicked'");
    target.edtUserName = Utils.castView(view, R.id.edtUserName, "field 'edtUserName'", EditText.class);
    view7f080090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPassword = Utils.findRequiredViewAsType(source, R.id.tvPassword, "field 'tvPassword'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvForgotPassword, "field 'tvForgotPassword' and method 'onViewClicked'");
    target.tvForgotPassword = Utils.castView(view, R.id.tvForgotPassword, "field 'tvForgotPassword'", TextView.class);
    view7f0801ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.edtPassword, "field 'edtPassword' and method 'onViewClicked'");
    target.edtPassword = Utils.castView(view, R.id.edtPassword, "field 'edtPassword'", EditText.class);
    view7f08008d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvRememberMe = Utils.findRequiredViewAsType(source, R.id.tvRememberMe, "field 'tvRememberMe'", TextView.class);
    target.llLogin = Utils.findRequiredViewAsType(source, R.id.llLogin, "field 'llLogin'", LinearLayout.class);
    target.llLoginView = Utils.findRequiredViewAsType(source, R.id.llLoginView, "field 'llLoginView'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tvCreateAccount, "field 'tvCreateAccount' and method 'onViewClicked'");
    target.tvCreateAccount = Utils.castView(view, R.id.tvCreateAccount, "field 'tvCreateAccount'", TextView.class);
    view7f0801a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llBanner = Utils.findRequiredViewAsType(source, R.id.llBanner, "field 'llBanner'", LinearLayout.class);
    target.rlLogin = Utils.findRequiredViewAsType(source, R.id.rlLogin, "field 'rlLogin'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvLoginView = null;
    target.ivRememberChecked = null;
    target.llRememberMe = null;
    target.ivLogo = null;
    target.tvLogin = null;
    target.tvSigInToContinue = null;
    target.tvAccountId = null;
    target.edtUserName = null;
    target.tvPassword = null;
    target.tvForgotPassword = null;
    target.edtPassword = null;
    target.tvRememberMe = null;
    target.llLogin = null;
    target.llLoginView = null;
    target.tvCreateAccount = null;
    target.llBanner = null;
    target.rlLogin = null;

    view7f080090.setOnClickListener(null);
    view7f080090 = null;
    view7f0801ac.setOnClickListener(null);
    view7f0801ac = null;
    view7f08008d.setOnClickListener(null);
    view7f08008d = null;
    view7f0801a3.setOnClickListener(null);
    view7f0801a3 = null;
  }
}
