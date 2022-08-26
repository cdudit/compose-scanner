package fr.cdudit.composescanner.models

import java.util.*

data class File(
    val name: String,
    val fullPath: String,
    val date: Date
)

val sampleData = mutableListOf(
    File(name = "Lorem Ipsum", fullPath = "", date = Date()),
    File(name = "Dolor Sit", fullPath = "", date = Date()),
    File(name = "Amet Lorem", fullPath = "", date = Date()),
    File(name = "Ipsum Test", fullPath = "", date = Date()),
)