package com.netimur.buckshooter.data.model

sealed class ShellOrdinalNumber {
    data object Unknown : ShellOrdinalNumber()
    data class Concrete(val orderNumber: Int) : ShellOrdinalNumber()
}