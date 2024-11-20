package com.netimur.buckshooter.ui.gameprocess.alert

internal sealed interface GameProcessAlert {
    data object NoneAlert : GameProcessAlert
}