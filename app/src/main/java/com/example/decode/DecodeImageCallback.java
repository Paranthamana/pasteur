package com.example.decode;

import com.google.zxing.Result;

import java.io.Serializable;

public interface DecodeImageCallback extends Serializable {

    void decodeSucceed(Result result);

    void decodeFail(int type, String reason);


}
