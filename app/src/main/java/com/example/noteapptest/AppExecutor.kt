package com.example.noteapptest

import java.util.concurrent.Executors

object AppExecutor {
    val ioExecutor = Executors.newFixedThreadPool(4)
}