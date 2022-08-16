// Generated code from Butter Knife. Do not modify!
package com.example.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.R;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QRCodeActivity_ViewBinding implements Unbinder {
  private QRCodeActivity target;

  private View view7f08005e;

  @UiThread
  public QRCodeActivity_ViewBinding(QRCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QRCodeActivity_ViewBinding(final QRCodeActivity target, View source) {
    this.target = target;

    View view;
    target.qrCodeHeaderBlackPic = Utils.findRequiredViewAsType(source, R.id.qr_code_header_black_pic, "field 'qrCodeHeaderBlackPic'", Button.class);
    target.qrCodeHeaderBar = Utils.findRequiredViewAsType(source, R.id.qr_code_header_bar, "field 'qrCodeHeaderBar'", RelativeLayout.class);
    target.zxingBarcodeScanner = Utils.findRequiredViewAsType(source, R.id.zxing_barcode_scanner, "field 'zxingBarcodeScanner'", DecoratedBarcodeView.class);
    view = Utils.findRequiredView(source, R.id.btnQRBack, "field 'btnQRBack' and method 'onViewClicked'");
    target.btnQRBack = Utils.castView(view, R.id.btnQRBack, "field 'btnQRBack'", Button.class);
    view7f08005e = view;
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
    QRCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qrCodeHeaderBlackPic = null;
    target.qrCodeHeaderBar = null;
    target.zxingBarcodeScanner = null;
    target.btnQRBack = null;

    view7f08005e.setOnClickListener(null);
    view7f08005e = null;
  }
}
