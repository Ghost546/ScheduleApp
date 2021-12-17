package com.example.scheduleapp

interface ITagForLog {
    val tag: String
    get() = this.javaClass.simpleName
}