// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HelpActivity_ViewBinding implements Unbinder {
  private HelpActivity target;

  private View view7f0800ca;

  @UiThread
  public HelpActivity_ViewBinding(HelpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpActivity_ViewBinding(final HelpActivity target, View source) {
    this.target = target;

    View view;
    target.vvHelp = Utils.findRequiredViewAsType(source, R.id.vvHelp, "field 'vvHelp'", VideoView.class);
    view = Utils.findRequiredView(source, R.id.ivHelpBack, "field 'ivHelpBack' and method 'onViewClicked'");
    target.ivHelpBack = Utils.castView(view, R.id.ivHelpBack, "field 'ivHelpBack'", ImageView.class);
    view7f0800ca = view;
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
    HelpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vvHelp = null;
    target.ivHelpBack = null;

    view7f0800ca.setOnClickListener(null);
    view7f0800ca = null;
  }
}
