package com.netimur.buckshooter.core.foundation.utils

inline fun <I, O> List<I>.mapList(mapSingle: I.() -> O): List<O> {
    return mapNotNull { it.mapSingle() }
}