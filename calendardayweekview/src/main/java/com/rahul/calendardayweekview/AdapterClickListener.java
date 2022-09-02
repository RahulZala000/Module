package com.rahul.calendardayweekview;

import android.view.View;

import java.time.LocalDateTime;

public interface AdapterClickListener {
    void onItemClick(View view, int pos, Object object);
}
