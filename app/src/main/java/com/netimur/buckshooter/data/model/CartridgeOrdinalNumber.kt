package com.netimur.buckshooter.data.model

sealed class CartridgeOrdinalNumber {
    data object Unknown : CartridgeOrdinalNumber()
    data class Concrete(val orderNumber: Int) : CartridgeOrdinalNumber()
}