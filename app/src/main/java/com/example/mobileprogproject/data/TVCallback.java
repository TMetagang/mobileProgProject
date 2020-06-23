package com.example.mobileprogproject.data;

import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.presentation.model.RestMPTvResponse;

import java.util.List;

public interface TVCallback {
     void onSuccess(List<MPTv> response);
     void onFailed();

}
