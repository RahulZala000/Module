package com.vimal.demo_module.model

import android.graphics.drawable.Drawable

data class RowCalendarItemStyle(
    var colorDayText: Int = 0,
    var colorDateText: Int = 0,
    var backgroundDrawable: Drawable?,
) {
    fun setDefault(style: RowCalendarItemStyle?) {
        if (style == null) return
        if (this.colorDayText == 0) {
            this.colorDayText = style.colorDayText
        }
        if (this.colorDateText == 0) {
            this.colorDateText = style.colorDateText
        }
        if (this.backgroundDrawable == null) {
            this.backgroundDrawable = style.backgroundDrawable
        }
    }
}
