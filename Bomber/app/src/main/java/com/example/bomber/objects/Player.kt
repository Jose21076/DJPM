package com.example.bomber.objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.bomber.R

class Player {

    var x = 0
    var y = 0
    var speedRight = 10
    var speedLeft = -10
    var minY = 0
    var maxY = 0
    var minX = 0
    var maxX = 0

    var bitmap : Bitmap
    var right = false
    var left = false
    var up = false
    var down = false

    var detectCollision : Rect

    constructor(context: Context, height: Int, width: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        maxY = height - bitmap.height
        minY = 0

        minX = 0
        maxX = width - bitmap.width

        x = width / 2
        y = maxY

        detectCollision = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(){

        if (right) x += speedRight
        if (left) x += speedLeft
        if (up) y += speedLeft
        if (down) y += speedRight

        if (x < minX) x = minX
        if (x > maxX) x = maxX
        if(y< minY) y = minY
        if(y> maxY) y = maxY

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

}