package com.example.bomber

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.Random

class Miner {
    var x = 0
    var y = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var generator = Random()

    var bitmap: Bitmap

    var detectCollision: Rect

    constructor(context: Context, width: Int, height: Int){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.miner)

        minX = 0
        maxX = width - bitmap.width

        minY = 0
        maxY = height - bitmap.height

        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)

    }

    fun update(){
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}