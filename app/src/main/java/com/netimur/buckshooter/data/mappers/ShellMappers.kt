package com.netimur.buckshooter.data.mappers

import com.netimur.buckshooter.core.storage.model.ShellEntity
import com.netimur.buckshooter.data.model.Shell
import com.netimur.buckshooter.data.model.ShellOrdinalNumber
import com.netimur.buckshooter.data.model.ShellType

internal fun Shell.toEntity(): ShellEntity {
    return ShellEntity(
        shellType = shellType.name,
        number = orderNumber.toIntOrNull()
    )
}

internal fun ShellEntity.toDomain(): Shell {
    return Shell(
        orderNumber = number.toShellOrdinalNumber(),
        shellType = shellType.toShellTypeOrUnknown()
    )
}

private fun String.toShellTypeOrUnknown(): ShellType {
    return try {
        ShellType.valueOf(this)
    } catch (e: IllegalArgumentException) {
        ShellType.UNKNOWN
    }
}

private fun Int?.toShellOrdinalNumber(): ShellOrdinalNumber {
    return if (this != null) {
        ShellOrdinalNumber.Concrete(orderNumber = this)
    } else {
        ShellOrdinalNumber.Unknown
    }
}

private fun ShellOrdinalNumber.toIntOrNull(): Int? {
    return when (this) {
        is ShellOrdinalNumber.Concrete -> orderNumber
        ShellOrdinalNumber.Unknown -> null
    }
}