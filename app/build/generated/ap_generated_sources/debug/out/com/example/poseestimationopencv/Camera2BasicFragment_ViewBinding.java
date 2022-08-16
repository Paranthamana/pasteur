// Generated code from Butter Knife. Do not modify!
package com.example.poseestimationopencv;

import android.view.View;
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

public class Camera2BasicFragment_ViewBinding implements Unbinder {
  private Camera2BasicFragment target;

  @UiThread
  public Camera2BasicFragment_ViewBinding(Camera2BasicFragment target, View source) {
    this.target = target;

    target.texture = Utils.findRequiredViewAsType(source, R.id.texture, "field 'texture'", AutoFitTextureView.class);
    target.drawview = Utils.findRequiredViewAsType(source, R.id.drawview, "field 'drawview'", DrawView.class);
    target.ivPoseDetails = Utils.findRequiredViewAsType(source, R.id.ivPoseDetails, "field 'ivPoseDetails'", ImageView.class);
    target.layoutFrame = Utils.findRequiredViewAsType(source, R.id.layout_frame, "field 'layoutFrame'", AutoFitFrameLayout.class);
    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", TextView.class);
    target.layoutBottom = Utils.findRequiredViewAsType(source, R.id.layout_bottom, "field 'layoutBottom'", LinearLayout.class);
    target.cPreviews = Utils.findRequiredViewAsType(source, R.id.cPreviews, "field 'cPreviews'", LinearLayout.class);
    target.num = Utils.findRequiredViewAsType(source, R.id.num, "field 'num'", TextView.class);
    target.ivCapture = Utils.findRequiredViewAsType(source, R.id.ivCapture, "field 'ivCapture'", ImageView.class);
    target.ivCamBtn = Utils.findRequiredViewAsType(source, R.id.ivCamBtn, "field 'ivCamBtn'", ImageView.class);
    target.tvLeftRight = Utils.findRequiredViewAsType(source, R.id.tvLeftRight, "field 'tvLeftRight'", TextView.class);
    target.tvHorizontal = Utils.findRequiredViewAsType(source, R.id.tvHorizontal, "field 'tvHorizontal'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Camera2BasicFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.texture = null;
    target.drawview = null;
    target.ivPoseDetails = null;
    target.layoutFrame = null;
    target.text = null;
    target.tvMessage = null;
    target.layoutBottom = null;
    target.cPreviews = null;
    target.num = null;
    target.ivCapture = null;
    target.ivCamBtn = null;
    target.tvLeftRight = null;
    target.tvHorizontal = null;
  }
}
