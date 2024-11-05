package com.example.bomber

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Boom {
    var x = 0
    var y = 0

    var bitmap: Bitmap

    var detectCollision: Rect

    constructor(context: Context, width: Int, height: Int){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boom)

        x = -300
        y = -300

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)

    }

    fun update(){
        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }
}