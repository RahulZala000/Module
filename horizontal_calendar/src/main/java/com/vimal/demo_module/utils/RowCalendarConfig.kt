package com.vimal.demo_module.utils

data class RowCalendarConfig(
    var sizeTopText: Float,
    var sizeBottomText: Float,
    var selectorColor: Int?,
    var formatTopText: String?,
    var formatBottomText: String?,
) {
    val DEFAULT_SIZE_TEXT_TOP = 14f
    val DEFAULT_SIZE_TEXT_BOTTOM = 20f

    val DEFAULT_FORMAT_TEXT_TOP = "dd"
    val DEFAULT_FORMAT_TEXT_BOTTOM = "EEE"

    fun setupDefaultValues(defaultConfig: RowCalendarConfig?) {
        if (defaultConfig == null) {
            return
        }
        if (selectorColor == null) {
            selectorColor = defaultConfig.selectorColor
        }
        if (sizeTopText == 0f) {
            sizeTopText = defaultConfig.sizeTopText
        }
        if (sizeBottomText == 0f) {
            sizeBottomText = defaultConfig.sizeBottomText
        }
    }

    companion object {

    }

}