package com.openflechette.model

import java.io.Serializable

/**
 * Paramètres de configuration d'une partie
 */
data class GameSettings(
    val gameMode: GameMode = GameMode.POINTS_501,
    val checkIn: CheckIn = CheckIn.STRAIGHT_IN,
    val checkOut: CheckOut = CheckOut.DOUBLE_OUT,
    val legs: Int = 1,
    val players: List<Player> = emptyList()
) : Serializable {
    enum class GameMode(val displayName: String, val points: Int) : Serializable {
        POINTS_101("101", 101),
        POINTS_201("201", 201),
        POINTS_301("301", 301),
        POINTS_401("401", 401),
        POINTS_501("501", 501),
        POINTS_601("601", 601),
        POINTS_701("701", 701),
        POINTS_801("801", 801),
        POINTS_901("901", 901),
        POINTS_1001("1001", 1001)
    }
    
    enum class CheckIn(val displayName: String) : Serializable {
        STRAIGHT_IN("Straight In"),
        DOUBLE_IN("Double In")
    }
    
    enum class CheckOut(val displayName: String) : Serializable {
        STRAIGHT_OUT("Straight Out"),
        DOUBLE_OUT("Double Out")
    }
}
