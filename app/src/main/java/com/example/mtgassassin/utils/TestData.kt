package com.example.mtgassassin.utils

import com.example.mtgassassin.model.Player

object TestData {
    val playersList = mutableListOf(
        Player("Robin", "Mickaël"),
        Player("Mickaël", "Victor"),
        Player("Victor", "Pierre"),
        Player("Pierre", "Numa"),
        Player("Numa", "Alex"),
        Player("Alex", "Robin"),
    )
}