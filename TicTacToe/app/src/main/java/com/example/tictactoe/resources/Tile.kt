package com.example.tictactoe.resources

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect

class Tile {
    var x = 0
    var y = 0
    var width = 0
    var height = 0

    var bitmap: Bitmap? = null

    var detectCollision: Rect
    var playable = true

    constructor(context: Context, x: Int, y: Int, width: Int, height: Int) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        this.x = x
        this.y = y
        this.width = width
        this.height = height

        detectCollision = Rect(x, y, x + width, y + height)
    }
}