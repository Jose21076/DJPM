package com.example.bomber


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.bomber.ui.theme.Brown

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas

    lateinit var paint :Paint
    var blocks = arrayListOf<Block>()
    lateinit var player : Player
    var bombs  = arrayListOf<Bomb>()
    lateinit var boom : Boom

    private fun init(context: Context, width: Int, height: Int){

        surfaceHolder = holder
        paint = Paint()

        var x = 0
        var y = 0
        while(x < width + 500) {
            while (y < height + 100) {
                blocks.add(Block(200, 200, x, y, width + 500))
                y += 200
            }
            y = 0
            x += 200
        }


        player = Player(context, height)
        for (i in 0..2){
            bombs.add(Bomb(context, width, height))
        }
        boom = Boom(context, width, height)

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
        player.update()

        boom.x = -500
        boom.y = -500

        for (block in blocks) {
            block.update()
        }

        for (bomb in bombs){
            bomb.update()
            if (Rect.intersects(player.detectCollision, bomb.detectCollision)) {
                boom.x = bomb.x
                boom.y = bomb.y
                boom.update()
                bomb.x = -500
                for (block in blocks) {
                    if (Rect.intersects(block.detectCollision, boom.detectCollision)) {
                        block.x = block.maxX
                    }
                }
            }
        }


    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            // design code here

            canvas.drawColor(Color.BLACK)

            paint.color = Brown.hashCode()
            for (block in blocks) {
                val rect : Rect = Rect(block.x, block.y, block.width, block.height)
                canvas.drawRect(
                    rect,
                    paint
                )
            }

            for (bomb in bombs){
                canvas.drawBitmap(bomb.bitmap, bomb.x.toFloat(), bomb.y.toFloat(), paint)
            }

            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                player.boosting = true
            }
            MotionEvent.ACTION_UP -> {
                player.boosting = false
            }
        }
        return true
    }
}