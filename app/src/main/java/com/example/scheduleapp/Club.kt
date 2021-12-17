package com.example.scheduleapp

import java.io.Serializable
import java.util.*

class Club : Serializable {
    var clubID: String? = null
    var name: String? = null
    var description: String? = null
    var specialty: String? = null
    var tutor: String? = null
    var clubLeader:String? = null
    var themes: String? = null

    constructor() {

    }

    constructor(name:String) {
        this.name = name
    }
}