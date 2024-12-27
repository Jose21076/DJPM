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
import com.example.bomber.objects.Block
import com.example.bomber.objects.Bomb
import com.example.bomber.objects.Boom
import com.example.bomber.objects.Diamond
import com.example.bomber.objects.Miner
import com.example.bomber.objects.Player
import com.example.bomber.ui.theme.Brown

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas

    lateinit var paint :Paint
    lateinit var block: Block
    lateinit var player : Player
    var bombs  = arrayListOf<Bomb>()
    lateinit var boom : Boom
    var diamonds = arrayListOf<Diamond>()
    var miners = arrayListOf<Miner>()

    var lives = 3
    var diamondscount = 10

    var onGameOver : () -> Unit = {}

    private fun init(context: Context, width: Int, height: Int){

        surfaceHolder = holder
        paint = Paint()

        block = Block(width, height, 0, 0 )

        player = Player(context, height, width)

        for (i in 0..1){
            bombs.add(Bomb(context, width, height))
        }

        for (i in 0..9){
            diamonds.add(Diamond(context, width, height))
        }

        for (i in 0..4){
            miners.add(Miner(context, width, height))
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

        for (bomb in bombs){
            bomb.update()
            if (Rect.intersects(player.detectCollision, bomb.detectCollision)) {
                boom.x = bomb.x
                boom.y = bomb.y
                boom.update()
                bomb.x = -500
            }
        }

        for (diamond in diamonds){
            diamond.update()
            if (Rect.intersects(boom.detectCollision, diamond.detectCollision)) {
                diamond.x = -500
                diamondscount--
            }
        }

        for (miner in miners){
            miner.update()
            if (Rect.intersects(boom.detectCollision, miner.detectCollision)) {
                miner.x = -500
                lives--
            }
        }

        if (lives <= 0 ){
            onGameOver()
        }
    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            // design code here

            canvas.drawColor(Color.BLACK)

            paint.color = Brown.hashCode()

            val rect = Rect(block.x, block.y, block.width, block.height)
            canvas.drawRect(rect, paint)

            for (diamond in diamonds){
                canvas.drawBitmap(diamond.bitmap, diamond.x.toFloat(), diamond.y.toFloat(), paint)
            }

            for (bomb in bombs){
                canvas.drawBitmap(bomb.bitmap, bomb.x.toFloat(), bomb.y.toFloat(), paint)
            }
            for (miner in miners){
                canvas.drawBitmap(miner.bitmap, miner.x.toFloat(), miner.y.toFloat(), paint)
            }
            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            paint.textSize = 55f
            paint.color = Color.WHITE
            canvas.drawText("Lives: ${lives}", 10f, 100f, paint)
            canvas.drawText("Diamonds: ${diamondscount}", 10f, 160f, paint)

            if (diamondscount == 0){
                paint.textSize = 100f
                paint.color = Color.GREEN
                canvas.drawText("You Win", (width / 2f) - 100f, (height / 2f) - 50f, paint)
            }
            if (lives == 0){
                paint.textSize = 100f
                paint.color = Color.RED
                canvas.drawText("Game Over", (width / 2f) - 100f, (height / 2f) - 50f, paint)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN-> {
                if ((event.x.toInt() > player.x) && (event.x.toInt() - player.x > 120)) {
                    player.left = false
                    player.right = true
                }
                if ((event.x.toInt() < player.x) && (player.x - event.x.toInt() > 120)) {
                    player.left = true
                    player.right = false
                }
                if ((event.y.toInt() > player.y) && (event.y.toInt() - player.y > 120)) {
                    player.up = false
                    player.down = true
                }
                if ((event.y.toInt() < player.y) && (player.y - event.y.toInt() > 120)) {
                    player.up = true
                    player.down = false
                }
            }
            MotionEvent.ACTION_UP-> {
                player.left = false
                player.right = false
                player.up = false
                player.down = false
            }
        }
        return true
    }
}