package com.example.bomber

import android.graphics.Rect

class Block {
    var x = 0
    var y = 0
    var width = 0
    var height = 0
    var maxX = 0
    var speed = 10

    var detectCollision: Rect

    constructor(width: Int, height: Int, x: Int, y: Int, maxX: Int){
        this.width = width
        this.height = height
        this.x = x
        this.y = y
        this.maxX = maxX

        detectCollision = Rect(x, y, width, height)

    }

    fun update() {
        x -= speed

        if (x < -200){
            x = maxX
        }
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + width
        detectCollision.bottom = y + height
    }
}