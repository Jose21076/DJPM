package com.example.tictactoe.resources

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.tictactoe.R

class Board{
    var x = 0
    var y = 0

    var bitmap: Bitmap

    constructor(context: Context, x: Int, y: Int) {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tictactoe)

        this.x = x - bitmap.width / 2
        this.y = y - (bitmap.height / 2)
    }
}