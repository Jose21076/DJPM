package com.example.bomber.objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.bomber.R
import java.util.Random

class Bomb {

    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap: Bitmap

    var generator = Random()

    var detectCollision: Rect

    constructor(context: Context, width: Int, height: Int){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bomb)

        minX = 0
        maxX = width

        minY = 0
        maxY = height - bitmap.height

        x = generator.nextInt(maxX)
        y = minY

        speed = generator.nextInt(5) + 10

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)

    }

    fun update() {
        y += speed

        if (y > maxY - bitmap.height){
            x = Random().nextInt(maxX)
            y = minY
            speed = generator.nextInt(5) + 10
        }

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}
