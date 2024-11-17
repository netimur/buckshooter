package com.netimur.buckshooter.data.mappers

import com.netimur.buckshooter.core.storage.model.CartridgeEntity
import com.netimur.buckshooter.data.model.Cartridge
import com.netimur.buckshooter.data.model.CartridgeOrdinalNumber
import com.netimur.buckshooter.data.model.CartridgeType

internal fun Cartridge.toEntity(): CartridgeEntity {
    return CartridgeEntity(
        cartridgeType = cartridgeType.name,
        number = orderNumber.toIntOrNull()
    )
}

internal fun CartridgeEntity.toDomain(): Cartridge {
    return Cartridge(
        orderNumber = number.toCartridgeOrdinalNumber(),
        cartridgeType = cartridgeType.toCartridgeTypeOrUnknown()
    )
}

private fun String.toCartridgeTypeOrUnknown(): CartridgeType {
    return try {
        CartridgeType.valueOf(this)
    } catch (e: IllegalArgumentException) {
        CartridgeType.UNKNOWN
    }
}

private fun Int?.toCartridgeOrdinalNumber(): CartridgeOrdinalNumber {
    return if (this != null) {
        CartridgeOrdinalNumber.Concrete(orderNumber = this)
    } else {
        CartridgeOrdinalNumber.Unknown
    }
}

private fun CartridgeOrdinalNumber.toIntOrNull(): Int? {
    return when (this) {
        is CartridgeOrdinalNumber.Concrete -> orderNumber
        CartridgeOrdinalNumber.Unknown -> null
    }
}