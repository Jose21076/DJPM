package com.example.tictactoe

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.tictactoe.resources.Board
import com.example.tictactoe.resources.Tile
import com.example.tictactoe.ui.theme.Purple40

class GameView: SurfaceView, Runnable {
    var playing = false
    var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas
    val padding = 23
    lateinit var board : Board

    var player = 0

    lateinit var rect1_1 : Tile
    lateinit var rect1_2 : Tile
    lateinit var rect1_3 : Tile

    lateinit var rect2_1 : Tile
    lateinit var rect2_2 : Tile
    lateinit var rect2_3 : Tile

    lateinit var rect3_1 : Tile
    lateinit var rect3_2 : Tile
    lateinit var rect3_3 : Tile

    lateinit var paint : Paint

    private fun init(context: Context, width: Int, height: Int){

        surfaceHolder = holder
        paint = Paint()

        board = Board(context, (width/2), (height/2))

        rect1_1 = Tile(context, board.x, board.y, board.bitmap.width / 3, board.bitmap.height / 3)
        rect1_2 = Tile(context, (board.x + board.bitmap.width / 3) + padding, board.y, board.bitmap.width / 3, board.bitmap.height / 3)
        rect1_3 = Tile(context, (board.x + ((board.bitmap.width / 3) * 2)) + padding, board.y, board.bitmap.width / 3, board.bitmap.height / 3)

        rect2_1 = Tile(context, board.x, (board.y + board.bitmap.height / 3) + padding, board.bitmap.width / 3, board.bitmap.height / 3)
        rect2_2 = Tile(context, (board.x + board.bitmap.width / 3) + padding, (board.y + board.bitmap.height / 3) + padding, board.bitmap.width / 3, board.bitmap.height / 3)
        rect2_3 = Tile(context, (board.x + ((board.bitmap.width / 3) * 2)) + padding, (board.y + board.bitmap.height / 3) + padding, board.bitmap.width / 3, board.bitmap.height / 3)

        rect3_1 = Tile(context, board.x, (board.y + ((board.bitmap.height / 3) * 2)) + padding, board.bitmap.width / 3, board.bitmap.height / 3)
        rect3_2 = Tile(context, (board.x + board.bitmap.width / 3) + padding, (board.y + ((board.bitmap.height / 3) * 2)) + padding,board.bitmap.width / 3, board.bitmap.height / 3)
        rect3_3 = Tile(context, (board.x + ((board.bitmap.width / 3) * 2)) + padding, (board.y + ((board.bitmap.height / 3) * 2)) + padding, board.bitmap.width / 3, board.bitmap.height / 3)


    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }

    override fun run() {
        while (playing){
            update()
            draw()
            control()
        }
    }


    fun update(){

    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            // design code here

            canvas.drawColor(Purple40.hashCode())

            canvas.drawBitmap(board.bitmap, board.x.toFloat(), board.y.toFloat(), paint)

            paint.color = Color.WHITE
            canvas.drawBitmap(rect1_1.bitmap!!, rect1_1.x.toFloat(), rect1_1.y.toFloat(), paint)
            canvas.drawBitmap(rect1_2.bitmap!!, rect1_2.x.toFloat(), rect1_2.y.toFloat(), paint)
            canvas.drawBitmap(rect1_3.bitmap!!, rect1_3.x.toFloat(), rect1_3.y.toFloat(), paint)
            canvas.drawBitmap(rect2_1.bitmap!!, rect2_1.x.toFloat(), rect2_1.y.toFloat(), paint)
            canvas.drawBitmap(rect2_2.bitmap!!, rect2_2.x.toFloat(), rect2_2.y.toFloat(), paint)
            canvas.drawBitmap(rect2_3.bitmap!!, rect2_3.x.toFloat(), rect2_3.y.toFloat(), paint)
            canvas.drawBitmap(rect3_1.bitmap!!, rect3_1.x.toFloat(), rect3_1.y.toFloat(), paint)
            canvas.drawBitmap(rect3_2.bitmap!!, rect3_2.x.toFloat(), rect3_2.y.toFloat(), paint)
            canvas.drawBitmap(rect3_3.bitmap!!, rect3_3.x.toFloat(), rect3_3.y.toFloat(), paint)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN-> {
                if((rect1_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect1_1.playable){
                    rect1_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect1_1.bitmap!!, rect1_1.x.toFloat(), rect1_1.y.toFloat(), paint)
                    player++
                    rect1_1.playable = false
                }
                else if((rect1_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect1_1.playable){
                    rect1_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect1_1.bitmap!!, rect1_1.x.toFloat(), rect1_1.y.toFloat(), paint)
                    player--
                    rect1_1.playable = false
                }
                if((rect1_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect1_2.playable){
                    rect1_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect1_2.bitmap!!, rect1_2.x.toFloat(), rect1_2.y.toFloat(), paint)
                    player++
                    rect1_2.playable = false
                }
                else if((rect1_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect1_2.playable){
                    rect1_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect1_2.bitmap!!, rect1_2.x.toFloat(), rect1_2.y.toFloat(), paint)
                    player--
                    rect1_2.playable = false
                }
                if((rect1_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect1_3.playable){
                    rect1_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect1_3.bitmap!!, rect1_3.x.toFloat(), rect1_3.y.toFloat(), paint)
                    player++
                    rect1_3.playable = false
                }
                else if((rect1_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect1_3.playable){
                    rect1_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect1_3.bitmap!!, rect1_3.x.toFloat(), rect1_3.y.toFloat(), paint)
                    player--
                    rect1_3.playable = false
                }
                if((rect2_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect2_1.playable){
                    rect2_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect2_1.bitmap!!, rect2_1.x.toFloat(), rect2_1.y.toFloat(), paint)
                    player++
                    rect2_1.playable = false
                }
                else if((rect2_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect2_1.playable){
                    rect2_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect2_1.bitmap!!, rect2_1.x.toFloat(), rect2_1.y.toFloat(), paint)
                    player--
                    rect2_1.playable = false
                }
                if((rect2_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect2_2.playable){
                    rect2_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect2_2.bitmap!!, rect2_2.x.toFloat(), rect2_2.y.toFloat(), paint)
                    player++
                    rect2_2.playable = false
                }
                else if((rect2_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect2_2.playable){
                    rect2_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect2_2.bitmap!!, rect2_2.x.toFloat(), rect2_2.y.toFloat(), paint)
                    player--
                    rect2_2.playable = false
                }
                if((rect2_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect2_3.playable){
                    rect2_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect2_3.bitmap!!, rect2_3.x.toFloat(), rect2_3.y.toFloat(), paint)
                    player++
                    rect2_3.playable = false
                }
                else if((rect2_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect2_3.playable){
                    rect2_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect2_3.bitmap!!, rect2_3.x.toFloat(), rect2_3.y.toFloat(), paint)
                    player--
                    rect2_3.playable = false
                }
                if((rect3_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect3_1.playable){
                    rect3_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect3_1.bitmap!!, rect3_1.x.toFloat(), rect3_1.y.toFloat(), paint)
                    player++
                    rect3_1.playable = false
                }
                else if((rect3_1.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect3_1.playable){
                    rect3_1.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect3_1.bitmap!!, rect3_1.x.toFloat(), rect3_1.y.toFloat(), paint)
                    player--
                    rect3_1.playable = false
                }
                if((rect3_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect3_2.playable){
                    rect3_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect3_2.bitmap!!, rect3_2.x.toFloat(), rect3_2.y.toFloat(), paint)
                    player++
                    rect3_2.playable = false
                }
                else if((rect3_2.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect3_2.playable){
                    rect3_2.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect3_2.bitmap!!, rect3_2.x.toFloat(), rect3_2.y.toFloat(), paint)
                    player--
                    rect3_2.playable = false
                }
                if((rect3_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 0 && rect3_3.playable){
                    rect3_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.x)
                    canvas.drawBitmap(rect3_3.bitmap!!, rect3_3.x.toFloat(), rect3_3.y.toFloat(), paint)
                    player++
                    rect3_3.playable = false
                }
                else if((rect3_3.detectCollision.contains(event.x.toInt(), event.y.toInt())) && player == 1 && rect3_3.playable){
                    rect3_3.bitmap = BitmapFactory.decodeResource(resources, R.drawable.circle)
                    canvas.drawBitmap(rect3_3.bitmap!!, rect3_3.x.toFloat(), rect3_3.y.toFloat(), paint)
                    player--
                    rect3_3.playable = false
                }
            }
        }
        return true
    }
}