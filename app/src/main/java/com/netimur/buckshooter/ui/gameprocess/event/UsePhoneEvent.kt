package com.netimur.buckshooter.ui.gameprocess.event

import com.netimur.buckshooter.ui.gamesetting.CartridgeType

internal data class UsePhoneEvent(
    val cartridgeType: CartridgeType,
    val orderNumber: Int
) : GameProcessEvent