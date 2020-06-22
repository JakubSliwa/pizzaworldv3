package pl.pizzaworld.engine.application.time

import java.time.OffsetDateTime


interface TimeProvider {
    fun now(): OffsetDateTime?
}